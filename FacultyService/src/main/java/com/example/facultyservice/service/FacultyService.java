package com.example.facultyservice.service;

import com.example.facultyservice.entity.Faculty;
import com.example.facultyservice.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> findAll(){
        return facultyRepository.findAll();
    }

    public Faculty getById(Long id){
        return facultyRepository.findById(id).get();
    }
    public Faculty save(Faculty faculty){
        facultyRepository.save(faculty);
        return faculty;
    }
    public Faculty delete(Faculty faculty){
        facultyRepository.delete(faculty);
        return faculty;
    }
}
