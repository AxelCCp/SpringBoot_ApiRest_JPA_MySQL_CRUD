package com.Springboot.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Springboot.app.dao.IUserRepositoryDao;
import com.Springboot.app.entity.User;


@Service
public class UserServiceImpl implements IUserService {

	@Transactional(readOnly=true)
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return iUserRepoDao.findAll();
	}

	@Transactional(readOnly=true)
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return iUserRepoDao.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return iUserRepoDao.findById(id);
	}

	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		return iUserRepoDao.save(user);
	}

	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		iUserRepoDao.deleteById(id);
	}
	
	
	@Autowired
	private IUserRepositoryDao iUserRepoDao;
	

}
