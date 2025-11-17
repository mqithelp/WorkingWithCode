package ru.hogwarts.school;

import org.junit.jupiter.api.AfterEach;
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

    private Long tempId;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/student";
    }

    @Test
    public void testGetAllStudentsCount() throws Exception {
        String url = baseUrl + "/count";
        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        Assertions.assertEquals(20, response.getBody());

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
        Assertions.assertEquals(expectedStudent, response.getBody());
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

        Assertions.assertEquals(expectedStudent, response.getBody());
    }

    @Test
    public void testAddStudent() throws Exception {
        String url = baseUrl;
        Student expectedStudent = new Student();
        Faculty expectedFaculty = new Faculty();
        expectedStudent.setId(5000L);
        expectedStudent.setAge(16);
        expectedStudent.setName("Test Student");
        expectedFaculty.setId(3L);
        expectedFaculty.setName("Когтевран");
        expectedFaculty.setColor("синий и бронзовый");
        expectedStudent.setFaculty(expectedFaculty);

        Student result = restTemplate.postForObject(baseUrl, expectedStudent, Student.class);

        Assertions.assertNotNull(result.getId());
        expectedStudent.setId(result.getId());

        Assertions.assertEquals(expectedStudent, result);
        tempId = expectedStudent.getId();
    }

    @Test
    public void testFullStudentLifecycleWithFaculty() throws Exception {
        String url = baseUrl;
        Student expectedStudent = new Student();
        Faculty expectedFaculty = new Faculty();
        expectedStudent.setId(5000L);
        expectedStudent.setAge(16);
        expectedStudent.setName("Test Student");
        expectedFaculty.setId(3L);
        expectedFaculty.setName("Когтевран");
        expectedFaculty.setColor("синий и бронзовый");
        expectedStudent.setFaculty(expectedFaculty);

        Student result = restTemplate.postForObject(baseUrl, expectedStudent, Student.class);

        Assertions.assertNotNull(result.getId());
        expectedStudent.setId(result.getId());

        Assertions.assertEquals(expectedStudent, result);
        Long studentId = result.getId();

        try {
            // EDIT
            Student updateStudent = new Student();
            updateStudent.setId(studentId);
            updateStudent.setAge(18);
            updateStudent.setName("Updated Test Student");
            updateStudent.setFaculty(expectedFaculty);

            restTemplate.put(baseUrl, updateStudent);

            // GET
            Student updated = restTemplate.getForObject(baseUrl + "/" + studentId, Student.class);
            Assertions.assertEquals("Updated Test Student", updated.getName());
            Assertions.assertEquals(18, updated.getAge());
            Assertions.assertNotNull(updated.getFaculty());

        } finally {
            restTemplate.delete(baseUrl + "/" + studentId);
            ResponseEntity<Student> response = restTemplate.getForEntity(baseUrl + "/" + studentId, Student.class);
            Assertions.assertEquals(500, response.getStatusCodeValue());
        }
    }

}
