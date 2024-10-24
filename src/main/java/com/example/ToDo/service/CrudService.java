package com.example.ToDo.service;

import java.util.List;


public interface CrudService<T, I>{

    T findById(I id);
    List<T>findAll();
    T add(T object);
    void deleteById(I id);
    T update(T object);



}
