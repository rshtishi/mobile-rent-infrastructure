package com.github.rshtishi;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {


    @Nested
    @DisplayName("Success Cases")
    class SuccessCases {

        @Test
        @DisplayName("Given a phone, when it is booked, then it should return true")
        public void testBook() {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer("Rando");
            // execute
            phone.book(customer);
            // verify
            assertTrue(phone.isBooked());
        }

        @Test
        @DisplayName("Given a phone, when it is returned, then it should return false")
        public void testReturnedBook() {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer("Rando");
            phone.book(customer);
            // execute
            phone.returnPhone(customer);
            // verify
            assertFalse(phone.isBooked());
        }

    }

    @Nested
    @DisplayName("Failure Cases")
    class FailureCases {

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " "})
        @DisplayName("Given a phone, when it is booked with invalid customer, then it should throw an exception")
        void testBookWithInvalidCustomer(String customerName) {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer(customerName);
            // execute and verify
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> phone.book(customer));
            assertTrue(exception.getMessage().contains("Customer is required!"));
        }

        @Test
        @DisplayName("Given a phone, when it is booked twice, then it should throw an exception")
        void testBookTwice() {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer("Rando");
            phone.book(customer);
            // execute and verify
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> phone.book(customer));
            assertTrue(exception.getMessage().contains("Phone is already booked!"));
        }


        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " "})
        @DisplayName("Given a phone, when it is returned with invalid customer, then it should throw an exception")
        void testReturnedBookWithInvalidCustomer(String customerName) {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer(customerName);
            // execute and verify
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> phone.returnPhone(customer));
            assertTrue(exception.getMessage().contains("Customer is required!"));
        }

        @Test
        @DisplayName("Given a phone, when it is returned without booking, then it should throw an exception")
        void testReturnedBookWithoutBooking() {
            // setup
            Phone phone = new Phone("1", "Samsung");
            Customer customer = new Customer("Rando");
            // execute and verify
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> phone.returnPhone(customer));
            assertTrue(exception.getMessage().contains("Phone is not booked!"));
        }

    }
}