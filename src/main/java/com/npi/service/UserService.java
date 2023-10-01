package com.npi.service;

import java.util.List;

import com.npi.entity.User;
import com.npi.entity.UserLogin;
import com.npi.exception.DuplicateUserFoundException;
import com.npi.exception.UserNotFoundException;
import com.npi.exception.WrongPasswordException;

import jakarta.validation.Valid;


public interface UserService {

	

//	public User registerUser(@Valid User user) throws DuplicateUserFoundException;

	public User login(@Valid UserLogin user) throws UserNotFoundException,WrongPasswordException;

	public User getUserByEmailId(@Valid String userEmailId) throws UserNotFoundException;

	public String deleteUserByemailId(@Valid String userEmailId) throws UserNotFoundException;

	public User updateUserByEmailId(@Valid User user) throws UserNotFoundException;

	public List<User> getAllUsers();

	public String addUser(User userInfo);//throws DuplicateUserFoundException;


	

}
//public User registerUser(UserDTO user) throws DuplicateUserFoundException
