package com.example.sinhvienservice.service;

import com.example.sinhvienservice.entity.Faculty;
import com.example.sinhvienservice.entity.SinhVien;
import com.example.sinhvienservice.entity.StudentAndFacultyVo;
import com.example.sinhvienservice.repository.SinhVienRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<SinhVien> findAll(){
        return sinhVienRepository.findAll();
    }
    public SinhVien findById(Long id){
        return sinhVienRepository.findById(id).get();
    }

    public SinhVien save(SinhVien sinhVien){
        return sinhVienRepository.save(sinhVien);
    }
    public SinhVien delete(SinhVien sinhVien){
        sinhVienRepository.delete(sinhVien);
        return sinhVien;
    }
    @Retry(name = "retryfaculty")
    public StudentAndFacultyVo studentAndFacultyVo(Long id){
        StudentAndFacultyVo studentAndFacultyVo = new StudentAndFacultyVo();
        SinhVien sinhVien = findById(id);
        Faculty faculty = restTemplate.getForObject("http://localhost:8000/api/faculty/"+sinhVien.getIdFaculty(), Faculty.class);
        studentAndFacultyVo.setFaculty(faculty);
        studentAndFacultyVo.setSinhVien(sinhVien);
        return studentAndFacultyVo;
    }
}
