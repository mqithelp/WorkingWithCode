    package ru.hogwarts.school;

    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.web.client.TestRestTemplate;
    import org.springframework.boot.test.web.server.LocalServerPort;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import ru.hogwarts.school.controller.StudentController;

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class SchoolApplicationTests {

   @LocalServerPort
    private int port;

    @Autowired
        private StudentController studentController;

    @Autowired
        private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception{
        Assertions.assertNotNull(studentController);
    }
    @Test
        public void testGetAllStudents() throws Exception{
        String url = "http://localhost:" + port + "/student/count";
        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        //Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        //Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(20, response.getBody()); // Достаем значение по ключу

    }

    }
