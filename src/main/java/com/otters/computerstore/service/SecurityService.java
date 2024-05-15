package com.otters.computerstore.service;

import com.otters.computerstore.component.staff.Role;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.staff.StaffRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService, UserDetailsService {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private PasswordEncoder encoder;

    public StaffEntity createUser(String username, String password, Role role) {
        StaffEntity user =  new StaffEntity("admin","0993843234", encoder.encode(password),  username, "3123");
        user.setRole(role);
        return user;
    }

    @Override
    @Transactional
    public void generateUsersRoles() {
//        StaffEntity user = createUser("admin", "1234", Role.ADMIN);
//        staffRepo.save(user);
//
//        staffRepo.flush();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
