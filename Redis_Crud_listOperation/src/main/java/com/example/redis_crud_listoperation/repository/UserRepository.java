package com.example.redis_crud_listoperation.repository;

import com.example.redis_crud_listoperation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private RedisTemplate redisTemplate;

    private ListOperations listOperations;

    public UserRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        listOperations = redisTemplate.opsForList();
    }

    public List<User> findAll(){
        long size = listOperations.size("USER");
        return listOperations.range("USER", 0, size);
    }

    public User findById(String id){
        int index =-1;
        List<User> list = findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)){
                index = i;
            }
        }
        if (index == -1){
            return null;
        }else {
            return (User) listOperations.index("USER", index);
        }
    }

    public User create(User user){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        user.setId(id);
        listOperations.rightPush("USER", user);
        return findById(user.getId());
    }

    public User update(User user, String id){
        int index = -1;
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                user.setId(id);
                index = i;
                listOperations.set("USER", index, user);
            }
        }
        if (index ==-1){
            return null;
        }else {
            return (User) listOperations.index("USER", index);
        }
    }

    public boolean deleteById(String id){
        User user = findById(id);
        if (user != null){
            listOperations.remove("USER", 1, user);
            return true;
        }
        return false;
    }

    public void deleteAll(){
        List<User> list = findAll();
        for (int i = 0; i < list.size(); i++) {
            listOperations.remove("USER", 1, list.get(i));
        }
    }
}
