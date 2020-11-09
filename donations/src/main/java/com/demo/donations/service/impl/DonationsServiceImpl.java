package com.demo.donations.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import com.demo.donations.model.entity.CompanyEntity;
import com.demo.donations.model.entity.CountryEntity;
import com.demo.donations.model.entity.OperationEntity;
import com.demo.donations.model.entity.UserEntity;
import com.demo.donations.model.objects.Companies;
import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.DonateRequest;
import com.demo.donations.model.objects.OperationInterfaceA;
import com.demo.donations.model.objects.OperationsInterface;
import com.demo.donations.model.objects.QueryDonations;
import com.demo.donations.model.repository.CompanyRepo;
import com.demo.donations.model.repository.CountryRepo;
import com.demo.donations.model.repository.OperationRepo;
import com.demo.donations.model.repository.UserRepo;
import com.demo.donations.service.DonationsService;
import com.demo.donations.service.ValidateTokenService;
import com.demo.donations.utils.Utils;
import com.fasterxml.uuid.Generators;
import com.google.common.base.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.lang.Collections;

@Service
public class DonationsServiceImpl implements DonationsService {

    @Autowired
    ValidateTokenService validateToken;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    OperationRepo operationRepo;

    @Value("${app.config.adminProfile}")
    private Long adminLevel;

    public CustomResponse getCountryList() {
        CustomResponse response = new CustomResponse();
        try {
            List<CountryEntity> countryList = countryRepo.findAll();
            response.setCode(Utils.CODE_OK);
            response.setResponse("Operación realizada con éxito");
            response.setObjectResponse(countryList);
        } catch (Exception e) {
            response.setCode(Utils.CODE_ERROR);
            response.setResponse(e.getMessage());
        }
        return response;
    }

    public CustomResponse getCompanyList(Long country) {
        CustomResponse response = new CustomResponse();
        try {
            CountryEntity c = countryRepo.findByIdCountry(country);
            List<CompanyEntity> companies = companyRepo.findByCountry(c);
            if (!Collections.isEmpty(companies)) {
                List<Companies> compList = companies.stream().map(m -> {
                    return new Companies(m.getIdCompany(), m.getName(), m.getCountry().getIdCountry());
                }).collect(Collectors.toList());
                response.setCode(Utils.CODE_OK);
                response.setResponse("Operación realizada con éxito");
                response.setObjectResponse(compList);
            } else {
                response.setCode(Utils.CODE_BAD_REQUEST);
                response.setResponse("Sin empresas en el país consultado");
            }
        } catch (Exception e) {
            response.setCode(Utils.CODE_ERROR);
            response.setResponse(e.getMessage());
        }
        return response;
    }

    public CustomResponse donate(DonateRequest request, String token) {
        CustomResponse response = new CustomResponse();
        try {
            if (request.getCountry() != null && request.getCompany() != null && request.getAmount() > 0) {
                if (!StringUtils.isEmpty(request.getCreditCard().getExpiration())
                        && !StringUtils.isEmpty(request.getCreditCard().getNumber())
                        && !(request.getCreditCard().getNumber().length() < 16) 
                        && !StringUtils.isEmpty(request.getCreditCard().getOwner())
                        && !StringUtils.isEmpty(request.getCreditCard().getSecret())) {
                    Map<String, Object> tokenValues = validateToken.extractAllClaims(token);
                    UserEntity user = userRepo.findByEmail(tokenValues.get("email").toString());
                    OperationEntity last = operationRepo.findLastUserOperationByCountry(request.getCountry(),
                            user.getIdUser());
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    String monthYear = String.valueOf(cal.get(Calendar.MONTH) + 1)
                            + String.valueOf(cal.get(Calendar.YEAR));
                    boolean available = false;
                    if (last != null) {
                        int daysDiff = 0;
                        daysDiff = Utils.getDateDiff(date, last.getExecution()).get("days");
                        if (monthYear != last.getMonthYear() && daysDiff > 0) {
                            available = true;
                        }
                    } else {
                        available = true;
                    }
                    if (available) {
                        CompanyEntity company = companyRepo.findByIdCompany(request.getCompany());
                        CountryEntity country = countryRepo.findByIdCountry(request.getCountry());
                        if(company.getCountry().getIdCountry() == country.getIdCountry()) {
                            //Successfull third party SDK credit card operation
                            OperationEntity operation = new OperationEntity();
                            UUID uuid = Generators.timeBasedGenerator().generate();
                            operation.setAmount(request.getAmount());
                            operation.setCreditCard(request.getCreditCard().getNumber());
                            operation.setMonthYear(monthYear);
                            operation.setTransactionUUID(uuid.toString());
                            operation.setIdUser(user);
                            operation.setIdCompany(company);
                            operation.setIdCountry(country);
                            operation.setExecution(date);
                            operationRepo.save(operation);
                            response.setCode(Utils.CODE_OK);
                            response.setResponse("Donación realizada con éxito");
                        } else {
                            response.setCode(Utils.CODE_BAD_REQUEST);
                            response.setResponse("Parametros incorrectos");
                        }
                    } else {
                        response.setCode(Utils.CODE_BAD_REQUEST);
                        response.setResponse("Ya ha registrado una trasacción para este país este mes");
                    }
                } else {
                    response.setCode(Utils.CODE_BAD_REQUEST);
                    response.setResponse("Tarjeta inválida");
                }
            } else {
                response.setCode(Utils.CODE_BAD_REQUEST);
                response.setResponse("Parametros incompletos");
            }
        } catch(Exception e) {
            response.setCode(Utils.CODE_ERROR);
            response.setResponse(e.getMessage());
        }
        return response;
    }

