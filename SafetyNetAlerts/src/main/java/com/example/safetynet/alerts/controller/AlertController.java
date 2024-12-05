package com.example.safetynet.alerts.controller;

import com.example.safetynet.alerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


// REST Controller that handles all the emergency alert end points.
@RestController
public class AlertController {

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }



    // Returns a list of people that are serviced by a fire station, with a count for adults and children
    @GetMapping("/firestation")
    public Map<String, Object> getPersonsByStation(@RequestParam int stationNumber) {
        System.out.println("REQUEST: GET /firestation?stationNumber=" + stationNumber);
        Map<String, Object> result = alertService.getPeopleByStation(stationNumber);
        System.out.println("RESPONSE: " + result);
        return result;
    }

    // Returns children that are at a specific address.
    @GetMapping("/childAlert")
    public Map<String, Object> getChildrenAtAddress(@RequestParam String address) {
        System.out.println("REQUEST: GET /childAlert?address=" + address);
        Map<String, Object> result = alertService.getChildrenAtAddress(address);
        System.out.println("RESPONSE: " + result);
        return result;
    }

    // Returns the phone numbers of residents that are serviced by a fire station
    @GetMapping("/phoneAlert")
    public List<String> getPhonesByStation(@RequestParam int firestation) {
        System.out.println("REQUEST: GET /phoneAlert?firestation=" + firestation);
        List<String> result = alertService.getPhonesByStation(firestation);
        System.out.println("RESPONSE: " + result);
        return result;
    }

    // Returns detailed information about a person
    @GetMapping("/personInfo")
    public Map<String, Object> getPersonInfo(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        System.out.println("REQUEST: GET /personInfo?firstName=" + firstName + "&lastName=" + lastName);
        Map<String, Object> result = alertService.getPersonInfo(firstName, lastName);
        System.out.println("RESPONSE: " + result);
        return result;
    }
}
