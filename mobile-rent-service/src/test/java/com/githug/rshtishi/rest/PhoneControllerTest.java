package com.githug.rshtishi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githug.rshtishi.config.MysqlTestContainer;
import com.githug.rshtishi.dto.BookRequestDto;
import com.githug.rshtishi.dto.ReturnRequestTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhoneControllerTest implements MysqlTestContainer {

    private static final String API_ENDPOINT = "/api/v1/phones";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("Success scenarios")
    class SuccessScenarios {
        @Test
        @DisplayName("Should return all phones")
        void shouldReturnAllPhones() {
            given()
                    .when()
                    .port(port)
                    .get(API_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .body("size()", equalTo(10));
        }

        @Test
        @DisplayName("Should book a phone successfully")
        void shouldBookPhoneSuccessfully() throws JsonProcessingException {
            String bookBy = "jeremy.clark@mail.com";
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new BookRequestDto(bookBy)))
                    .patch(API_ENDPOINT + "/2/book")
                    .then()
                    .statusCode(200)
                    .body("availability", equalTo(false))
                    .body("bookedBy", equalTo(bookBy));
        }

        @Test
        @DisplayName("Should return a phone successfully")
        void shouldReturnPhoneSuccessfully() throws JsonProcessingException {
            // setup
            String bookBy = "jeremy.clark@mail.com";
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new BookRequestDto(bookBy)))
                    .patch(API_ENDPOINT + "/3/book")
                    .then()
                    .statusCode(200)
                    .body("availability", equalTo(false))
                    .body("bookedBy", equalTo(bookBy));
            //execute
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new ReturnRequestTo(bookBy)))
                    .patch(API_ENDPOINT + "/3/return")
                    .then()
                    .statusCode(200)
                    //verify
                    .body("availability", equalTo(true))
                    .body("bookedBy", equalTo(""));
        }

    }

    @Nested
    @DisplayName("Failure scenarios")
    class FailureScenarios {
        @Test
        @DisplayName("Should return 404 when booking a non existing phone")
        void shouldReturn404WhenBookingNonExistingPhone() throws JsonProcessingException {
            String bookBy = "jeremy.clark@mail.com";
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new BookRequestDto(bookBy)))
                    .patch(API_ENDPOINT + "/0/book")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Should return bad request when booking an already booked phone")
        void shouldReturnBadRequestWhenBookingAlreadyBookedPhone() throws JsonProcessingException {
            //setup
            String bookBy = "jeremy.clark@mail.com";
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new BookRequestDto(bookBy)))
                    .patch(API_ENDPOINT + "/4/book")
                    .then()
                    .statusCode(200)
                    .body("availability", equalTo(false))
                    .body("bookedBy", equalTo(bookBy));
            //execute
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new BookRequestDto(bookBy)))
                    .patch(API_ENDPOINT + "/4/book")
                    //verify
                    .then()
                    .statusCode(400);
        }

        @Test
        @DisplayName("Should return bad request when returning an already available phone")
        void shouldReturnBadRequestWhenReturningAlreadyAvailablePhone() throws JsonProcessingException {
            // setup
            String bookBy = "jeremy.clark@mail.com";
            //execute
            given()
                    .when()
                    .port(port)
                    .contentType("application/json")
                    .body(objectMapper.writeValueAsString(new ReturnRequestTo(bookBy)))
                    .patch(API_ENDPOINT + "/5/return")
                    .then()
                    //verify
                    .statusCode(400);
        }
    }


}