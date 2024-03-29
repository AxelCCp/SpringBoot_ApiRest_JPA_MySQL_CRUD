package com.Springboot.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Springboot.app.entity.User;

public interface IUserService {
	
	
	//METODO QUE NOS DEVUELVE COLECCIÓN EN FORMA DE ITERABLE
	public Iterable<User>findAll();
	//
	public Page<User>findAll(Pageable pageable);
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);

}
