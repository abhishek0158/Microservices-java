package com.lcwd.user.service.HotelService.repositories;


import com.lcwd.user.service.HotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel,String> {
}
