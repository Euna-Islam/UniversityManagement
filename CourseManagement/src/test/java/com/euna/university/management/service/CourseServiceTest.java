package com.euna.university.management.service;

import com.euna.university.management.model.Course;
import com.euna.university.management.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @Test
    public void it_should_return_all_courses() throws Exception {
        List<Course> allCourses = new ArrayList<>();

        Course course = new Course();
        course.setCourseid(new BigInteger("1"));
        course.setCoursename("TestCourse");
        course.setAuthor("Angel");
//        courseRepository.save(course);
//        allCourses.add(course);

//        Mockito.when(courseRepository.).thenReturn(allCourses);
//
//        mockMvc.perform(get("/fetchAllCourses"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].coursename", Matchers.is("TestCourse")));
    }
}
