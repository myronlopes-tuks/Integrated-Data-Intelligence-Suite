package com.Visualize_Service.Visualize_Service.service;

import com.Visualize_Service.Visualize_Service.exception.InvalidRequestException;
import com.Visualize_Service.Visualize_Service.request.VisualizeDataRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class VisualizeServiceTest {
    @InjectMocks
    private VisualizeServiceImpl service ;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Dummy test")
    public void dummyTest(){
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("When findTrendsNullRequest is Null")
    public void visualizeDataNullRequest(){
        Assertions.assertThrows(InvalidRequestException.class, () -> service.visualizeData(null));
    }

}
