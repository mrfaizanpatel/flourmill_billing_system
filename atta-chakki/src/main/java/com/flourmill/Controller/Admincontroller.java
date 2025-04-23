package com.flourmill.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flourmill.Entity.Admin;
import com.flourmill.Service.Adminservice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class Admincontroller {
	@Autowired
	private Adminservice adminservice;
	
	//just testing project
@GetMapping("/test")
	public String test() {
		return "this is testing";
	}

//postdata into database

@PostMapping("/users")
public ResponseEntity<Admin> createUser(@RequestBody Admin user) {
    return ResponseEntity.ok(adminservice.savedata(user));
}

//for login of  admin
@PostMapping("/login")
public ResponseEntity<?>loginadmin(@RequestBody Admin admin){
	Optional<Admin>existingadmin=adminservice.loginadmin(admin.getUsername(), admin.getPassword());
	
	if(existingadmin.isPresent()) {
		return ResponseEntity.ok("login successfull");
		}else {
			return ResponseEntity.status(401).body("invalid username and password");
		}
}
@PostMapping("/mylogin")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String username = credentials.get("username");
    String password = credentials.get("password");

    Admin admin = adminservice.login(username, password);
    if (admin != null) {
        return ResponseEntity.ok(admin); // You can return token or member data
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or inactive user");
    }
}
@PostMapping("/adminsignup")
public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody Admin admin) {
//	admin.setRole("User");
//    memberRepository.save(member);
	adminservice.savedata(admin); // delegate to service

    
    Map<String, String> response = new HashMap();
    response.put("message", "Admin registered successfully");

    return ResponseEntity.ok(response);
}//to get all users("ADMIN" & "appllication user") 
@GetMapping("/getallapplicationusers")
public List<Admin>getallusers(){
	return adminservice.getalluser();
}

}
