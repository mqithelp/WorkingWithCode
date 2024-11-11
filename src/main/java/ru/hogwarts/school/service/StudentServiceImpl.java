package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidStudentException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private Map<Long, Student> students = new HashMap<>();
    private Long id = 0L;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        if (student == null) throw new InvalidStudentException();
        this.id++;
        student.setId(this.id);
        students.put(this.id, student);
        return students.get(this.id);
    }

    @Override
    public Student getStudent(Long id) {
        if (!students.containsKey(id)) throw new InvalidStudentException();
        return students.get(id);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student == null) throw new InvalidStudentException();
        Long id = student.getId();
        if (!students.containsKey(id)) throw new StudentNotFoundException();
        students.put(id, student);
        return students.get(id);
    }

    @Override
    public Student deleteStudent(Long id) {
        if (!students.containsKey(id)) throw new StudentNotFoundException();
        Student deletedStudent = students.get(id);
        students.remove(id);
        return deletedStudent;
    }

    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }

    @Override
    public Collection<Student> getStudentByAge(int age) {
        return students.values().stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
    }
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
