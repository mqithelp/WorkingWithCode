package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyIsNullException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long id = 0L;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        if (faculty == null) throw new FacultyIsNullException();
        this.id++;
        faculty.setId(this.id);
        faculties.put(this.id, faculty);
        return faculties.get(this.id);
    }

    @Override
    public Faculty getFaculty(Long id) {
        if (!faculties.containsKey(id)) throw new FacultyNotFoundException();
        return faculties.get(id);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        if (faculty == null) throw new FacultyIsNullException();
        Long id = faculty.getId();
        if (!faculties.containsKey(id)) throw new FacultyNotFoundException();
        faculties.put(id, faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        if (!faculties.containsKey(id)) throw new FacultyNotFoundException();
        Faculty deletedFaculty = faculties.get(id);
        faculties.remove(id);
        return deletedFaculty;
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }
    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
