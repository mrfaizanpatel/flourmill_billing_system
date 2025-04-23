package com.flourmill.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flourmill.Entity.Admin;
import com.flourmill.Repository.Adminrepo;

@Service
public class Adminservice {

	@Autowired
	private Adminrepo adminrepo;
	
	//to post data into database logic
	public Admin savedata(Admin admin){
		return adminrepo.save(admin);
	}
	
	//code for login 
	public Optional<Admin>loginadmin(String username,String password){
//		return adminrepo.findByNameAndPassword(name, password);
           return adminrepo.findByUsernameAndPassword(username, password);
	}
	
	public Admin login(String username, String password) {
	    Optional<Admin> optionalMember = adminrepo.findByUsername(username);
	    if (optionalMember.isPresent()) {
	        Admin member = optionalMember.get();
	        // ✅ Check both password and status
	        if (member.getPassword().equals(password)) { // optionally add status check here
	            return member;
	        }
	    }
	    return null;
	}
public List<Admin>getalluser(){
	return adminrepo.findAll();
}
	
}
