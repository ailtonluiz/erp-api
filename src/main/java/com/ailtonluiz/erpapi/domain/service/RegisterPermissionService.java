package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.exception.PermissionNotFoundException;
import com.ailtonluiz.erpapi.domain.model.Permission;
import com.ailtonluiz.erpapi.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterPermissionService {


    private static final String MESSAGE_PERMISSION_IN_USE
            = "Permission code %d cannot be removed as it is in use.";


    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Transactional
    public void delete(Long permissionId) {
        try {
            permissionRepository.deleteById(permissionId);
            permissionRepository.flush();


        } catch (EmptyResultDataAccessException e) {
            throw new PermissionNotFoundException(permissionId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_PERMISSION_IN_USE, permissionId));
        }
    }

    public Permission searchOrFail(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }
}