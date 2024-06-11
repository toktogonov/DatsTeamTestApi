package com.booking.testCases;


import com.booking.api.bookingTestRequests.GetBookingSearch;
import com.booking.api.model.createBooking.CreateBookingBody;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestClass extends BasicData {

    @Test(priority = 1)
    public void createBookingTest() {
        CreateBookingBody createBookingBody = new CreateBookingBody();
        //Создает броинрование
        Response responseCreateBkng = createBookingRequest.createBooking(createBookingBody);

        responseCreateBkng.body().prettyPrint();
        System.out.println(responseCreateBkng.statusCode());
        Assert.assertEquals(responseCreateBkng.statusCode(), statusCode200);
        Assert.assertEquals(responseCreateBkng.jsonPath().getString("booking.firstname"), createBookingBody.getFirstname(), "firstname не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getString("booking.lastname"), createBookingBody.getLastname(), "lastname не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getInt("booking.totalprice"), createBookingBody.getTotalprice(), "totalprice не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getBoolean("booking.depositpaid"), createBookingBody.isDepositpaid(), "depositpaid не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getString("booking.bookingdates.checkin"), createBookingBody.getBookingdates().getCheckin(), "checkin не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getString("booking.bookingdates.checkout"), createBookingBody.getBookingdates().getCheckout(), "checkout не совпадает");
        Assert.assertEquals(responseCreateBkng.jsonPath().getString("booking.additionalneeds"), createBookingBody.getAdditionalneeds(), "additionalneeds не совпадают");

    }


    @Test(priority = 2)
    public void getBookingSearchByIdTest() {
        CreateBookingBody createBookingBody1 = new CreateBookingBody();
        //Создает бронирование
        Response responseCreateBkng2 = createBookingRequest.createBooking(createBookingBody1);

        Assert.assertEquals(responseCreateBkng2.statusCode(), statusCode200);
        System.out.println("ResponseCreateBkng:");
        responseCreateBkng2.prettyPrint();
        Assert.assertNotNull(responseCreateBkng2.jsonPath().getInt("bookingid"), "bookingId null");

        //Сохраняет id бронирования в bookingId
        int bookingId = responseCreateBkng2.jsonPath().getInt("bookingid");

        System.out.println("---------SearchById--------------");

        GetBookingSearch getBookingSearch1 = new GetBookingSearch();
        //Осуществляется поиск бронирований по Id
        Response responseGetBkngSearchById = getBookingSearch1.getBookingSearchById(bookingId);
        Assert.assertEquals(responseGetBkngSearchById.statusCode(), statusCode200);
        System.out.println("ResponseSearchById:");
        responseGetBkngSearchById.prettyPrint();
    }


    @Test(priority = 3)
    public void getBookingIdByNameTest() {
        //Создает бронирование
        CreateBookingBody createBookingBody2 = new CreateBookingBody();
        Response responseCreateBkng3 = createBookingRequest.createBooking(createBookingBody2);

        Assert.assertEquals(responseCreateBkng3.statusCode(), statusCode200);
        System.out.println("ResponseCreateBkng:");
        responseCreateBkng3.prettyPrint();

        Assert.assertNotNull(responseCreateBkng3.jsonPath().getString("booking.firstname"), "firstname null");

        //Сохраняет firstname бронирования в переменную firstname
        String firstname = responseCreateBkng3.jsonPath().getString("booking.firstname");

        // Формирует параметр для запроса по имени
        Map<String, String> params = new HashMap<>();
        params.put("firstname", firstname);

        GetBookingSearch getBookingSearch2 = new GetBookingSearch();
        //Осуществляется поиск Id бронирований, по имени пользователя
        Response responseGetBkngIdByName = getBookingSearch2.getBkngBySearchParams(params);

        Assert.assertEquals(responseGetBkngIdByName.getStatusCode(), statusCode200);

        System.out.println("responseGetBkngIdByName:");
        responseGetBkngIdByName.prettyPrint();

        Assert.assertEquals(responseCreateBkng3.jsonPath().getInt("bookingid"), responseGetBkngIdByName.jsonPath().getInt("[0].bookingid"), "Не совпадают ID");

    }

    @Test(priority = 4)
    public void getBookingIdsByCheckinCheckoutTest() {
        CreateBookingBody createBookingBody3 = new CreateBookingBody();
        // Создает бронирование
        Response responseCreateBkng4 = createBookingRequest.createBooking(createBookingBody3);
        Assert.assertEquals(responseCreateBkng4.statusCode(), statusCode200);

        // Сохраняет даты заезда и выезда из созданного бронирования
        String checkinDate = createBookingBody3.getBookingdates().getCheckin();
        String checkoutDate = createBookingBody3.getBookingdates().getCheckout();

        // Формирует параметры для запроса по датам заезда и выезда
        Map<String, String> params = new HashMap<>();
        params.put("checkin", checkinDate);
        params.put("checkout", checkoutDate);

        GetBookingSearch getBookingSearch3 = new GetBookingSearch();
        // Осуществляется поиск Id бронирований по датам заезда и выезда
        Response responseGetBookingIds = getBookingSearch3.getBkngBySearchParams(params);
        Assert.assertEquals(responseGetBookingIds.getStatusCode(), statusCode200);

        System.out.println("ResponseGetBookingIds:");
        responseGetBookingIds.prettyPrint();

        // Проверяет, что список Id бронирований не пустой
        JsonPath jsonPath = responseGetBookingIds.jsonPath();
        Assert.assertFalse(jsonPath.getList("").isEmpty(), "Пустой список");

    }
}
