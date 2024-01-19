package com.euna.university.management.service;

import com.euna.university.management.error.NoRecordFoundException;
import com.euna.university.management.model.Course;
import com.euna.university.management.repository.CourseRepository;
import com.euna.university.management.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void createCourse_success() {
        Course course = createCourse();
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
    }

    @Test
    void fetchCourseById_success() throws NoRecordFoundException {
        BigInteger courseId = BigInteger.valueOf(1);
        Course existingCourse = createCourse();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
        Course fetchedCourse = courseService.fetchCourseById(courseId);

        assertNotNull(fetchedCourse);
    }

    @Test
    void fetchCourseById_failure() {
        BigInteger nonExistingId = BigInteger.valueOf(500);
        when(courseRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NoRecordFoundException.class,
                () -> courseService.fetchCourseById(nonExistingId));
    }

    @Test
    void fetchAllCourses_success() {
        List<Course> allCourses = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(allCourses);
        List<Course> fetchedCourses = courseService.fetchAllCourses();

        assertNotNull(fetchedCourses);
        Assertions.assertEquals(allCourses, fetchedCourses);
    }

    @Test
    void deleteCourseById_success() {
        BigInteger courseIdToDelete = BigInteger.valueOf(789);
        Mockito.doNothing().when(courseRepository).deleteById(courseIdToDelete);

        assertDoesNotThrow(() -> courseService.deleteCourseById(courseIdToDelete));

        verify(courseRepository, times(1)).deleteById(courseIdToDelete);
    }

    @Test
    void deleteCourseById_failure() {
        BigInteger nonExistingId = BigInteger.valueOf(999);
        doThrow(EmptyResultDataAccessException.class).when(courseRepository).deleteById(nonExistingId);

        assertThrows(NoRecordFoundException.class, () -> courseService.deleteCourseById(nonExistingId));
    }

    Course createCourse() {
        Course course = new Course();
        course.setCourseId(new BigInteger("1"));
        course.setCourseName("TestCourse");
        course.setCourseCode("Angel");
        return course;
    }
}
