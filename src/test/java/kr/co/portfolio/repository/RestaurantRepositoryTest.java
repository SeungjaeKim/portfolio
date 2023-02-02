package kr.co.portfolio.repository;

import kr.co.portfolio.entity.RestaurantEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties ={"spring.config.location = classpath:application-test.yml"})
class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("음식점 저장 테스트")
    public void createRestaurantTest(){
        RestaurantEntity entity = new RestaurantEntity();
        entity.setTitle("테스트가게");
        entity.setCategory("음식");
        entity.setAddress("서울");
        entity.setRoadAddress("서울길");
        entity.setHomePageLink("aaa.com");
        entity.setImageLink("image.com");
        entity.setVisitYn("N");
        entity.setVisitCnt(0);
        entity.setLastVisitDate(LocalDate.now());
        RestaurantEntity savedData = restaurantRepository.save(entity);
        System.out.println(savedData.toString());
    }

    @Test
    @DisplayName("음식점 조회")
    public void findBySeqTest() {
        this.createRestaurantTest();
        List<RestaurantEntity> list = restaurantRepository.findBySeq(1L);
        for (RestaurantEntity entity : list) {
            System.out.println(entity.toString());
        }
    }
}