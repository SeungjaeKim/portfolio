package kr.co.portfolio.controller;

import kr.co.portfolio.model.RestaurantDto;
import kr.co.portfolio.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant/api")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public RestaurantDto search(@RequestParam String query) {
        return restaurantService.search(query);
    }

    @PostMapping("")
    public RestaurantDto add(@RequestBody RestaurantDto restaurantDto){
        log.info("{}", restaurantDto);

        return restaurantService.add(restaurantDto);
    }

    @GetMapping("/all")
    public List<RestaurantDto> findAll(){
        return restaurantService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable Long index) {
        restaurantService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable Long index){
        restaurantService.addVisit(index);
    }

}
