//package com.troja.GradeBook.mapper;
//
//import com.troja.GradeBook.dto.RoleDto;
//import com.troja.GradeBook.entity.Role;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleMapper {
//
//    public RoleDto toDto(Role role) {
//        if (role == null) {
//            return null;
//        }
//
//        RoleDto roleDto = new RoleDto();
//        roleDto.setName(role.getName()); // Map the role name from the enum
//
//        return roleDto;
//    }
//
//    public Role toEntity(RoleDto roleDto) {
//        if (roleDto == null) {
//            return null;
//        }
//
//        Role role = new Role();
//        role.setRole(Role.valueOf(roleDto.getName())); // Convert string name back to RoleEnum
//
//        return role;
//    }
//}
