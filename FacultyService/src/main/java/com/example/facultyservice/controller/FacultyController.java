package com.example.facultyservice.controller;

import com.example.facultyservice.entity.Faculty;
import com.example.facultyservice.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public List<Faculty> findAll(){
        return facultyService.findAll();
    }

    @GetMapping("/{id}")
    public Faculty findById(@PathVariable(name = "id") Long id){
        return facultyService.getById(id);
    }
    @PostMapping("/")
    public Faculty save(@RequestBody Faculty faculty){
        return facultyService.save(faculty);
    }
    @DeleteMapping("/{id}")
    public Faculty delete(@PathVariable(name = "id") Long id){
        return facultyService.delete(facultyService.getById(id));
    }
    @PutMapping("/")
    public Faculty update(@RequestBody Faculty faculty){
        return facultyService.save(faculty);
    }
}
