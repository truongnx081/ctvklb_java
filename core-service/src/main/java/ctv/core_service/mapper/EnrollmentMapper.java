package ctv.core_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ctv.core_service.dto.request.EnrollmentCreationRequest;
import ctv.core_service.dto.request.EnrollmentUpdationRequest;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    Enrollment toEnrollment(EnrollmentCreationRequest request);

    UserResponse toEnrollmentResponse(Enrollment enrollment);

    void updateEnrollment(@MappingTarget Enrollment enrollment, EnrollmentUpdationRequest request);
}
