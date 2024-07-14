package edu.forum.alura.services;

import edu.forum.alura.controllers.dtos.CourseRequestDto;
import edu.forum.alura.controllers.dtos.CourseResponseDto;
import edu.forum.alura.exceptions.NotFoundException;
import edu.forum.alura.models.entities.CourseEntity;
import edu.forum.alura.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public  CourseResponseDto createCourse(CourseRequestDto course) {
        CourseEntity newCourse = new CourseEntity(course);
        courseRepository.save(newCourse);
        return new CourseResponseDto(newCourse);
    }

    public CourseResponseDto getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(CourseResponseDto::new)
                .orElseThrow(() -> new NotFoundException("Course " + id +
                        " not found"));
    }

    public CourseEntity getCourseEntityById(Long id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course " + id + " not " +
                    "found");
        }
        return course.get();
    }

    public void deleteCourse() {
        // delete course
    }
}
