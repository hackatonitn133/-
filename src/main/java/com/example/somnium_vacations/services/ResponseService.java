package com.example.somnium_vacations.services;

import com.example.somnium_vacations.models.ResponseModel;
import com.example.somnium_vacations.repositories.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponsesRepository repository;

    public ResponseModel findResponseById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<ResponseModel> findAllResponses(String keyword) {

        return repository.findAll();
    }

}
