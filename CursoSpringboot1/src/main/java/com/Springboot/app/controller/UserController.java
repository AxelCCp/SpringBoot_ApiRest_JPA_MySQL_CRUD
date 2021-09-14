package com.Springboot.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.app.entity.User;
import com.Springboot.app.service.IUserService;

// @RestController : CONTROLLER(DEFINIMOS QUE ES EL CONTROLADOR) REST(INDICAMOS QUE EL VALOR DE RETORNO DE LOS MÉTODOS SERÁ UN JSON)
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//CREAR NUEVO USUARIO
	//@RequestBody : RECIBIMOS EL EN CUERPO DE LA PETICIÓN UN USUARIO
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.save(user));
	}
	
	//LEER UN USUARIO
	// @PathVariable(value="id") : LE DECIMOS QUE EL ID QUE RECIBA POR GETMAPPING SE ALMACENARÁ EN Long userId
	@GetMapping("/{id}")  //LEE AL USUARIO CON ID VARIABLE{}
	public ResponseEntity <?> read(@PathVariable(value="id") Long userId){
		Optional<User> oUser = iUserService.findById(userId);
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build(); //DEVUELVE ERROR 404
		}
		return ResponseEntity.ok(oUser); //CON OK DICE Q TODO ESTÁ BN Y DEVUELVE EL USUARIO
	}
	
	
	//UPDATE AN USER
	//@RequestBody User userDetails : LE PASAMOS EN EL CUERPO DE LA PETICIÓN UN USUARIO QUE SERÁ EL QUE TENDRÁ LOS DATOS NUEVOS QUE QUEREMOS MODIFICAR.
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value="id") Long userId){
		Optional<User> user = iUserService.findById(userId);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		//ACTUALIZAMOS AL USUARIO 
		//user.get() : OBTENEMOS AL USUARIO ... Y LOS LOS SET() LO ACTUALIZAMOS
		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnabled(userDetails.getEnabled());
		
		
		//HttpStatus.CREATED : DEVULEVE ESTADO 201 DE CREADO, OSEA Q ESTÁ BN.
		//body(iUserService.save(user.get())); : CONSTRUIMOS EL CUERPO Y GUARDAMOS AL USUARIO EN A BBDD.
		return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.save(user.get()));
	}
	
	
	//DELETE AN USER
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value="id")Long userId){
		if(!iUserService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		iUserService.deleteById(userId);
		return ResponseEntity.ok().build();
	}
	
	// Read all Users
	@GetMapping
	public List<User> readAll(){
		
		//StreamSupport.stream(iUserService.findAll().spliterator() : PARA CONSTRUIR LA LISTA.
		//FALSE : LE DECIMOS QUE LA LISTA DEBE SER SECUENCIAL.
		//collect(Collectors.toList()); : COMBIERTE LA COLECCIÓN EN UNA LISTA.
		
		List<User> users = StreamSupport.stream(iUserService.findAll().spliterator(), false).collect(Collectors.toList());
		
		return users;
	}
	
	
	
	@Autowired
	private IUserService iUserService;

}
