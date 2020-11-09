package com.demo.donations.service;

import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.DonateRequest;
import com.demo.donations.model.objects.QueryDonations;

public interface DonationsService {
    
    public CustomResponse getCountryList();
    public CustomResponse getCompanyList(Long country);
    public CustomResponse donate(DonateRequest request, String token);
    public CustomResponse queryDonations(QueryDonations dates, String token);
    public CustomResponse getGeneralInfo(QueryDonations dates, String token);
}
