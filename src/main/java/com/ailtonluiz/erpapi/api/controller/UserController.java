package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.UserModelAssembler;
import com.ailtonluiz.erpapi.api.disassembler.UserInputDisassembler;
import com.ailtonluiz.erpapi.api.model.UserModel;
import com.ailtonluiz.erpapi.api.model.input.PasswordInput;
import com.ailtonluiz.erpapi.api.model.input.UserInput;
import com.ailtonluiz.erpapi.api.model.input.UserWithPasswordInput;
import com.ailtonluiz.erpapi.domain.model.User;
import com.ailtonluiz.erpapi.domain.repository.UserRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @GetMapping
    public List<UserModel> list() {
        List<User> allUsers = userRepository.findAll();
        return userModelAssembler.toCollectionModel(allUsers);
    }

    @GetMapping("/{userId}")
    public UserModel search(@PathVariable Long userId) {
        User user = registerUserService.searchOrFail(userId);

        return userModelAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userInputDisassembler.toDomainObject(userWithPasswordInput);
        user = registerUserService.save(user);

        return userModelAssembler.toModel(user);
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId,
                            @RequestBody @Valid UserInput userInput) {
        User currentUser = registerUserService.searchOrFail(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = registerUserService.save(currentUser);

        return userModelAssembler.toModel(currentUser);
    }

    @PutMapping("{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        registerUserService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }

}