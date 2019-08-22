package com.alvyl.springthymeleaf.service;

import com.alvyl.springthymeleaf.model.Role;
import com.alvyl.springthymeleaf.model.User;
import com.alvyl.springthymeleaf.repository.RoleRepository;
import com.alvyl.springthymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User registerUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User newUser = userRepository.saveAndFlush(user);
        Role role = new Role();
        role.setRole("ROLE_USER");
        role.setUsername(user.getUsername());
        roleRepository.save(role);
        return newUser;
    }

    public User fetchUser(String userName){
        return userRepository.findByUsername(userName);
    }
    public User updateUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User result = userRepository.saveAndFlush(user);
        return result;
    }
}
