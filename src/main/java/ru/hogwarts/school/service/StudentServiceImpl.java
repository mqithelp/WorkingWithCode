package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidStudentException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {

        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {

        return null;
    }

    @Override
    public Collection<Student> getAllStudents() {
        return null;
    }

    @Override
    public Collection<Student> getStudentByAge(int age) {
        return null;
//                students.values().stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
    }
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
