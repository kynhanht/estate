package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.ErrorMessageConstants;
import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.request.PasswordRequest;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.UserSearchRequest;
import com.laptrinhjavaweb.dto.respone.PaginationResponse;
import com.laptrinhjavaweb.dto.respone.StaffResponse;
import com.laptrinhjavaweb.dto.respone.UserSearchResponse;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.UserEntity_;
import com.laptrinhjavaweb.enums.SearchOperationEnum;
import com.laptrinhjavaweb.exception.InternalException;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.specifications.SearchCriteria;
import com.laptrinhjavaweb.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;


    @Override
    public PaginationResponse<UserSearchResponse> searchUsers(UserSearchRequest request) {

        UserSpecification userSpecification = new UserSpecification();
        Specification<UserEntity> specification = Specification
                .where(userSpecification.byCommon(new SearchCriteria(UserEntity_.STATUS, SystemConstants.ACTIVE_STATUS, SearchOperationEnum.EQUAL)))
                .and(userSpecification.byCommon(new SearchCriteria(UserEntity_.FULL_NAME, request.getName(), SearchOperationEnum.CONTAINING))
                .or(userSpecification.byCommon(new SearchCriteria(UserEntity_.USER_NAME, request.getName(), SearchOperationEnum.CONTAINING))))
                .and(userSpecification.orderBy(request.getSortColumnName(), request.getSortDirection()));
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getTotalPageItems());
        Page<UserEntity> page = userRepository.findAll(specification, pageable);
        List<UserSearchResponse> responses = page
                .getContent()
                .stream()
                .map(userConverter::convertToUserSearchResponse)
                .collect(Collectors.toList());
        PaginationResponse<UserSearchResponse> paginationResponse = new PaginationResponse();
        paginationResponse.setPage(request.getPage());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setTotalPageItems(request.getTotalPageItems());
        paginationResponse.setTotalItems((int) page.getTotalElements());
        paginationResponse.setListResult(responses);
        return paginationResponse;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        UserDTO userDTO = userConverter.convertToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserEntity entity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDTO(entity);
        roles.forEach(item -> {
            dto.setRoleCode(item.getCode());
        });
        return dto;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO newUser) {
        RoleEntity role = roleRepository
                .findOneByCode(newUser.getRoleCode())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.ROLE_NOT_FOUND));
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(SystemConstants.ACTIVE_STATUS);
        userEntity.setPassword(passwordEncoder.encode(SystemConstants.PASSWORD_DEFAULT));
        return userConverter.convertToDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository
                .findOneByCode(updateUser.getRoleCode())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.ROLE_NOT_FOUND));
        UserEntity oldUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        oldUser.setFullName(updateUser.getFullName());
        oldUser.setRoles(Stream.of(role).collect(Collectors.toList()));
        return userConverter.convertToDTO(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void updatePassword(Long id, PasswordRequest passwordRequest){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        if (passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())
                && passwordRequest.getNewPassword().equals(passwordRequest.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new InternalException(ErrorMessageConstants.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(Long id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        userEntity.setPassword(passwordEncoder.encode(SystemConstants.PASSWORD_DEFAULT));
        return userConverter.convertToDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String userName, UserDTO updateUser) {
        UserEntity oldUser = userRepository
                .findOneByUserName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        oldUser.setFullName(updateUser.getFullName());
        oldUser.setPhone(updateUser.getPhone());
        oldUser.setEmail(updateUser.getEmail());
        return userConverter.convertToDTO(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            UserEntity userEntity = userRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
            userEntity.setStatus(SystemConstants.NO_ACTIVE_STATUS);
            userRepository.save(userEntity);
        });
    }

    @Override
    public Map<Long, String> getStaffs() {
        Map<Long, String> staffMap = new HashMap<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        for (UserEntity userEntity : staffs) {
            staffMap.put(userEntity.getId(), userEntity.getFullName());
        }
        return staffMap;
    }

    @Override
    public List<StaffResponse> getStaffsByBuildingId(Long buildingId) {
        // Get All staff who are active
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        // Map to List<StaffResponse>
        List<StaffResponse> staffResponses = staffs.stream().map(staff -> {
                    StaffResponse staffRespone = new StaffResponse();
                    staffRespone.setStaffId(staff.getId());
                    staffRespone.setFullName(staff.getFullName());
                    staffRespone.setChecked("");
                    // Map into List of buildingId
                    List<Long> buildingIds = staff.getBuildings()
                            .stream()
                            .map(building -> building.getId())
                            .collect(Collectors.toList());
                    // Check List of buildingId contain request building
                    if(buildingIds.contains(buildingId)) staffRespone.setChecked("checked");
                    return staffRespone;
                }
        ).collect(Collectors.toList());
        return staffResponses;
    }

    @Override
    public List<StaffResponse> getStaffByCustomerId(Long customerId) {
        // Get All staff who are active
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        // Map to List<StaffResponse>
        List<StaffResponse> staffResponses = staffs.stream().map(staff -> {
                    StaffResponse staffRespone = new StaffResponse();
                    staffRespone.setStaffId(staff.getId());
                    staffRespone.setFullName(staff.getFullName());
                    staffRespone.setChecked("");
                    // Map into List of buildingId
                    List<Long> customerIds = staff.getCustomers()
                            .stream()
                            .map(customer -> customer.getId())
                            .collect(Collectors.toList());
                    // Check List of customerId contain request customerId
                    if(customerIds.contains(customerId)) staffRespone.setChecked("checked");
                    return staffRespone;
                }
        ).collect(Collectors.toList());
        return staffResponses;
    }
}
