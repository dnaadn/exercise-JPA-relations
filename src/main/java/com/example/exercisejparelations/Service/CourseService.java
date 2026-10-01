package com.example.exercisejparelations.Service;

import com.example.exercisejparelations.API.ApiException;
import com.example.exercisejparelations.DTO.StudentDTO;
import com.example.exercisejparelations.Model.Course;
import com.example.exercisejparelations.Model.Student;
import com.example.exercisejparelations.Model.Teacher;
import com.example.exercisejparelations.Repository.CourseRepository;
import com.example.exercisejparelations.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Integer teacherId, Course course){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course){
        Course oldCourse = courseRepository.findCourseById(id);
        if(oldCourse==null){
            throw new ApiException("Course not found");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null){
            throw new ApiException("Course not found");
        }
        courseRepository.delete(course);
    }

    public String getTeacherNameByCourse(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        if (course.getTeacher() == null) {
            throw new ApiException("The Course has no teacher");
        }
        return course.getTeacher().getName();
    }

    public List<StudentDTO> getStudentsByCourse(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);

        if (course == null) {
            throw new ApiException("Course not found");
        }
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : course.getStudents()) {
            StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), student.getAge(), student.getMajor());
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }


}
