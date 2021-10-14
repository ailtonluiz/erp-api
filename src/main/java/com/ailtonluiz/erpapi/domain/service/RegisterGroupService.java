package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.exception.GroupNotFoundException;
import com.ailtonluiz.erpapi.domain.model.Group;
import com.ailtonluiz.erpapi.domain.model.Permission;
import com.ailtonluiz.erpapi.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterGroupService {

    private static final String MESSAGE_GROUP_IN_USE
            = "Code group %d cannot be removed as it is in use.";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RegisterPermissionService registerPermissionService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long groupId) {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MESSAGE_GROUP_IN_USE, groupId));

        }
    }

    public Group searchOrFail(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    @Transactional
    public void disassociatePermission(Long groupId, Long permissionId) {
        Group group = searchOrFail(groupId);
        Permission permission = registerPermissionService.searchOrFail(permissionId);

        group.removePermission(permission);
    }
    @Transactional
    public void associatePermission(Long groupId, Long permissionId) {
        Group group = searchOrFail(groupId);
        Permission permission = registerPermissionService.searchOrFail(permissionId);

        group.addPermission(permission);
    }


}