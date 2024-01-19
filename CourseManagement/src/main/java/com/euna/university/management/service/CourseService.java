package com.euna.university.management.service;

import com.euna.university.management.error.NoRecordFoundException;
import com.euna.university.management.model.Course;
import java.math.BigInteger;
import java.util.List;


public interface CourseService {
    Course createCourse(Course course);

    Course fetchCourseById(BigInteger id);

    List<Course> fetchAllCourses();

    void deleteCourseById(BigInteger id) throws NoRecordFoundException;

    Course updateCourse(BigInteger id, Course updatedCourse) throws NoRecordFoundException;
}
