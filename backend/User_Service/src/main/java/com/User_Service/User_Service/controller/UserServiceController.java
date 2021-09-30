package com.User_Service.User_Service.controller;


import com.User_Service.User_Service.request.*;
import com.User_Service.User_Service.response.*;
import com.User_Service.User_Service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/User")
public class UserServiceController {

    @Autowired
    private UserServiceImpl service;
    /**
     * This function will allow the admin to change the permissions of a user.
     * @param request The request containing the necessary information about the user.
     * @return A class that contains if the update was successful or not.
     * @throws Exception Thrown when any exceptions are encountered
     */
    @PostMapping(value = "/changeUser")
    public @ResponseBody ChangeUserResponse changeUser(@RequestBody ChangeUserRequest request) throws Exception {
        //ChangeUserRequest request = requestEntity.getBody();
        return service.changeUser(request);
    }

    /**
     * This function will allow the gateway to connect to the user service for getting all users.
     * @return A class that contains if the update was successful or not.
     * @throws Exception Thrown when any exceptions are encountered
     */
    @GetMapping(value = "/getAll",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody GetAllUsersResponse getAllUsers() throws Exception {
        //GetAllUsersRequest request = requestEntity.getBody();
        return service.getAllUsers();
    }

    @PostMapping(value = "/getUser" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody GetUserResponse getUser(@RequestBody GetUserRequest request) throws Exception{
        //GetUserRequest request = requestEntity.getBody();
        return service.getUser(request);
    }

    @PostMapping(value = "/register")
    public @ResponseBody RegisterResponse register(@RequestBody RegisterRequest request) throws Exception {
        //RegisterRequest request = requestEntity.getBody();
        return service.register(request);
    }

    @PostMapping(value = "/requestAdmin")
    public @ResponseBody
    RequestAdminResponse registerAdmin(@RequestBody RequestAdminRequest request) throws Exception {
        //RegisterRequest request = requestEntity.getBody();
        return service.requestAdmin(request);
    }

    @PostMapping(value = "/login")
    public @ResponseBody LoginResponse login(@RequestBody LoginRequest request) throws Exception {
        //LoginRequest request = requestEntity.getBody();
        return service.login(request);
    }

    @PostMapping(value = "/getCurrentUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody GetCurrentUserResponse getCurrentUser(@RequestBody GetCurrentUserRequest request) throws Exception {
        return service.getCurrentUser(request);
    }

    @PostMapping(value = "/verifyAccount")
    public @ResponseBody VerifyAccountResponse verifyAccount(@RequestBody VerifyAccountRequest request) throws Exception {
        return service.verifyAccount(request);
    }

    @PostMapping(value = "/resendCode")
    public @ResponseBody ResendCodeResponse resendCode(@RequestBody ResendCodeRequest request) throws Exception {
        return service.resendCode(request);
    }

    @PostMapping(value = "/updateProfile")
    public @ResponseBody UpdateProfileResponse resendCode(@RequestBody UpdateProfileRequest request) throws Exception {
        return service.updateProfile(request);
    }

    @PostMapping(value = "/addReport")
    public @ResponseBody
    ReportResponse addReport(@RequestBody ReportRequest request) throws Exception {
        return service.addReport(request);
    }

    @PostMapping(value = "/removeReport")
    public @ResponseBody
    ReportResponse removeReport(@RequestBody ReportRequest request) throws Exception {
        return service.removeReport(request);
    }

    @GetMapping(value = "/getReports/{id}")
    public @ResponseBody GetUserReportsResponse getReports(@PathVariable String id) throws Exception {
        return service.getReports(new GetUserReportsRequest(id));
    }
}
