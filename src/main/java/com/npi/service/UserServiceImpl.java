package com.npi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.npi.entity.User;
import com.npi.entity.UserLogin;
import com.npi.exception.DuplicateUserFoundException;
import com.npi.exception.UserNotFoundException;
import com.npi.exception.WrongPasswordException;
import com.npi.repository.UserInfoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Set<String> setOfEmailIds = new HashSet<>();
	boolean flag;

	@Autowired
	private UserInfoRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	private String userNotFound = "User Not Found With Email Id --";

	@Override
	public String addUser(User userInfo) {

		List<User> userList = userRepository.findAll();

		if (null != userList) {
			for (User user : userList) {
				if (user.getUserEmailId().equals(userInfo.getUserEmailId()))
					flag = true;
				else
					flag = false;
			}
		}

		if (flag) {
			return "User already exists";
		} else {
			userInfo.setUserpassword(encoder.encode(userInfo.getUserpassword()));
			userRepository.save(userInfo);
			return "User saved";
		}
	}

	@Override
	public User login(@Valid UserLogin user) throws UserNotFoundException, WrongPasswordException {
		List<User> list = userRepository.findAll();
		Set<String> setOfEmailIds = new HashSet<>();

		for (User c : list) {
			setOfEmailIds.add(c.getUserEmailId());
		}

		if (!setOfEmailIds.contains(user.getEmailId())) {
			throw new UserNotFoundException(userNotFound + user.getEmailId());
		}

		Optional<User> usrOptional = userRepository.findByUserEmailId(user.getEmailId());

		if (usrOptional.isPresent()) {
			User usr = usrOptional.get();
			if (!usr.getUserpassword().equals(user.getPassword())) {
				throw new WrongPasswordException("Wrong password entered.");
			}
			return usr;
		} else {
			throw new UserNotFoundException("User not found for email: " + user.getEmailId());
		}
	}

	@Override
	public User getUserByEmailId(@Valid String userEmailId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findByUserEmailId(userEmailId);

		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new UserNotFoundException(userNotFound + userEmailId);
		}
	}

	@Override
	public String deleteUserByemailId(@Valid String userEmailId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findByUserEmailId(userEmailId);

		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException(userNotFound + userEmailId);
		}

		User user = optionalUser.get();
		userRepository.delete(user);
		return "User deleted with user email-Id: " + userEmailId;
	}

	@Override
	public User updateUserByEmailId(@Valid User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findByUserEmailId(user.getUserEmailId());

		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException(userNotFound + user.getUserEmailId());
		}

		User existingUser = optionalUser.get();
		existingUser.setUserName(user.getUserName());
		existingUser.setUserPhoneNo(user.getUserPhoneNo());
		existingUser.setUserpassword(user.getUserpassword());
		existingUser.setRole(user.getRole());

		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
