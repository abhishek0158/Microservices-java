package com.lcwd.user.service.HotelService.services;

import com.lcwd.user.service.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel Create(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String id);

}
