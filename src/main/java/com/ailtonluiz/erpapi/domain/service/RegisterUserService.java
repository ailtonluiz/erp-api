package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.exception.TransactionException;
import com.ailtonluiz.erpapi.domain.exception.UserNotFoundException;
import com.ailtonluiz.erpapi.domain.model.Group;
import com.ailtonluiz.erpapi.domain.model.User;
import com.ailtonluiz.erpapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegisterUserService {

    private static final String MESSAGE_USER_IN_USE
            = "User code %d cannot be removed as it is in use.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterGroupService registerGroupService;


    @Transactional
    public User save(User user) {
        userRepository.detach(user);
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new TransactionException(
                    String.format("Já existe um usuário cadastrado com esse e-mail %s", user.getEmail()));
        }


        return userRepository.save(user);
    }


    @Transactional
    public void remove(Long userId) {
        try {
            userRepository.deleteById(userId);
            userRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(userId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MESSAGE_USER_IN_USE, userId));

        }
    }

    @Transactional
    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = searchOrFail(userId);

        if (user.passwordNotMatchesWith(currentPassword)) {
            throw new TransactionException("Senha atual informada não coincide com a senha do usuário.");
        }
        user.setPassword(newPassword);
    }


    public User searchOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }


    @Transactional
    public void disassociateGroup(Long userId, Long groupId) {
        User user = searchOrFail(userId);
        Group group = registerGroupService.searchOrFail(groupId);

        user.removeGroup(group);
    }

    @Transactional
    public void associateGroup(Long userId, Long groupId) {
        User user = searchOrFail(userId);
        Group group = registerGroupService.searchOrFail(groupId);

        user.addGroup(group);
    }


}