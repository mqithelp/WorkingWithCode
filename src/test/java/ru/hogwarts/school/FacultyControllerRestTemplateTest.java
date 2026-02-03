package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/faculty";
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Гриффиндор");
        faculty.setColor("красный");

        ResponseEntity<Faculty> response = restTemplate.postForEntity(
                getBaseUrl(),
                faculty,
                Faculty.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Гриффиндор");
        assertThat(response.getBody().getColor()).isEqualTo("красный");
    }

    @Test
    public void testGetFacultyById() {
        // Создаем факультет
        Faculty faculty = new Faculty();
        faculty.setName("Слизерин");
        faculty.setColor("зеленый");

        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity(
                getBaseUrl(),
                faculty,
                Faculty.class
        );
        Long facultyId = createResponse.getBody().getId();

        // Получаем факультет по ID
        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                getBaseUrl() + "/" + facultyId,
                Faculty.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(facultyId);
        assertThat(response.getBody().getName()).isEqualTo("Слизерин");
    }

    @Test
    public void testUpdateFaculty() {
        // Создаем факультет
        Faculty faculty = new Faculty();
        faculty.setName("Когтевран");
        faculty.setColor("синий");

        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity(
                getBaseUrl(),
                faculty,
                Faculty.class
        );
        Long facultyId = createResponse.getBody().getId();
        faculty.setId(facultyId);

        // Обновляем факультет
        faculty.setColor("голубой");
        faculty.setName("Когтевран Обновленный");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> requestEntity = new HttpEntity<>(faculty, headers);

        ResponseEntity<Faculty> response = restTemplate.exchange(
                getBaseUrl() + "/" + facultyId,
                HttpMethod.PUT,
                requestEntity,
                Faculty.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(facultyId);
        assertThat(response.getBody().getName()).isEqualTo("Когтевран Обновленный");
        assertThat(response.getBody().getColor()).isEqualTo("голубой");
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<List<Faculty>> response = restTemplate.exchange(
                getBaseUrl() + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetFacultiesByColorOrName() {
        // Создаем факультеты для теста
        Faculty faculty1 = new Faculty();
        faculty1.setName("Гриффиндор");
        faculty1.setColor("красный");
        restTemplate.postForEntity(getBaseUrl(), faculty1, Faculty.class);

        // Ищем по цвету
        ResponseEntity<List<Faculty>> responseByColor = restTemplate.exchange(
                getBaseUrl() + "?color=красный",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        );

        assertThat(responseByColor.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseByColor.getBody()).isNotNull();
        assertThat(responseByColor.getBody()).anyMatch(f -> f.getColor().equals("красный"));

        // Ищем по имени
        ResponseEntity<List<Faculty>> responseByName = restTemplate.exchange(
                getBaseUrl() + "?name=Гриффиндор",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        );

        assertThat(responseByName.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseByName.getBody()).isNotNull();
        assertThat(responseByName.getBody()).anyMatch(f -> f.getName().equals("Гриффиндор"));
    }

    @Test
    public void testGetFacultyByStudents() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                getBaseUrl() + "/get?name=Гарри Поттер",
                Faculty.class
        );

        // Может вернуть факультет или 200 с null
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}