package com.lcwd.user.service.HotelService.services.impl;

import com.lcwd.user.service.HotelService.entity.Hotel;
import com.lcwd.user.service.HotelService.entity.Rating;
import com.lcwd.user.service.HotelService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.HotelService.repositories.HotelRepository;
import com.lcwd.user.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
        RestTemplate restTemplate=new RestTemplate();
        Rating ratings[]=restTemplate.getForEntity("http://localhost:8083/ratings",Rating[].class).getBody();

        List<Rating> ratingList1=Arrays.stream(ratings).filter(rating ->{
            if(rating.getHotelId().equals(id)){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
         hotel.setRatingList(ratingList1);
         return hotel;

    }
}
