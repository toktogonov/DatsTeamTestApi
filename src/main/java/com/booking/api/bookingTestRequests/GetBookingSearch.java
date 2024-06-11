package com.booking.api.bookingTestRequests;

import com.booking.api.ApiRequests;
import com.booking.api.dataProviders.ConfigReader;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Map;

public class GetBookingSearch extends ApiRequests {

    private final String endpoint = "/booking/";

    public Response getBookingSearchById(int bookingID){
        return request(ConfigReader.getProperty("baseURI")
                ,endpoint+bookingID
                , Method.GET);
    }

    public Response getBkngBySearchParams(Map<String, String> params){
        return request(ConfigReader.getProperty("baseURI")
                ,endpoint
                , params
                , Method.GET);
    }
}
