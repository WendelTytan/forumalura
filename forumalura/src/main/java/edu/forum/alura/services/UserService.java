package edu.forum.alura.services;

import edu.forum.alura.controllers.dtos.UserRequestDto;
import edu.forum.alura.controllers.dtos.UserResponseDto;
import edu.forum.alura.exceptions.NotFoundException;
import edu.forum.alura.exceptions.UserNotFoundException;
import edu.forum.alura.models.entities.UserEntity;
import edu.forum.alura.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto user) {
        UserEntity newUser = new UserEntity(user);
        userRepository.findByEmail(newUser.getUsername())
                .ifPresent(e -> {
                    throw new IllegalArgumentException("User already exists");
                });
        String hashedPassword =
                new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
        return new UserResponseDto(newUser);
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(() -> new NotFoundException("User " + id + " not" +
                        " found"));
    }

    public UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));
    }
}
