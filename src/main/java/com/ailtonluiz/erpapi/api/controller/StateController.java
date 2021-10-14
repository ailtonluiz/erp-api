package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.StateModelAssembler;
import com.ailtonluiz.erpapi.api.disassembler.StateInputDisassembler;
import com.ailtonluiz.erpapi.api.model.StateModel;
import com.ailtonluiz.erpapi.api.model.input.StateInput;
import com.ailtonluiz.erpapi.domain.model.State;
import com.ailtonluiz.erpapi.domain.repository.StateRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerStateService;

    @Autowired
    private StateModelAssembler stateModelAssembler;

    @Autowired
    private StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public List<StateModel> list() {
        List<State> allStates = stateRepository.findAll();

        return stateModelAssembler.toCollectionModel(allStates);
    }

    @GetMapping("/{stateId}")
    public StateModel search(@PathVariable Long stateId) {
        State state = registerStateService.searchOrFail(stateId);

        return stateModelAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel add(@RequestBody @Valid StateInput stateInput) {
        State state = stateInputDisassembler.toDomainObject(stateInput);

        state = registerStateService.save(state);

        return stateModelAssembler.toModel(state);
    }

    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId,
                             @RequestBody @Valid StateInput stateInput) {
        State currentState = registerStateService.searchOrFail(stateId);

        stateInputDisassembler.copyToDomainObject(stateInput, currentState);

        currentState = registerStateService.save(currentState);

        return stateModelAssembler.toModel(currentState);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long stateId) {
        registerStateService.delete(stateId);
    }

}