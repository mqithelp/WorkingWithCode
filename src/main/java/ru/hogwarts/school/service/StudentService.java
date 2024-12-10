package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student addStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> getAllStudents();

    Collection<Student> getStudentByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);
}
