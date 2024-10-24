package com.example.ToDo.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.dto.converter.UserDTOToUser;
import com.example.ToDo.dto.converter.UserToUserDTO;
import com.example.ToDo.dto.converter.UserToUserDTOEdit;

import com.example.ToDo.entity.UserEntity;
import com.example.ToDo.repository.UserRepository;
import com.example.ToDo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserToUserDTOEdit userToUserDTOEdit;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserToUserDTO userToUserDTO;
    private final UserDTOToUser userDTOToUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserToUserDTOEdit userToUserDTOEdit, UserRepository userRepository, ModelMapper modelMapper,
                           UserToUserDTO userToUserDTO, UserDTOToUser userDTOToUser, PasswordEncoder passwordEncoder) {
        this.userToUserDTOEdit = userToUserDTOEdit;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userToUserDTO = userToUserDTO;
        this.userDTOToUser = userDTOToUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO findById(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        System.out.println(" user 5" + userDTO);
        UserEntity user = userDTOToUser.convert(userDTO);
        System.out.println(" user 4" + user);
        if (user != null) {
            System.out.println(" user 46" + user);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return userToUserDTO.convert(userRepository.save(user));
        }
        throw new RuntimeException("User conversion failed");
    }

    @Override
    public void deleteById(String id) {
        // Implement delete logic if needed
    }

    @Override
    public UserDTO update(UserDTO object) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserEntity userOptional = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return userToUserDTO.convert(userOptional);
    }

    public UserDTO edit(UserDTOEdit userDTOEdit) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userOptional = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(userDTOEdit.getOldPassword(), userOptional.getPassword())) {
            if (userDTOEdit.getNewPassword().equals(userDTOEdit.getNewPassword2())) {
                userOptional.setPassword(passwordEncoder.encode(userDTOEdit.getNewPassword()));
                return userToUserDTO.convert(userRepository.save(userOptional));
            }
        }

        return null;
    }
}