package com.demo.donations.controller;

import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.DonateRequest;
import com.demo.donations.model.objects.QueryDonations;
import com.demo.donations.service.DonationsService;
import com.demo.donations.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donations")
public class DonationController {
    
    @Autowired
    DonationsService donationService;
    
    @GetMapping("/getCountries")
    public ResponseEntity<CustomResponse> getCountries() {
        CustomResponse response = null;
        try {
            response = donationService.getCountryList();
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/getCompanylist/{country}")
    public ResponseEntity<CustomResponse> getCompanyList(@PathVariable("country") Long country) {
        CustomResponse response = null;
        try {
            response = donationService.getCompanyList(country);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/donate")
    public ResponseEntity<CustomResponse> donate(@RequestBody DonateRequest request, 
                                                    @RequestHeader("token") String token) {
        CustomResponse response = null;
        try {
            response = donationService.donate(request, token);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getDonations")
    public ResponseEntity<CustomResponse> getDonations(@RequestBody QueryDonations request, 
                                                        @RequestHeader("token") String token) {
        CustomResponse response = null;
        try {
            response = donationService.queryDonations(request, token);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getGeneralInfo")
    public ResponseEntity<CustomResponse> getGenerals(@RequestBody QueryDonations request, 
                                                        @RequestHeader("token") String token) {
        CustomResponse response = null;
        try {
            response = donationService.getGeneralInfo(request, token);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
