package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.GroupModelAssembler;
import com.ailtonluiz.erpapi.api.model.GroupModel;
import com.ailtonluiz.erpapi.domain.model.User;
import com.ailtonluiz.erpapi.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @GetMapping
    public List<GroupModel> list(@PathVariable Long userId) {
        User user = registerUserService.searchOrFail(userId);

        return groupModelAssembler.toCollectionModel(user.getGroups());
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        registerUserService.disassociateGroup(userId, groupId);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        registerUserService.associateGroup(userId, groupId);
    }
}