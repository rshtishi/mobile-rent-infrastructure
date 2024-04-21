package com.github.rshtishi;

import java.util.Date;

public class Phone {

    private String id;
    private String brand;
    private Booking booking;

    public Phone(String id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isBooked() {
        return booking != null;
    }

    public Date getBookingDate() {
        return booking != null ? booking.bookingDate() : null;
    }

    public void book(Customer customer) {
        if (customer == null || customer.name() == null || customer.name().isBlank()) {
            throw new IllegalArgumentException("Customer is required!");
        }
        if (booking != null) {
            throw new IllegalStateException("Phone is already booked!");
        }

        booking = new Booking(customer, new Date());
    }


    public void returnPhone(Customer customer) {
        if (customer == null || customer.name() == null || customer.name().isBlank()) {
            throw new IllegalArgumentException("Customer is required!");
        }
        if (booking == null) {
            throw new IllegalStateException("Phone is not booked!");
        }
        if (!booking.bookedBy().equals(customer)) {
            throw new IllegalStateException("Wrong Customer returning the book!");
        }
        booking = null;
    }

}
