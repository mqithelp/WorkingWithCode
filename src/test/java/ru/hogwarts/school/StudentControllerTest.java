package ru.hogwarts.school;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.GetLimitStudents;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/student";
    }

    @Test
    public void testCreateStudent() {
        // Создание студента
        Student student = new Student();
        student.setName("Гарри Поттер");
        student.setAge(17);

        ResponseEntity<Student> response = restTemplate.postForEntity(
                getBaseUrl(),
                student,
                Student.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Гарри Поттер");
        assertThat(response.getBody().getAge()).isEqualTo(17);
    }

    @Test
    public void testGetStudentById() {
        // Сначала создаем студента
        Student student = new Student();
        student.setName("Рон Уизли");
        student.setAge(17);

        ResponseEntity<Student> createResponse = restTemplate.postForEntity(
                getBaseUrl(),
                student,
                Student.class
        );
        Long studentId = createResponse.getBody().getId();

        // Получаем студента по ID
        ResponseEntity<Student> response = restTemplate.getForEntity(
                getBaseUrl() + "/" + studentId,
                Student.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(studentId);
        assertThat(response.getBody().getName()).isEqualTo("Рон Уизли");
    }


    @Test
    public void testUpdateStudent() {
        // Создаем студента
        Student student = new Student();
        student.setName("Гермиона Грейнджер");
        student.setAge(17);

        ResponseEntity<Student> createResponse = restTemplate.postForEntity(
                getBaseUrl(),
                student,
                Student.class
        );
        Long studentId = createResponse.getBody().getId();
        student.setId(studentId);

        // Обновляем студента
        student.setAge(18);
        student.setName("Гермиона Грейнджер-Уизли");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> requestEntity = new HttpEntity<>(student, headers);

        ResponseEntity<Student> response = restTemplate.exchange(
                getBaseUrl(),
                HttpMethod.PUT,
                requestEntity,
                Student.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(studentId);
        assertThat(response.getBody().getName()).isEqualTo("Гермиона Грейнджер-Уизли");
        assertThat(response.getBody().getAge()).isEqualTo(18);
    }

      @Test
    public void testGetAllStudents() {
        // Получаем всех студентов
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                getBaseUrl() + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testFindByAgeBetween() {
        // Создаем студентов
        Student student1 = new Student();
        student1.setName("Студент 18 лет");
        student1.setAge(18);
        restTemplate.postForEntity(getBaseUrl(), student1, Student.class);

        Student student2 = new Student();
        student2.setName("Студент 22 года");
        student2.setAge(22);
        restTemplate.postForEntity(getBaseUrl(), student2, Student.class);

        Student student3 = new Student();
        student3.setName("Студент 25 лет");
        student3.setAge(25);
        restTemplate.postForEntity(getBaseUrl(), student3, Student.class);

        // Ищем студентов в возрасте от 20 до 24
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                getBaseUrl() + "/find?min=20&max=24",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).allMatch(s -> s.getAge() >= 20 && s.getAge() <= 24);
    }

    @Test
    public void testGetStudentsByFaculty() {
        // Тест поиска студентов по факультету
        // Предполагается, что в БД уже есть данные
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                getBaseUrl() + "/get?name=Гриффиндор",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetCountAllStudent() {
        ResponseEntity<Long> response = restTemplate.getForEntity(
                getBaseUrl() + "/count",
                Long.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetAvgAgeStudents() {
        ResponseEntity<Float> response = restTemplate.getForEntity(
                getBaseUrl() + "/avg",
                Float.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }


    @Test
    public void testGetAllStudentsFilterByCharASorted() {
        ResponseEntity<List<GetLimitStudents>> response = restTemplate.exchange(
                getBaseUrl() + "/filterA",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GetLimitStudents>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}