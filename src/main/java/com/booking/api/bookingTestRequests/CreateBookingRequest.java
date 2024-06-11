package com.booking.api.bookingTestRequests;

import com.booking.api.ApiRequests;
import com.booking.api.dataProviders.ConfigReader;
import com.booking.api.model.createBooking.CreateBookingBody;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class CreateBookingRequest extends ApiRequests {

   private final String endpoint = "/booking";

    public Response createBooking(CreateBookingBody createBookingBody) {

        return request(ConfigReader.getProperty("baseURI")
                , endpoint
                , createBookingBody.body()
                , Method.POST);
    }


}
