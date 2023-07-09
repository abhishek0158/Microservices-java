package com.lcwd.user.service.HotelService.services.impl;

import com.lcwd.user.service.HotelService.entity.Hotel;
import com.lcwd.user.service.HotelService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.HotelService.repositories.HotelRepository;
import com.lcwd.user.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel Create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
    }
}
