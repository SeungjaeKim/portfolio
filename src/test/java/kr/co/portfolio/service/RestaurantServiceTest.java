package kr.co.portfolio.service;

import kr.co.portfolio.model.RestaurantDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void searchTest(){
        RestaurantDto result = restaurantService.search("갈비집");

        System.out.println(result);

        Assertions.assertNotNull(result);
    }
}
