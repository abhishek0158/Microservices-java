package com.lcwd.user.service.UserService.service.impl;

import com.lcwd.user.service.UserService.Entities.Hotel;
import com.lcwd.user.service.UserService.Entities.Rating;
import com.lcwd.user.service.UserService.Entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {




        User  user= userRepository.findById(userId)
                .orElseThrow(()->new  ResourceNotFoundException("Given id is not found id="+userId));

        //fetching rating from rating service
        //communication between one service to other


        Rating[] ratings=restTemplate.getForEntity("http://RATING-SERVICE/ratings/users/"+user.getUserId(),
                Rating[].class).getBody();

        List<Rating> ratingList=Arrays.stream(ratings).toList();



     List<Rating> ratingList1= ratingList.stream().map(rating -> {

         //RestTemplate way of calling other service
          //Hotel hotel= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class).getBody();
           //rating.setHotel(hotel);

         //Feign Client way to call other service
         Hotel hotel=hotelService.getHotel(rating.getHotelId());
         
         rating.setHotel(hotel);
          return rating;
       }).toList();

        logger.info("{}",ratings);
        user.setRatings(ratingList1);
        return user;
    }
}
