package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Student student;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/student";
    }

    @Test
    public void testGetAllStudentsCount() throws Exception {
        String url = baseUrl + "/count";
        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        Assertions.assertEquals(20, response.getBody()); // Достаем значение по ключу

    }

    @Test
    public void testGetStudentById() throws Exception {
        String url = baseUrl + "/{id}";
        Student expectedStudent = new Student();
        Faculty expectedFaculty = new Faculty();
        expectedStudent.setId(5L);
        expectedStudent.setAge(16);
        expectedStudent.setName("Luna Lovegood");
        expectedFaculty.setId(3L);
        expectedFaculty.setName("Когтевран");
        expectedFaculty.setColor("синий и бронзовый");
        expectedStudent.setFaculty(expectedFaculty);

        ResponseEntity<Student> response = restTemplate.getForEntity(url, Student.class, 5);
        Assertions.assertEquals(expectedStudent, response.getBody()); // Достаем значение по ключу
   }


    @Test
    public void testGetStudentByFaculity() throws Exception {
        String url = baseUrl + "/get?name={name}";
        List<Student> expectedStudent;
        InputStream is = getClass().getResourceAsStream("/griffindor.json");
        if (is == null) {
            throw new FileNotFoundException("Файл griffindor.json не найден");
        }
        expectedStudent = objectMapper.readValue(is, new TypeReference<List<Student>>() {});
        ResponseEntity<List<Student>> response = restTemplate.exchange(url, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Student>>() {},"Гриффиндор");

        Assertions.assertEquals(expectedStudent, response.getBody()); // Достаем значение по ключу
    }

}
