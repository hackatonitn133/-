package com.example.somnium_vacations.services.users;

import com.example.somnium_vacations.models.UserModel;
import com.example.somnium_vacations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> user = Optional.ofNullable(repository.findByLogin(login));
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user with login - " + login + " not found!"));
    }

    public UserModel findUserById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<UserModel> findAllUsers() {
        return repository.findAll();
    }

    public UserModel findUserByPrincipal(Principal principal) {

        if(principal == null) {
            return null;
        }

        return repository.findByLogin(principal.getName());
    }

    public boolean createUser(UserModel user) {
        String login = user.getLogin();
        if(repository.findByLogin(login) != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        repository.save(user);

        return true;
    }
    public boolean createUserMail(UserModel user) {
        String email = user.getEmail();
        if(repository.findByLogin(email) != null) {
            return false;
        }
        user.setRoles("ROLE_USER");
        repository.save(user);

        return true;
    }

    public void saveUser(UserModel user) {
        repository.save(user);
    }

}
