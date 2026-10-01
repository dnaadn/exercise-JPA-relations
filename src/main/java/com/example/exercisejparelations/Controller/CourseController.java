package com.example.exercisejparelations.Controller;

import com.example.exercisejparelations.API.ApiResponse;
import com.example.exercisejparelations.Model.Course;
import com.example.exercisejparelations.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<?> addCourse(@PathVariable Integer teacherId, @RequestBody @Valid Course course) {
        courseService.addCourse(teacherId, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
    }

    @GetMapping("/teacher/{courseId}")
    public ResponseEntity<?> getTeacherNameByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getTeacherNameByCourse(courseId));
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getStudentsByCourse(courseId));
    }
}
