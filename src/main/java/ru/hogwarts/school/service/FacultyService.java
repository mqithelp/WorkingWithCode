package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> getFacultyByColor(String color);
}
