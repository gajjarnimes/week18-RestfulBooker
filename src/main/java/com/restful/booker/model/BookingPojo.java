package com.restful.booker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingPojo {
      private   String firstname;
      private    String lastname;
      private      int totalprice;
      private     boolean depositpaid;
      private   Bookingdates bookingdates;

      private   String additionalneeds;



        public String getFirstname() {
            return this.firstname; }
        public void setFirstname(String firstname) {
            this.firstname = firstname; }


        public String getLastname() {
            return this.lastname; }
        public void setLastname(String lastname) {
            this.lastname = lastname; }

        public int getTotalprice() {
            return this.totalprice; }
        public void setTotalprice(int totalprice) {
            this.totalprice = totalprice; }

        public boolean getDepositpaid() {
            return this.depositpaid; }
        public void setDepositpaid(boolean depositpaid) {
            this.depositpaid = depositpaid; }


        public Bookingdates getBookingdates() {
            return this.bookingdates; }
        public void setBookingdates(Bookingdates bookingdates) {
            this.bookingdates = bookingdates; }

    public String getAdditionalneeds() {
        return this.additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

     public static class Bookingdates {
         private    String checkin;
         private  String checkout;

         public String getCheckin() {
             return this.checkin;
         }

         public void setCheckin(String checkin) {
             this.checkin = checkin;
         }

         public String getCheckout() {
             return this.checkout;
         }

         public void setCheckout(String checkout) {
             this.checkout = checkout;
         }



     }



    }

