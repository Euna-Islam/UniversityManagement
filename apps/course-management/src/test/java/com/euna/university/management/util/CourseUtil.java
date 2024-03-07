package com.euna.university.management.util;

import com.euna.university.management.model.Course;

import java.math.BigInteger;

public class CourseUtil {
    public static Course createCourse() {
        BigInteger id = new BigInteger("1");
        Course course = new com.euna.university.management.model.Course();
        course.setCourseId(id);
        course.setCourseName("TestCourse");
        course.setCourseCode("Angel");

        return course;
    }
}
