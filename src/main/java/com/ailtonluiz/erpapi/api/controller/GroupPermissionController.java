package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.PermissionModelAssembler;
import com.ailtonluiz.erpapi.api.model.PermissionModel;
import com.ailtonluiz.erpapi.domain.model.Group;
import com.ailtonluiz.erpapi.domain.service.RegisterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {


    @Autowired
    private RegisterGroupService registerGroupService;


    @Autowired
    private PermissionModelAssembler permissionModelAssembler;


    @GetMapping
    public List<PermissionModel> list(@PathVariable Long groupId) {
        Group group = registerGroupService.searchOrFail(groupId);

        return permissionModelAssembler.toCollectionModel(group.getPermissions());
    }

    @DeleteMapping("/{permissonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        registerGroupService.disassociatePermission(groupId, permissionId);
    }

    @PutMapping("/{permissonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        registerGroupService.associatePermission(groupId, permissionId);
    }
}