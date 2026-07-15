package com.school.attendance_system.mapper;

import com.school.attendance_system.dto.CourseDTO;
import com.school.attendance_system.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course toEntity(CourseDTO dto);
}
