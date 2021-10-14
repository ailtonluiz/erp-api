package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.PaymentMethodModelAssembler;
import com.ailtonluiz.erpapi.api.disassembler.PaymentMethodInputDisassembler;
import com.ailtonluiz.erpapi.api.model.PaymentMethodModel;
import com.ailtonluiz.erpapi.api.model.input.PaymentMethodInput;
import com.ailtonluiz.erpapi.domain.exception.EntityNotFoundException;
import com.ailtonluiz.erpapi.domain.exception.PaymentMethodNotFoundException;
import com.ailtonluiz.erpapi.domain.exception.TransactionException;
import com.ailtonluiz.erpapi.domain.model.PaymentMethod;
import com.ailtonluiz.erpapi.domain.repository.PaymentMethodRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterPaymentMethodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/paymentMethods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private RegisterPaymentMethodService registerPaymentMethodService;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Autowired
    private PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime endUpdateDate = paymentMethodRepository.getEndUpdateDate();

        if (endUpdateDate != null) {
            eTag = String.valueOf(endUpdateDate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<PaymentMethod> allPaymentMethods = paymentMethodRepository.findAll();

        List<PaymentMethodModel> paymentMethodModels = paymentMethodModelAssembler
                .toCollectionModel(allPaymentMethods);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodModels);
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodModel> search(@PathVariable Long paymentMethodId,
                                                     ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime updateDate = paymentMethodRepository
                .getEndUpdateDateById(paymentMethodId);

        if (updateDate != null) {
            eTag = String.valueOf(updateDate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        PaymentMethod paymentMethod = registerPaymentMethodService.searchOrFail(paymentMethodId);

        PaymentMethodModel paymentMethodModel = paymentMethodModelAssembler.toModel(paymentMethod);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentMethodModel);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModel add(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {

        try {
            PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);

            paymentMethod = registerPaymentMethodService.save(paymentMethod);


            return paymentMethodModelAssembler.toModel(paymentMethod);
        } catch (EntityNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModel update(@PathVariable Long paymentMethodId,
                                     @RequestBody @Valid PaymentMethodInput paymentMethodInput) {

        try {


            PaymentMethod currentPaymentMethod = registerPaymentMethodService.searchOrFail(paymentMethodId);

            paymentMethodInputDisassembler.copyToDomainObject(paymentMethodInput, currentPaymentMethod);

            currentPaymentMethod = registerPaymentMethodService.save(currentPaymentMethod);

            return paymentMethodModelAssembler.toModel(currentPaymentMethod);


        } catch (PaymentMethodNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long paymentMethodId) {

        registerPaymentMethodService.delete(paymentMethodId);
    }

}