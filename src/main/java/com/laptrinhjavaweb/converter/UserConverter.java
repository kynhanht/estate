package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.respone.UserSearchResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public UserSearchResponse convertToUserSearchResponse(UserEntity userEntity){

        UserSearchResponse response = modelMapper.map(userEntity, UserSearchResponse.class);
        if(!userEntity.getRoles().isEmpty()){
            response.setRoleCode(userEntity.getRoles().get(0).getCode());
        }
        return  response;
    }

}
