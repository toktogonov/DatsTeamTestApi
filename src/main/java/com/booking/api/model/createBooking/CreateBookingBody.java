package com.booking.api.model.createBooking;

import com.booking.api.dataProviders.MockDataGenerate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

@Data
public class CreateBookingBody {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public CreateBookingBody() {
        this.firstname = MockDataGenerate.randomFirstName();
        this.lastname = MockDataGenerate.randomLastname();
        this.totalprice = MockDataGenerate.randomTotalPrice();
        this.depositpaid = MockDataGenerate.randomDepositPaid();
        this.additionalneeds = MockDataGenerate.randomAdditionalNeeds();
        this.bookingdates = new BookingDates(MockDataGenerate.randomCheckin(), MockDataGenerate.randomCheckout());
    }

    @Data
    public static class BookingDates {
        private String checkin;
        private String checkout;

        public BookingDates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }
    }

    public String body() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


}
