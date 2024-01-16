package com.euna.university.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@ApiModel(description = "Course")
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @Min(value = 1L, message = "The value must be greater than zero")
    public BigInteger courseid;

    @NotBlank(message="Course name is mandatory")
    @ApiModelProperty(notes = "Course name is mandatory")
    public String coursename;

    @NotBlank(message="Author name is mandatory")
    @ApiModelProperty(notes = "Author name is mandatory")
    public String author;

    public Course() {
    }
}
