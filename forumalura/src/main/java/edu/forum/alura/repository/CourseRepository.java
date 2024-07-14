package edu.forum.alura.repository;

import edu.forum.alura.models.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
}
