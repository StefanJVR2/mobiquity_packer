package com.mobiquityinc.controllers;

import com.mobiquityinc.domain.models.BestFitResult;
import com.mobiquityinc.domain.models.SingleBestFitRequest;
import com.mobiquityinc.exception.APIException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Packer {

    @PostMapping("/pack")
    public BestFitResult pack(@RequestBody SingleBestFitRequest request) throws APIException {
        BestFitResult result = new BestFitResult();
        result.setResult(com.mobiquityinc.packer.Packer.pack(request));
        return result;
    }

    @PostMapping("/packs")
    public BestFitResult packMultiple(@RequestBody SingleBestFitRequest[] request) throws APIException {
        BestFitResult result = new BestFitResult();

        result.setResult(com.mobiquityinc.packer.Packer.pack(request));

        return result;
    }
}
