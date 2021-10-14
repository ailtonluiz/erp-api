package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.domain.exception.PermissionNotFoundException;
import com.ailtonluiz.erpapi.domain.exception.TransactionException;
import com.ailtonluiz.erpapi.domain.model.Permission;
import com.ailtonluiz.erpapi.domain.repository.PermissionRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RegisterPermissionService registerPermissionService;


    @GetMapping
    public List<Permission> list() {
        return permissionRepository.findAll();
    }

    @GetMapping("/{permissionId}")
    public Permission search(@PathVariable Long permissionId) {

        return registerPermissionService.searchOrFail(permissionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permission add(@RequestBody Permission permission) {
        try {
            return registerPermissionService.save(permission);

        } catch (PermissionNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{permissionId}")
    public Permission update(@PathVariable Long permissionId,
                             @RequestBody Permission permission) {
        Permission currentPermission = registerPermissionService.searchOrFail(permissionId);

        BeanUtils.copyProperties(permission, currentPermission, "id");
        try {
            return registerPermissionService.save(currentPermission);
        } catch (PermissionNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }


    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long permissionId) {

        registerPermissionService.delete(permissionId);
    }


}