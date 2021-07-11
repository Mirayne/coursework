package com.example.demo.services;

import com.example.demo.entities.UsersTable;
import com.example.demo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersTable> findAll(){
        return usersRepository.findAll();
    }

    public void deleteUser(Integer id){
        usersRepository.deleteById(id);
    }

    public void saveUser(UsersTable usersTable) {
        usersTable.setPassword(passwordEncoder().encode(usersTable.getPassword()));
        usersRepository.save(usersTable);
    }

    public UsersTable getUserById(Integer id) {
        return usersRepository.getOne(id);
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
