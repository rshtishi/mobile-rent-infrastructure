package com.githug.rshtishi.rest;

import com.githug.rshtishi.dto.BookRequestDto;
import com.githug.rshtishi.dto.ReturnRequestTo;
import com.githug.rshtishi.entity.Phone;
import com.githug.rshtishi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phones")
@CrossOrigin(origins = "http://localhost:4200")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phones = phoneService.getAllPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @PatchMapping("/{id}/book")
    public ResponseEntity<Phone> bookPhone(@PathVariable("id") long id,@RequestBody BookRequestDto bookRequestDto) {
        Phone rentedPhone = phoneService.bookPhone(id, bookRequestDto.bookBy());
        return new ResponseEntity<>(rentedPhone, HttpStatus.OK);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<Phone> returnPhone(@PathVariable("id") long id, @RequestBody ReturnRequestTo returnRequestTo) {
        Phone updatedPhone = phoneService.returnPhone(id, returnRequestTo.returnBy());
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }
}