    public CustomResponse queryDonations(QueryDonations dates, String token) {
        CustomResponse response = new CustomResponse();
        try {
            if(dates.getEnd() != null && dates.getStart() != null) {
                Map<String, Object> tokenValues = validateToken.extractAllClaims(token);
                UserEntity user = userRepo.findByEmail(tokenValues.get("email").toString());
                if(user != null) {
                    List<OperationsInterface> list = new ArrayList<OperationsInterface>();
                    List<OperationEntity> operations = operationRepo.getDonations(user.getIdUser(), dates.getStart(), dates.getEnd());
                    if(!Collections.isEmpty(operations)) {
                        list = operations.stream().map( m -> {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
                            String parsedString = sdf.format(m.getExecution());
                            OperationsInterface op = new OperationsInterface();
                            op.setAmount(m.getAmount());
                            op.setCompany(m.getIdCompany().getName());
                            op.setCountry(m.getIdCountry().getCode());
                            op.setExecution(parsedString);
                            String cutted = m.getCreditCard().substring(m.getCreditCard().length() - 4, m.getCreditCard().length());
                            op.setCredit( Strings.padStart(cutted, 16, '*'));
                            return op;
                        }).collect(Collectors.toList());
                    }
                    response.setCode(Utils.CODE_OK);
                    response.setResponse("Operaciones consultadas con éxito");
                    response.setObjectResponse(list);
                } else {
                    response.setCode(Utils.CODE_BAD_REQUEST);
                    response.setResponse("Parametros incorrectos");                    
                }
            } else {
                response.setCode(Utils.CODE_BAD_REQUEST);
                response.setResponse("Parametros incompletos");
            }
        } catch(Exception e) {
            response.setCode(Utils.CODE_ERROR);
            response.setResponse(e.getMessage());
        }
        return response;
    }

    public CustomResponse getGeneralInfo(QueryDonations dates, String token) {
        CustomResponse response = new CustomResponse();
        try {
            if(dates.getEnd() != null && dates.getStart() != null) {
                Map<String, Object> tokenValues = validateToken.extractAllClaims(token);
                UserEntity user = userRepo.findByEmail(tokenValues.get("email").toString());
                if(user != null && user.getUserLevel().getIdLevel() == this.adminLevel) {
                    List<OperationInterfaceA> list = new ArrayList<OperationInterfaceA>();
                    List<OperationEntity> operations = operationRepo.getDonationsInfo(dates.getStart(), dates.getEnd());
                    if(!Collections.isEmpty(operations)) {
                        list = operations.stream().map( m -> {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
                            String parsedString = sdf.format(m.getExecution());
                            OperationInterfaceA op = new OperationInterfaceA();
                            op.setAmount(m.getAmount());
                            op.setCompany(m.getIdCompany().getName());
                            op.setCountry(m.getIdCountry().getCode());
                            op.setDocument(m.getIdUser().getDocument());
                            op.setEmail(m.getIdUser().getEmail());
                            op.setName(m.getIdUser().getName());
                            op.setLastname(m.getIdUser().getLastname());
                            op.setExecution(parsedString);
                            return op;
                        }).collect(Collectors.toList());
                    }
                    response.setCode(Utils.CODE_OK);
                    response.setResponse("Información consultada con éxito");
                    response.setObjectResponse(list);
                } else {
                    response.setCode(Utils.CODE_UNAUTORIZED);
                    response.setResponse("Parametros incorrectos");                    
                }
            } else {
                response.setCode(Utils.CODE_BAD_REQUEST);
                response.setResponse("Parametros incompletos");
            }
        } catch(Exception e) {
            response.setCode(Utils.CODE_ERROR);
            response.setResponse(e.getMessage());
        }
        return response;
    }
}
