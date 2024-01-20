package com.euna.university.management.controller;

import com.euna.university.management.error.NoRecordFoundException;
import com.euna.university.management.model.Course;
import com.euna.university.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping(path = "/")
    @ResponseBody
    public String index() {
        return "Application Running";
    }

    @RequestMapping(path = "/fetchAllCourses", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Course> returnAllCourses() {
        return courseService.fetchAllCourses();
    }

    @RequestMapping(path = "/fetchCourseById/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Course returnCourseById(@PathVariable("id") BigInteger id) {
        return courseService.fetchCourseById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/createCourse",
            produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveCourse(@Valid @RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/deleteCourse/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable("id") BigInteger id) {
        courseService.deleteCourseById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/updateCourse/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Course updateCourse(@PathVariable("id") BigInteger id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoRecordFoundException.class)
    public Map<String, String> handleNoRecordFoundException(NoRecordFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", ex.getMessage());
        return errors;
    }
}