package com.Gateway_Service.Gateway_Service.controller;



import com.Gateway_Service.Gateway_Service.dataclass.analyse.AnalyseDataRequest;
import com.Gateway_Service.Gateway_Service.dataclass.analyse.AnalyseDataResponse;
import com.Gateway_Service.Gateway_Service.dataclass.impor.*;
import com.Gateway_Service.Gateway_Service.dataclass.user.GetUserRequest;
import com.Gateway_Service.Gateway_Service.dataclass.user.GetUserResponse;
import com.Gateway_Service.Gateway_Service.dataclass.parse.*;
import com.Gateway_Service.Gateway_Service.dataclass.user.*;
import com.Gateway_Service.Gateway_Service.dataclass.visualize.VisualizeDataRequest;
import com.Gateway_Service.Gateway_Service.dataclass.visualize.VisualizeDataResponse;
import com.Gateway_Service.Gateway_Service.rri.DataSource;
import com.Gateway_Service.Gateway_Service.service.AnalyseService;
import com.Gateway_Service.Gateway_Service.service.ImportService;
import com.Gateway_Service.Gateway_Service.service.ParseService;


//import com.netflix.discovery.DiscoveryClient;

import com.Gateway_Service.Gateway_Service.service.VisualizeService;

import com.Gateway_Service.Gateway_Service.service.UserService;

import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/")
public class GatewayServiceController {

    @Autowired
    private ImportService importClient;

    @Autowired
    private ParseService parseClient;

    @Autowired
    private AnalyseService analyseClient;

    @Autowired
    private VisualizeService visualizeClient;

    @Autowired
    private DiscoveryClient discoveryClient;
  
    @Autowired
    private UserService userClient;

    //@Autowired
    //private RestTemplate restTemplate;



    /**
     * This method is used the map/convert the name os a service to its respective url on a specific host
     * @param serviceName This is string value of a service's name identity
     * @return String This is string value that would represent a url of a service
     */
    private String getServiceURL(String serviceName){
        return this.discoveryClient.getInstances(serviceName).get(0).getUri().toString();
    }

    /**
     * Test function, this method is used to test the servic
     * @return String This is a string value of a json test
     *
    @GetMapping(value ="/{key}", produces = "application/json")
    public String testNothing(@PathVariable String key) {
        String output = "";

        ImportTwitterRequest importRequest = new ImportTwitterRequest(key,10);
        ImportTwitterResponse importResponse = importClient.getTwitterDataJson(importRequest);

        if(importResponse.getFallback() == true)
            output = importResponse.getFallbackMessage();
        else
            output = importResponse.getJsonData();

        return output;
    }*/


    @GetMapping(value ="/analyse/trainData", produces = "application/json")
    public String trainData() {
        String output = "";

        //analyseClient.trainData();
        if(analyseClient.trainData())
            output = "Succsess";
        else{
            output = "Fail";
        }
        return output;
    }


    /*@GetMapping(value ="test/{line}", produces = "application/json")
    public String testNothing2(@PathVariable String line) {

        String output = "";
        AnalyseDataResponse analyseResponse = analyseClient.findSentiment(line);

        if(analyseResponse.getFallback() == true)
            output = analyseResponse.getFallbackMessage();
        else {
            output = analyseResponse.getSentiment().getCssClass();
        }

        return output;
    }*/

