package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    public List<RoleDTO> findAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleDTO> list = new ArrayList<>();
        roleEntities.forEach(item -> {
            RoleDTO roleDTO = roleConverter.convertToDto(item);
            list.add(roleDTO);
        });
        return list;
    }

    @Override
    public Map<String, String> getRoles() {
        Map<String, String> roles = new HashMap<>();
        List<RoleEntity> roleEntities = roleRepository.findAll();
        roleEntities.forEach(entity -> {
            roles.put(entity.getCode(), entity.getName());
        });
        return roles;
    }
}
