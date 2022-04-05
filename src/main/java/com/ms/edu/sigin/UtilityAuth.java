package com.ms.edu.sigin;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.edu.model.Profile;
import com.ms.edu.model.User;


@Service
public class UtilityAuth {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProfileRepository profileRepo;

	public UtilityAuth() {}
	
	public Profile insertUser(LinkedHashMap<String, String> obj) {
		Hmac512PasswordEncoder hash = new Hmac512PasswordEncoder("SPwQje7UnwOhFfdZ7fsahXVsGE081n5vvbRdcOCBYZX5rI5V/ey7Se2waI/hYHDvXvPx4+3NdcuRITusOsHPgQ==");
		User user = new User(obj.get("email"),hash.encode(obj.get("password")),"ADMIN");
		Profile profile = new Profile(obj.get("email"),obj.get("name"),obj.get("surname"),obj.get("phone"));
		userRepo.saveUser(user.getEmail(),user.getPassword(),user.getRole());
		profileRepo.saveProfile(user.getEmail(),profile.getName(),profile.getPhone(),profile.getSurname());
		return profile;
		
	}

}
