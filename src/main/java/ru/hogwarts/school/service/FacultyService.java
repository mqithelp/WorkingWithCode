package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long id = 0L;

    public Faculty addFaculty(Faculty faculty) {
        if (faculty == null) return null;
        this.id++;
        faculties.put(this.id, faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        if (this.id < id) return null;
        return faculties.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        Long id;
        if (faculty == null) return null;
        id = faculty.getId();
        if (!faculties.containsKey(id)) return null;
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id) {
        Faculty deletedFaculty;
        if (this.id < id) return null;
        if (!faculties.containsKey(id)) return null;
        deletedFaculty = faculties.get(id);
        faculties.remove(id);
        return deletedFaculty;
    }
}
