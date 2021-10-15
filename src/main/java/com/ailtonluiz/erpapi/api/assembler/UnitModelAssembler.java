package com.ailtonluiz.erpapi.api.assembler;


import com.ailtonluiz.erpapi.api.model.UnitModel;
import com.ailtonluiz.erpapi.domain.model.Unit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnitModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UnitModel toModel(Unit unit) {
        return modelMapper.map(unit, UnitModel.class);
    }

    public List<UnitModel> toCollectionModel(List<Unit> units) {
        return units.stream()
                .map(unit -> toModel(unit))
                .collect(Collectors.toList());
    }

}