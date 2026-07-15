package com.school.attendance_system.mapper;

import com.school.attendance_system.dto.StudentDTO;
import com.school.attendance_system.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);
}
