package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Был выполнен метод addStudent()");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        logger.info("Был выполнен метод getStudent()");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Был выполнен метод editStudent()");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Был выполнен метод deleteStudent()");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        logger.info("Был выполнен метод getAllStudents()");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getStudentByAge(int age) {
        logger.info("Был выполнен метод getStudentByAge()");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Был выполнен метод findByAgeBetween()");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> getStudentByFaculty(String name) {
        logger.info("Был выполнен метод getStudentByFaculty()");
        return studentRepository.findStudentsByFaculty_Name(name);
    }

    @Override
    public Student getStudentById(Long id) {
        logger.info("Был выполнен метод getStudentById()");
        return studentRepository.getStudentsById(id);
    }

    @Override
    public Long getCountAllStudent() {
        logger.info("Был выполнен метод getCountAllStudent()");
        return studentRepository.getCountStudents();
    }

    @Override
    public Float getAvgAgeStudents() {
        logger.info("Был выполнен метод getAvgAgeStudent()");
        return studentRepository.getAvgAgeStudents();
    }

    @Override
    public Collection<GetLimitStudents> getLimitStudents() {
        logger.info("Был выполнен метод getLimitStudents()");
        return studentRepository.getLastFiveStudents();
    }

}
