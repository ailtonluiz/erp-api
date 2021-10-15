package com.ailtonluiz.erpapi.api.disassembler;

import com.ailtonluiz.erpapi.api.model.input.StateInput;
import com.ailtonluiz.erpapi.api.model.input.UnitInput;
import com.ailtonluiz.erpapi.domain.model.State;
import com.ailtonluiz.erpapi.domain.model.Unit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UnitInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Unit toDomainObject(UnitInput unitInput) {
        return modelMapper.map(unitInput, Unit.class);
    }

    public void copyToDomainObject(UnitInput unitInput, Unit unit) {
        modelMapper.map(unitInput, unit);
    }

}