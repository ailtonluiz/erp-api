package com.ailtonluiz.erpapi.api.disassembler;

import com.ailtonluiz.erpapi.api.model.input.GroupInput;
import com.ailtonluiz.erpapi.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GroupInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Group toDomainObject(GroupInput groupInput) {
        return modelMapper.map(groupInput, Group.class);
    }

    public void copyToDomainObject(GroupInput groupInput, Group group) {
        modelMapper.map(groupInput, group);
    }
}