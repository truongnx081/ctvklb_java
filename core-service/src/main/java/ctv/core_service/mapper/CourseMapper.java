package ctv.core_service.mapper;


import ctv.core_service.dto.request.CourseCreationRequest;
import ctv.core_service.dto.request.CourseUpdationRequest;
import ctv.core_service.dto.response.CourseResponse;
import ctv.core_service.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toCourse(CourseCreationRequest request);
    CourseResponse toCourseResponse(Course course);

    void updateCourse(@MappingTarget Course course, CourseUpdationRequest request);
}
