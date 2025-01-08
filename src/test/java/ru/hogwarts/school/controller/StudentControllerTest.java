package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testGetAllStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/all", String.class))
                .isNotEmpty();
    }
    @Test
    void addStudent() {
    }

    @Test
    void readStudent() {
    }

    @Test
    void editStudent() {
    }

    @Test
    void delStudent() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByAge() {
    }

    @Test
    void findByAgeBetween() {
    }

    @Test
    void studentsByFaculty() {
    }
}