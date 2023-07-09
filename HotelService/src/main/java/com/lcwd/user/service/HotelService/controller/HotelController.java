package com.lcwd.user.service.HotelService.controller;

import com.lcwd.user.service.HotelService.entity.Hotel;
import com.lcwd.user.service.HotelService.services.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.Create(hotel));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getHotel(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }



}
