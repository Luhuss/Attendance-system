package com.school.attendance_system.mapper;

import com.school.attendance_system.dto.UserDTO;
import com.school.attendance_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
