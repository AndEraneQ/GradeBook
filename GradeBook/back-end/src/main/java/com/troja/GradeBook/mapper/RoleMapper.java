package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.RoleDto;
import com.troja.GradeBook.entity.Role;

public class RoleMapper {
    RoleDto toDto(Role role){
        return new RoleDto(role.getName());
    }
}
