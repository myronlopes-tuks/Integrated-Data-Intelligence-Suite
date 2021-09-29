package com.Analyse_Service.Analyse_Service.controller;

import com.Analyse_Service.Analyse_Service.exception.AnalyzerException;
import com.Analyse_Service.Analyse_Service.exception.InvalidRequestException;
import com.Analyse_Service.Analyse_Service.request.*;
import com.Analyse_Service.Analyse_Service.response.*;
import com.Analyse_Service.Analyse_Service.service.AnalyseServiceImpl;
import com.Analyse_Service.Analyse_Service.service.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/Analyse")
public class AnalyseServiceController {

    @Autowired
    private AnalyseServiceImpl analyseService;

    @Autowired
    private TrainServiceImpl trainService;

    /**
     * This method is used to facilitate communication to the Analyse-Service.
     * @param request This is a request entity which contains a AnalyseDataRequest object.
     * @return AnalyseDataResponse This object contains analysed data which has been processed by Analyse-Service.
     * @throws Exception This is thrown if exception caught in Analyse-Service.
     */
    @PostMapping("/analyzeData")
    public @ResponseBody AnalyseDataResponse analyzeData(@RequestBody AnalyseDataRequest request) throws AnalyzerException {
        //AnalyseDataRequest request = getBody();

        if (request == null) {
            throw new InvalidRequestException("AnalyzeDataRequest Object is null");
        }

        if (request.getDataList() == null){
            throw new InvalidRequestException("AnalyseData DataList is null");
        }

        return analyseService.analyzeData(request);
    }


    /**
     * This method is used to facilitate communication to the Train-Service.
     * @param request This is a request entity which contains a TrainModelRequest object.
     * @return TrainModelResponse This object contains trained data which has been processed by Train-Service.
     * @throws Exception This is thrown if exception caught in Train-Service.
     */
    @PostMapping("/trainModel")
    public @ResponseBody
    TrainUserModelResponse trainModel(@RequestBody TrainUserModelRequest request) throws AnalyzerException {
        //AnalyseDataRequest request = getBody();
        if (request == null) {
            throw new InvalidRequestException("TrainModelRequest Object is null");
        }

        if (request.getDataList() == null){
            throw new InvalidRequestException("TrainModel DataList is null");
        }

        if (request.getModelName() == null){
            throw new InvalidRequestException("Model Name is null");
        }

        return trainService.trainUserModel(request);
    }


    @GetMapping("/trainData")
    public boolean trainData() {
        //AnalyseDataRequest request = getBody();
        try {
            trainService.trainApplicationModel();
            return true;
        } catch (AnalyzerException e){
            e.printStackTrace();
            return false;
        }
    }




    /*@PostMapping("/analyzeData")
    public AnalyseDataResponse analyzeData(RequestEntity<AnalyseDataRequest> requestEntity) throws Exception{
        AnalyseDataRequest request = requestEntity.getBody();
        return service.analyzeData(request);
    }*/


}