    /**
     * This the endpoint for registering the user.
     * @param form This is the body sent by POST
     * @return This is the response http entity.
     */
    @PostMapping(value = "/user/register",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterForm form) {
        RegisterRequest registerRequest = new RegisterRequest(form.getUsername(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getEmail());
        RegisterResponse registerResponse = userClient.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }


    /*
    @PostMapping(value = "/user/requestAdmin",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<RegisterAdminResponse> registerAdmin(@RequestBody RegisterForm form) {
        RegisterAdminRequest registerRequest = new RegisterAdminRequest(form.getUsername(), form.getFirstName(), form.getLastName(), form.getPassword(), form.getEmail());
        RegisterAdminResponse registerResponse = userClient.requestAdmin(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
    */


    @GetMapping(value ="user/getUser/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<GetUserResponse> getUser(@PathVariable String id){
        GetUserRequest getUserRequest = new GetUserRequest(UUID.fromString(id));
        GetUserResponse getUserResponse = userClient.getUser(getUserRequest);
        return new ResponseEntity<>(getUserResponse, HttpStatus.OK);
    }

    /**
     * This is the endpoint to allow the user to login.
     * @param request This is the body send by POST
     * @return This is the response http entity
     */
    @PostMapping(value = "/user/login",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = userClient.login(request);
        System.out.println(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This is the endpoint to allow the user to verify their account
     * via email.
     * @param request This is the body send by POST
     * @return This is the response http entity
     */
    @PostMapping(value = "/user/verify",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<VerifyAccountResponse> verify(@RequestBody VerifyAccountRequest request) {
        System.out.println("Verifying User: " + request.getEmail());
        VerifyAccountResponse response = userClient.verifyAccount(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * This the endpoint for resending the verification code.
     * @param request This is the body send by POST
     * @return This is the response http entity.
     */
    @PostMapping(value = "/user/resend",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<ResendCodeResponse> resendCode(@RequestBody ResendCodeRequest request) {
        ResendCodeResponse response = userClient.resendCode(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * This the endpoint for resending the verification code.
     * @param request This is the body send by POST
     * @return This is the response http entity.
     */
    @PostMapping(value = "/user/updateProfile",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<UpdateProfileResponse> updateProfile(@RequestBody UpdateProfileRequest request) {
        UpdateProfileResponse response = userClient.updateProfile(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * This the endpoint for changing the permission of a user
     * @param request This is the body send by POST
     * @return This is the response http entity
     */
    @PostMapping(value = "/changeUser",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<ChangeUserResponse> changeUser(@RequestBody ChangeUserRequest request) {
        ChangeUserResponse response = userClient.managePermissions(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This the endpoint for getting all the users registered on the system
     * @return This is the response http entity. It contains all the users.
     */
    @GetMapping(value = "/user/getAll", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        System.out.println("Getting all users from the database");
        GetAllUsersResponse response = userClient.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/retrievePrevious", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<ArrayList<ArrayList<Graph>>> retrievePreviousData() {
        return null;
    }

    /**
     * This the endpoint for getting all the users registered on the system.
     * @param jsonRequest This is the body send by POST.
     * @return This is the response http entity. It contains all the users.
     */
    @PostMapping(value = "/addNewApiSource", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> addApiSource(@RequestBody String jsonRequest) {
        String response = importClient.addApiSource(jsonRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This the endpoint for getting all the users registered on the system
     * @param request This is the body send by POST
     * @return This is the response http entity. It contains all the users.
     */
    @PostMapping(value = "/getSourceById", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<GetAPISourceByIdResponse> getSourceById(@RequestBody GetAPISourceByIdRequest request) {
        GetAPISourceByIdResponse response = importClient.getSourceById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This the endpoint for getting all the users registered on the system
     * @param jsonRequest This is the body send by POST
     * @return This is the response http entity. It contains all the users.
     */
    @PostMapping(value = "/updateAPI", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> editAPISource(@RequestBody String jsonRequest) {
        String response = importClient.editAPISource(jsonRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This the endpoint for getting all the api sources.
     * @return This is the response http entity. It contains all the users.
     */
    @GetMapping(value = "/getAllSources", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<GetAllAPISourcesResponse> editAPISource() {
        GetAllAPISourcesResponse response = importClient.getAllAPISources();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method is used to facilitate communication to all the Services.
     * Outputs data related to a topic/key.
     * @param key This is a path variable of string value
     * @return ResponseEntity<ArrayList<ArrayList<Graph>>>
     *     This object contains data representing a response from all the services combined.
     * @throws Exception This is thrown if exception caught in any of the Services.
     */
    @PostMapping(value = "/main/{key}", produces = "application/json")
    @CrossOrigin
    //@HystrixCommand(fallbackMethod = "fallback")
    public ResponseEntity<ArrayList<ArrayList<Graph>>> init(@PathVariable String key, @RequestBody SearchRequest request) throws Exception {
        ArrayList<ArrayList<Graph>> outputData = new ArrayList<>();

        System.out.println(request.getUsername());
        System.out.println(request.getPermission());
        //ArrayList <String> outputData = new ArrayList<>();
        HttpHeaders requestHeaders;

        /*********************IMPORT*************************/

        //String url = "http://Import-Service/Import/importData";
        //UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("value",key);

        ImportDataRequest importRequest = new ImportDataRequest(key,100);
        ImportDataResponse importResponse = importClient.importData(importRequest);

        if(importResponse.getFallback() == true) {
            //outputData.add(importResponse.getFallbackMessage());
            //return new ArrayList<>();//outputData;

            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = importResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }

        System.out.println("***********************IMPORT HAS BEEN DONE*************************");



        /*********************PARSE*************************/

        ParseImportedDataRequest parseRequest = new ParseImportedDataRequest(DataSource.TWITTER, importResponse.getList().get(0).getData(), request.getPermission());
        ParseImportedDataResponse parseResponse = parseClient.parseImportedData(parseRequest);
        ArrayList<ParsedData> socialMediaData = parseResponse.getDataList();

        ParseImportedDataRequest parseRequestNews = new ParseImportedDataRequest(DataSource.NEWSARTICLE, importResponse.getList().get(1).getData(), request.getPermission());
        parseResponse = parseClient.parseImportedData(parseRequestNews);
        ArrayList<ParsedArticle> newsData = parseResponse.getArticleList();

        if(parseResponse.getFallback() == true) {
            //outputData.add(parseResponse.getFallbackMessage());
            //outputData.add();
            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = parseResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }

        System.out.println("***********************PARSE HAS BEEN DONE*************************");



        /*********************ANALYSE*************************/

        AnalyseDataRequest analyseRequest = new AnalyseDataRequest(socialMediaData, newsData);//    DataSource.TWITTER,ImportResponse. getJsonData());
        AnalyseDataResponse analyseResponse = analyseClient.analyzeData(analyseRequest);


        if(analyseResponse.getFallback() == true) {
            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = analyseResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }



        System.out.println("***********************ANALYSE HAS BEEN DONE*************************");


        /*********************VISUALISE**********************/

        VisualizeDataRequest visualizeRequest = new VisualizeDataRequest(
                analyseResponse.getPattenList(),
                analyseResponse.getRelationshipList(),
                analyseResponse.getPattenList(),
                analyseResponse.getTrendList(),
                analyseResponse.getAnomalyList(),
                analyseResponse.getWordList());//    DataSource.TWITTER,ImportResponse. getJsonData());
        VisualizeDataResponse visualizeResponse = visualizeClient.visualizeData(visualizeRequest);


        if(visualizeResponse.getFallback() == true) {
            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = analyseResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }

        System.out.println("***********************VISUALIZE HAS BEEN DONE*************************");


        for(int i =0; i < visualizeResponse.outputData.size(); i++)
            outputData.add(visualizeResponse.outputData.get(i));

        return new ResponseEntity<>(outputData,HttpStatus.OK);

    }


    @GetMapping(value = "/collect/{key}/{from}/{to}", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> collectDatedData(@PathVariable String key, @PathVariable String from, @PathVariable String to){


        ImportTwitterResponse res = importClient.importDatedData(new ImportTwitterRequest(key, from, to));

        if(!res.getFallback()){
            System.out.println(".........................Import completed successfully..................\n\n\n");


            ParseImportedDataRequest parseRequest = new ParseImportedDataRequest(DataSource.TWITTER, res.getJsonData(), "VIEWING");
            ParseImportedDataResponse parseResponse = parseClient.parseImportedData(parseRequest);

            if(!parseResponse.getFallback()) {
                System.out.println("........................Parsed Data Successfully...........................\n\n\n");
                return new ResponseEntity<>("{ \n \"success\" : true \n}",HttpStatus.OK);
            }

        }
        System.out.println("///////////////////////////////////////    FAILED //////////////////////////////////////////");
        return null;
    }


    public static class Graph{
        Graph(){}
    }

    public static class ErrorGraph extends Graph{
        public String Error;
    }



}
