package com.example.demo.user;

import java.util.List;

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

@RestController
@RequestMapping("/customer")
public class userController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List <Customer>> getAllUsers() {
		List<Customer> users = userservice.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
		Customer savedUser = userservice.createOrUpdateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getUserById(@PathVariable int id){
		Customer user = userservice.getUserById(id);
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public Customer upadteUser(@PathVariable int id,@RequestBody Customer user1)
	{
		return userservice.updateUserPassword(id,user1);
	}
	
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable int id) {
		try {
			userservice.deleteUserById(id);
			return "Deleted the respective ID record";
		}
		catch(RuntimeException e) {
			return "Unable to delete the respective ID record- ID not found or some other error.";
		}
	}
	
}
