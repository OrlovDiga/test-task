package com.rincentral.test.services.impl;

import com.rincentral.test.models.external.ExternalBrand;
import com.rincentral.test.models.external.ExternalCar;
import com.rincentral.test.models.external.ExternalCarInfo;
import com.rincentral.test.models.external.RestPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalCarsApiService {
    private static final Logger LOGGER = LogManager.getLogger(ExternalCarsApiService.class);

    @Value("${ext.api.ip}")
    private String extApiIp;
    @Value("${ext.api.port}")
    private String extApiPort;
    private static final String ALL_CARS_URL = "http://%s:%s/api/v1/cars";
    private static final String CAR_BY_ID_URL = "http://%s:%s/api/v1/cars/%d";
    private static final String ALL_BRANDS_URL = "http://%s:%s/api/v1/brands";
    private static final String ALL_CARS_BY_PAGE_NUM = "http://%s:%s/api/v1/cars/paged?page=%d";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ExternalCar> loadAllCars() {
        String url = String.format(ALL_CARS_URL, extApiIp, extApiPort);
        try {
            ResponseEntity<ExternalCar[]> allCarsResponse = restTemplate.getForEntity(url, ExternalCar[].class);
            if (allCarsResponse.getStatusCode() != HttpStatus.OK || allCarsResponse.getBody() == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(allCarsResponse.getBody());

        } catch (RestClientException restClientException) {
            LOGGER.error("Error when trying to load all cars", restClientException);
            return Collections.emptyList();
        }
    }

    public ExternalCarInfo loadCarInformationById(int id) {
        String carUrl = String.format(CAR_BY_ID_URL, extApiIp, extApiPort, id);
        try {
            ResponseEntity<ExternalCarInfo> carInfoResponse = restTemplate.getForEntity(carUrl, ExternalCarInfo.class);
            if (carInfoResponse.getStatusCode() != HttpStatus.OK || carInfoResponse.getBody() == null) {
                return null;
            }
            return carInfoResponse.getBody();
        } catch (RestClientException restClientException) {
            LOGGER.error("Error when trying to load car with id {}", id, restClientException);
            return null;
        }
    }

    public List<ExternalBrand> loadAllBrands() {
        String url = String.format(ALL_BRANDS_URL, extApiIp, extApiPort);
        try {
            ResponseEntity<ExternalBrand[]> allBrandsResponse = restTemplate.getForEntity(url, ExternalBrand[].class);
            if (allBrandsResponse.getStatusCode() != HttpStatus.OK || allBrandsResponse.getBody() == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(allBrandsResponse.getBody());
        } catch (RestClientException restClientException) {
            LOGGER.error("Error when trying to load all brands", restClientException);
            return Collections.emptyList();
        }
    }

    public List<ExternalCar> loadAllCarsByPages() {
        ParameterizedTypeReference<RestPageImpl<ExternalCar>> responseType =
                new ParameterizedTypeReference<>() {};
        int pageNumber = 0;
        int totalPages = 0;
        List<ExternalCar> result = new ArrayList<>();
        try {
            do {
                String pageUrl = String.format(ALL_CARS_BY_PAGE_NUM, extApiIp, extApiPort, pageNumber++);
                ResponseEntity<RestPageImpl<ExternalCar>> response =
                        restTemplate.exchange(pageUrl, HttpMethod.GET, null/*httpEntity*/, responseType);
                totalPages = response.getBody().getTotalPages();
                result.addAll(response.getBody().getContent());
            } while (pageNumber < totalPages);
        } catch (RestClientException restClientException) {
            LOGGER.error("Error when trying to load all cars by paged", restClientException);
            return Collections.emptyList();
        } catch (NullPointerException ex) {
            LOGGER.error("TotalPages is null. Error when trying to load all cars by paged", ex);
            return Collections.emptyList();
        }
        return result;
    }
}
