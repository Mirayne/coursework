package com.example.demo.control;

import com.example.demo.services.NiceService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AbstractController<T> {
    private NiceService<T> service;
    private final String name;

    public AbstractController(String name) {
        this.name = name;
    }

    //@GetMapping(name + "-list")
    public List<T> findSomething(){
        return service.findAll();
    }
}
