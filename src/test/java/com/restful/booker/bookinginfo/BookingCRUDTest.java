package com.restful.booker.bookinginfo;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends TestBase {
    static String firstname = "Joseph" + TestUtils.getRandomValue();
    static String lastname = "Boltas" + TestUtils.getRandomValue();
    static int totalprice = Integer.parseInt(1 + TestUtils.getRandomValue());
    static boolean depositpaid = true;
    static String additionalneeds = "vegetarian meal";

    static String token;
    static int id;

    @Steps
    BookingSteps bookingSteps;

    @Title("This method will create a Token")
    @Test
    public void test001() {
        ValidatableResponse response = bookingSteps.getToken().statusCode(200);
        token = response.extract().path("token");
    }

    @Title("This method will create a booking")
    @Test
    public void test002() {
        BookingPojo.Bookingdates bookingdates = new BookingPojo.Bookingdates();
        bookingdates.setCheckin("2022-10-14");
        bookingdates.setCheckout("2022-11-14");
        ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds).statusCode(200);
        id = response.extract().path("bookingid");
    }

    @Title("This method will verify new Booking ID creation")
    @Test
    public void test003() {
        ValidatableResponse response = bookingSteps.getBookingInfoByID();
        ArrayList<?> booking = response.extract().path("bookingid");
        Assert.assertTrue(booking.contains(id));
    }

    @Title("This method will get booking with Id")
    @Test
    public void test004() {
        bookingSteps.getSingleBookingIDs(id).statusCode(200);
    }

    @Title("This method will updated a booking with ID")
    @Test
    public void test005() {
        additionalneeds = "extra wine";
        BookingPojo.Bookingdates bookingdates = new BookingPojo.Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-12-01");
        bookingSteps.updateBookingWithID(id, token, firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        ValidatableResponse response = bookingSteps.getSingleBookingIDs(id);
        HashMap<String, ?> update = response.extract().path("");
        Assert.assertThat(update, hasValue("extra wine"));
    }

    @Title("This method will delete a booking with ID")
    @Test
    public void test006() {
        bookingSteps.deleteABookingID(id, token).statusCode(404);
        bookingSteps.getSingleBookingIDs(id).statusCode(404);
    }

}
