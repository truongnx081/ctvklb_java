package ctv.core_service.repository;


import ctv.core_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<ctv.core_service.entity.Course,Long> {

}
