
package com.example.exercisejparelations.Controller;

import com.example.exercisejparelations.API.ApiResponse;
import com.example.exercisejparelations.Model.Student;
import com.example.exercisejparelations.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/change-major/{studentId}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer studentId, @RequestBody String major) {
        studentService.changeMajor(studentId, major);
        return ResponseEntity.status(200).body(new ApiResponse("Major updated successfully"));
    }

    @PutMapping("/assign-student/{courseId}/{studentId}")
    public ResponseEntity<?> assignStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        studentService.assignStudentToCourse(courseId, studentId);
        return ResponseEntity.status(200).body(new ApiResponse("Student assigned to course successfully"));
    }
}
