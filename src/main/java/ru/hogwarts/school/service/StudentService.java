package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long id = 0L;

    public Student addStudent(Student student) {
        if (student == null) return null;
        this.id++;
        student.setId(this.id);
        students.put(this.id, student);
        return students.get(this.id);
    }

    public Student getStudent(Long id) {
        if (this.id < id) return null;
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        Long id;
        if (student == null) return null;
        id = student.getId();
        if (!students.containsKey(id)) return null;
        students.put(id, student);
        return student;
    }

    public Student deleteStudent(Long id) {
        Student deletedStudent;
        if (this.id < id) return null;
        if (!students.containsKey(id)) return null;
        deletedStudent = students.get(id);
        students.remove(id);
        return deletedStudent;
    }
    public Collection<Student> getAllStudents() {
        return students.values();
    }
}
