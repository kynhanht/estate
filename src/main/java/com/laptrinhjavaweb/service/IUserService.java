package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.PasswordRequest;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.UserSearchRequest;
import com.laptrinhjavaweb.dto.respone.PaginationResponse;
import com.laptrinhjavaweb.dto.respone.StaffResponse;
import com.laptrinhjavaweb.dto.respone.UserSearchResponse;

import java.util.List;
import java.util.Map;

public interface IUserService {

    PaginationResponse<UserSearchResponse> searchUsers(UserSearchRequest request);

    UserDTO findOneByUserName(String userName);

    UserDTO findUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void updatePassword(Long id, PasswordRequest passwordRequest);

    UserDTO resetPassword(Long id);

    UserDTO updateProfileOfUser(String userName, UserDTO userDTO);

    void deleteUsers(List<Long> ids);

    Map<Long, String> getStaffs();

    List<StaffResponse> getStaffsByBuildingId(Long buildingId);

    List<StaffResponse> getStaffByCustomerId(Long customerId);

}
