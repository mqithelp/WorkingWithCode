package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyIsNullException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        return null;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return null;
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return null;
    }
    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        return null;
//                faculties.values().stream()
//                .filter(faculty -> faculty.getColor().equals(color))
//                .collect(Collectors.toList());
    }
}
