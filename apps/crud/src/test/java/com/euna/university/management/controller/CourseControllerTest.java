package com.euna.university.management.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.euna.university.management.error.NoRecordFoundException;
import com.euna.university.management.util.CourseUtil;
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
    public void index_success() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void returnAllCourses_success() throws Exception {
        List<Course> allCourses = new ArrayList<>();

        Course course = CourseUtil.createCourse();
        allCourses.add(course);

        Mockito.when(courseService.fetchAllCourses())
                .thenReturn(allCourses);

        mockMvc.perform(get("/fetchAllCourses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].courseName", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$[0].courseCode", Matchers.is("Angel")))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void returnCourseById_success() throws Exception {
        Course course = CourseUtil.createCourse();

        Mockito.when(courseService.fetchCourseById(new BigInteger("1")))
                .thenReturn(course);

        mockMvc.perform(get("/fetchCourseById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId", Matchers.is(1)))
                .andExpect(jsonPath("$.courseName", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$.courseCode", Matchers.is("Angel")));
    }

    @Test
    public void returnCourseById_failure() throws Exception {
        doThrow(NoRecordFoundException.class)
                .when(courseService)
                .fetchCourseById(any(BigInteger.class));

        mockMvc.perform(get("/fetchCourseById/1"))
                .andExpect(status().isNotFound());

        verify(courseService).fetchCourseById(BigInteger.valueOf(1));
    }

    @Test
    public void saveCourse_success() throws Exception {
        String uri = "/createCourse";

        Course course = CourseUtil.createCourse();

        Mockito.when(courseService.createCourse(course)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getCourseJsonContent()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseId", Matchers.is(1)))
                .andExpect(jsonPath("$.courseName", Matchers.is("TestCourse")))
                .andExpect(jsonPath("$.courseCode", Matchers.is("Angel")));
    }

    @Test
    public void saveCourse_failure() throws Exception {
        String uri = "/createCourse";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
               .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"courseId\": 1}"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCourseById_success() throws Exception {
        Mockito.doNothing()
                .when(courseService)
                .deleteCourseById(any(BigInteger.class));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteCourse/{id}", BigInteger.valueOf(123))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(courseService)
                .deleteCourseById(BigInteger.valueOf(123));
    }

    @Test
    void deleteCourseById_failure() throws Exception {
        doThrow(NoRecordFoundException.class)
                .when(courseService)
                .deleteCourseById(any(BigInteger.class));

        mockMvc.perform(MockMvcRequestBuilders
               .delete("/deleteCourse/{id}", BigInteger.valueOf(123))
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());

        verify(courseService).deleteCourseById(BigInteger.valueOf(123));
    }

    @Test
    public void updateCourse_success() throws Exception {
        String uri = "/updateCourse/1";
        BigInteger id = new BigInteger("1");

        Course course = CourseUtil.createCourse();

        Mockito.when(courseService.updateCourse(id, course))
               .thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(getCourseJsonContent()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.courseId", Matchers.is(1)))
                        .andExpect(jsonPath("$.courseName", Matchers.is("TestCourse")))
                        .andExpect(jsonPath("$.courseCode", Matchers.is("Angel")));
    }

    @Test
    public void updateCourse_failure() throws Exception {
        BigInteger id = new BigInteger("1");

        Course course = CourseUtil.createCourse();

        doThrow(NoRecordFoundException.class)
                .when(courseService)
                .updateCourse(id, course);

        mockMvc.perform(MockMvcRequestBuilders
               .put("/updateCourse/{id}", id)
               .contentType(MediaType.APPLICATION_JSON)
               .content(getCourseJsonContent()))
               .andExpect(status().isNotFound());

        verify(courseService).updateCourse(id, course);
    }

    String getCourseJsonContent() {
        return "{\"courseId\": 1,\"courseName\": \"TestCourse\",\"courseCode\": \"Angel\"}";
    }
}
