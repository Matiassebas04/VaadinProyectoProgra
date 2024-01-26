package com.example.application.services;

import com.example.application.metodos.Cliente;
import com.example.application.views.cliente.ClienteView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends MongoRepository <Cliente, String> {

}
