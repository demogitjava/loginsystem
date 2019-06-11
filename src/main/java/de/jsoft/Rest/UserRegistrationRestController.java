package de.jsoft.Rest;

import de.jsoft.Exception.CustomErrorType;
import de.jsoft.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ravi Kant Soni
 */
@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	//private UserJpaRepository userJpaRepository;

	@Autowired
	de.jsoft.dao.LoadUsersToMemory loadusers;

	//@Autowired
	//public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
	//	this.userJpaRepository = userJpaRepository;
	//}

	@GetMapping("/")
	public ResponseEntity<List<Users>> listAllUsers() {
		logger.info("Fetching all users");
		List<Users> users = loadusers.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") final int id) {
		logger.info("Fetching User with id {}", id);
		Users user = loadusers.getItembyId(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<Users>((MultiValueMap<String, String>) new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	/*

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody final UsersDTO user)
	{
		logger.info("Creating User : {}", user);
		if (userJpaRepository.findByName(user.getName()) != null) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UsersDTO user) {
		logger.info("Updating User with id {}", id);
		UsersDTO currentUser = userJpaRepository.findById(id);
		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		userJpaRepository.saveAndFlush(currentUser);
		return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
		logger.info("Deleting User with id {}", id);
		UsersDTO user = userJpaRepository.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userJpaRepository.delete(id);
		return new ResponseEntity<UsersDTO>(new CustomErrorType("Deleted User with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}
		*/
}
