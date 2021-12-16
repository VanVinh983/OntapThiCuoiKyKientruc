package com.example.redis_crud_listoperation.controller;

import com.example.redis_crud_listoperation.entity.User;
import com.example.redis_crud_listoperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") String id) {
        return userRepository.findById(id);
    }
    @PostMapping("/")
    public User create(@RequestBody User user){
        return userRepository.create(user);
    }
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable(name = "id") String id){
        return userRepository.update(user, id);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") String id){
        boolean kq = userRepository.deleteById(id);
        if (kq == true){
            return "delete success";
        }else {
            return "delete fail";
        }
    }
    @DeleteMapping("/")
    public String deleteAll(){
        userRepository.deleteAll();
        return "da xoa tat ca";
    }
}
