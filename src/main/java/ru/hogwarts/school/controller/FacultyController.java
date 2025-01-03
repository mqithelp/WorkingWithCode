package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.GetOneFaculty;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> readFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        facultyService.editFaculty(id, faculty);
        if (faculty == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> delFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public Collection<Faculty> getAll() {
        return facultyService.getAllFaculties();
    }

    @GetMapping()
    public Collection<Faculty> getByColorOrName(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String name) {
        return facultyService.getByColorOrName(color,name);
    }
    @GetMapping("get")
    public Faculty facultyByStudents(
            @RequestParam(required = false) String name) {
        return facultyService.getfacultyByStudents(name);
    }
@GetMapping("lf")
    public Collection<GetOneFaculty> getOneLongestNameFaculty() {
        return facultyService.getOneFacultyByLength();
}
}
