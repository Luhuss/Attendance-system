package com.school.attendance_system.mapper;

import com.school.attendance_system.dto.RoleDTO;
import com.school.attendance_system.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO dto);
}
