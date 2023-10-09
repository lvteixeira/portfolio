package com.acme.toDoList.service;

import com.acme.toDoList.dto.UserDTO;
import com.acme.toDoList.model.UserEntity;
import com.acme.toDoList.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        TypeMap<UserDTO, UserEntity> typeMap = this.modelMapper.createTypeMap(UserDTO.class, UserEntity.class);

        typeMap.addMappings(mapper -> mapper.skip(UserEntity::setId));
    }

    public UserDTO convertEntityToDTO(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserEntity convertDTOToEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public List<UserDTO> convertEntityListToDTOList(List<UserEntity> entityList) {
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDTO>>() {}.getType();
        return modelMapper.map(entityList, targetListType);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    public void add(UserEntity user) {
        userRepository.save(user);
        userRepository.flush();
    }

    public void update(UserEntity updated, int id) {
        List<UserEntity> users = this.getAll();
        users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .ifPresent(user -> {
                    user.setName(updated.getName());
                    userRepository.flush();
                });
    }

    public void delete(int id) {
        Optional<UserEntity> userToDelete = this.findById(id);
        userToDelete.ifPresent(user -> userRepository.deleteById(id));
    }

    public void clear() {
        userRepository.deleteAll();
        userRepository.flush();
    }
}
