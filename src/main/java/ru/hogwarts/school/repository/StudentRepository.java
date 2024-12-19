package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findStudentsByFaculty_Name(String name);
    Student getStudentsById(Long id);

    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    Long getCountStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Float getAvgAgeStudents();

}
