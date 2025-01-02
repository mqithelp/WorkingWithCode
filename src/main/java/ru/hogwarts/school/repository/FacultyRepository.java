package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.GetLimitStudents;
import ru.hogwarts.school.service.GetOneFaculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    Faculty findByStudents_NameIgnoreCase(String name);

    @Query(value = "SELECT name as name FROM faculty order by LENGTH(name) desc LIMIT 1;", nativeQuery = true)
    Collection<GetOneFaculty> getLongestFaculty();

}
