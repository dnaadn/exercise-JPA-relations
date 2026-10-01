package com.example.exercisejparelations.Service;

import com.example.exercisejparelations.API.ApiException;
import com.example.exercisejparelations.Model.Course;
import com.example.exercisejparelations.Model.Student;
import com.example.exercisejparelations.Repository.CourseRepository;
import com.example.exercisejparelations.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }


    public void updateStudent(Integer id, Student student) {

        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null) {
            throw new ApiException("Student not found");
        }
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());

        studentRepository.save(oldStudent);
    }


    public void deleteStudent(Integer id) {

        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        studentRepository.delete(student);
    }

    public void assignStudentToCourse(Integer studentId, Integer courseId) {

        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new ApiException("Student not found");
        }

        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }

        student.getCourseSet().add(course);

        studentRepository.save(student);
    }

    public void changeMajor(Integer studentId, String major) {
        Student student = studentRepository.findStudentById(studentId);

        if (student == null) {
            throw new ApiException("Student not found");
        }

        student.setMajor(major);
        student.getCourseSet().clear();
        studentRepository.save(student);
    }
}
