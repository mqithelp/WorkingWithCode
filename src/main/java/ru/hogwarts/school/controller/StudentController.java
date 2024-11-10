package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> readStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);

    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        if (student == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public Student delStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("all")
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }
    @GetMapping()
    public Collection<Student> getByAge(@RequestParam int age) {
        return studentService.getStudentByAge(age);
    }

}