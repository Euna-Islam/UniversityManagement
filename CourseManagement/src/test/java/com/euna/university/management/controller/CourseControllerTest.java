package com.euna.university.management.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.euna.university.management.model.Course;
import com.euna.university.management.service.CourseService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @MockBean
    CourseService courseService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void it_should_return_index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_all_courses() throws Exception {
        List<Course> allCourses = new ArrayList<>();

        Course course = new Course();
        course.setCourseid(new BigInteger("1"));
        course.setCoursename("TestCourse");
        course.setAuthor("Angel");
        allCourses.add(course);

        Mockito.when(courseService.fetchAllCourses()).thenReturn(allCourses);

        mockMvc.perform(get("/fetchAllCourses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseid", Matchers.is(1)))
                .andExpect(jsonPath("$[0].coursename", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$[0].author", Matchers.is("Angel")));
    }

    @Test
    public void it_should_return_one_course_by_id() throws Exception {
        Course course = new Course();
        course.setCourseid(new BigInteger("1"));
        course.setCoursename("TestCourse");
        course.setAuthor("Angel");

        Mockito.when(courseService.fetchCourseById(new BigInteger("1"))).thenReturn(course);

        mockMvc.perform(get("/fetchCourseById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseid", Matchers.is(1)))
                .andExpect(jsonPath("$.coursename", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$.author", Matchers.is("Angel")));
    }

    @Test
    public void it_should_return_void() throws Exception {
           mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCourse/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_created_course() throws Exception {
        String uri = "/createCourse";

        Course course = new Course();
        course.setCourseid(new BigInteger("1"));
        course.setCoursename("TestCourse");
        course.setAuthor("Angel");

        Mockito.when(courseService.createCourse(course)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"courseid\": 1,\"coursename\": \"TestCourse\",\"author\": \"Angel\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseid", Matchers.is(1)))
                .andExpect(jsonPath("$.coursename", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$.author", Matchers.is("Angel")));
    }

    @Test
    public void it_should_return_bad_request() throws Exception {
        String uri = "/createCourse";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"courseid\": 1}"))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void it_should_return_NoRecordFoundException() throws Exception {
////        Mockito.when(courseService.deleteCourseById(new BigInteger("1"))).thenThrow(new NoRecordFoundException("Course id '4' does no exist"));
//        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCourse/1"))
//                .andExpect(status().isOk());
//    }

}
