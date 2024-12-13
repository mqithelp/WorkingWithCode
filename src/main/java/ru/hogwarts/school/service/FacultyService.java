package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Long id, Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> getByColorOrName(String color, String Name);

    Faculty getfacultyByStudents(String name);
}
