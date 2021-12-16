package com.example.sinhvienservice.restcontroller;

import com.example.sinhvienservice.entity.SinhVien;
import com.example.sinhvienservice.entity.StudentAndFacultyVo;
import com.example.sinhvienservice.service.SinhVienService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sinhvien/")
public class SinhvienController {
    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/")
    @RateLimiter(name = "basic")
    public List<SinhVien> findAll(){
        return sinhVienService.findAll();
    }
    @GetMapping("/{id}")
    @RateLimiter(name = "basic")
    public SinhVien getById(@PathVariable(name = "id") Long id){
        return sinhVienService.findById(id);
    }
    @PostMapping("/")
    @RateLimiter(name = "basic")
    public SinhVien save(@RequestBody SinhVien sinhVien){
        return sinhVienService.save(sinhVien);
    }
    @DeleteMapping("/{id}")
    @RateLimiter(name = "basic")
    public SinhVien delete(@PathVariable(name = "id") Long id){
        return sinhVienService.delete(sinhVienService.findById(id));
    }
    @RateLimiter(name = "basic")
    @PutMapping("/")
    public SinhVien update(@RequestBody SinhVien sinhVien){
        return sinhVienService.save(sinhVien);
    }

    @GetMapping("/getVo/{id}")
    @RateLimiter(name = "basic")
    public StudentAndFacultyVo studentAndFacultyVo (@PathVariable(name = "id") Long id){
        return sinhVienService.studentAndFacultyVo(id);
    }

}
