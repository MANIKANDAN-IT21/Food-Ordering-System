package com.example.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.MenuItemDTO;


@FeignClient(name = "menu-service", url = "http://localhost:2222")
public interface MenuServiceClient {
	
	@GetMapping("menu")
	List<MenuItemDTO> getAllMenu();
	
    @GetMapping("menu/restaurant/{restaurantId}")
    List<MenuItemDTO> getMenuByRestaurant(@PathVariable("restaurantId") Long restaurantId);

}
