package com.booking.api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class ApiRequests {
    public RequestSpecification requestSpecification = RestAssured.given();

    public Response request(String baseUri, String endpoint, String body, Method method) {
        return requestSpecification.given()
                .baseUri(baseUri)
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .request(method, endpoint);

    }

    public Response request(String baseUri, String endpoint, Map<String, String> params, Method method) {
        return requestSpecification.given()
                .spec(requestSpecification)
                .baseUri(baseUri)
                .params(params)
                .contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .request(method, endpoint);
    }
    public Response request(String baseUri, String endpoint, Method method) {
        return requestSpecification.given()
                .spec(requestSpecification)
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .request(method, endpoint);
    }
}
