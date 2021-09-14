package com.Springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Springboot.app.entity.User;

@Repository
public interface IUserRepositoryDao extends JpaRepository<User,Long> {
	
	

}
