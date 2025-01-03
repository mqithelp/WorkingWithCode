package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.GetLimitStudents;

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

    @Query(value = "SELECT id, name FROM student order by id desc LIMIT 5;", nativeQuery = true)
    Collection<GetLimitStudents> getLastFiveStudents();

    @Query(value = "SELECT id as id, UPPER(name) as name FROM student where name ILIKE 'А%' order by name;", nativeQuery = true)
    Collection<GetLimitStudents> getAllStudentsSorted();
}
