package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.UnitModelAssembler;
import com.ailtonluiz.erpapi.api.disassembler.UnitInputDisassembler;
import com.ailtonluiz.erpapi.api.model.UnitModel;
import com.ailtonluiz.erpapi.api.model.input.UnitInput;
import com.ailtonluiz.erpapi.domain.model.Unit;
import com.ailtonluiz.erpapi.domain.repository.UnitRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private RegisterUnitService registerUnitService;

    @Autowired
    private UnitModelAssembler unitModelAssembler;

    @Autowired
    private UnitInputDisassembler unitInputDisassembler;

    @GetMapping
    public List<UnitModel> list() {
        List<Unit> allUnits = unitRepository.findAll();

        return unitModelAssembler.toCollectionModel(allUnits);
    }

    @GetMapping("/{unitId}")
    public UnitModel search(@PathVariable Long unitId) {
        Unit unit = registerUnitService.searchOrFail(unitId);

        return unitModelAssembler.toModel(unit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnitModel add(@RequestBody @Valid UnitInput unitInput) {
        Unit unit = unitInputDisassembler.toDomainObject(unitInput);

        unit = registerUnitService.save(unit);

        return unitModelAssembler.toModel(unit);
    }

    @PutMapping("/{unitId}")
    public UnitModel update(@PathVariable Long unitId,
                             @RequestBody @Valid UnitInput unitInput) {
        Unit currentUnit = registerUnitService.searchOrFail(unitId);

        unitInputDisassembler.copyToDomainObject(unitInput, currentUnit);

        currentUnit = registerUnitService.save(currentUnit);

        return unitModelAssembler.toModel(currentUnit);
    }

    @DeleteMapping("/{unitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long unitId) {
        registerUnitService.delete(unitId);
    }

}