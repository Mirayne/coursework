package com.example.demo.services;

import java.util.List;

public interface NiceService<T> {
    List<T> findAll();
    void deleteById(Integer id);
    T findById(Integer id);
    void save(T t);
}
