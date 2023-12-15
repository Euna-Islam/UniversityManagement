package com.euna.university.management.service.impl;

import com.euna.university.management.error.NoRecordFoundException;
import com.euna.university.management.model.Course;
import com.euna.university.management.repository.CourseRepository;
import com.euna.university.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    public CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course fetchCourseById(BigInteger id) throws NoRecordFoundException {
        return courseRepository.findById(id).orElseThrow
                (() -> new NoRecordFoundException("Course id '" + id + "' does no exist"));
    }

    @Override
    public List<Course> fetchAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourseById(BigInteger id) {
        try {
            courseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoRecordFoundException("Course id '" + id + "' does no exist");
        }
    }
}
