package kr.co.portfolio.service;

import kr.co.portfolio.entity.RestaurantEntity;
import kr.co.portfolio.model.RestaurantDto;
import kr.co.portfolio.naver.NaverClient;
import kr.co.portfolio.naver.dto.SearchImageReq;
import kr.co.portfolio.naver.dto.SearchImageRes;
import kr.co.portfolio.naver.dto.SearchLocalReq;
import kr.co.portfolio.naver.dto.SearchLocalRes;
import kr.co.portfolio.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final NaverClient naverClient;
    private final RestaurantRepository restaurantRepository;

    public RestaurantDto search(String query) {
        //지역 검색
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        SearchLocalRes searchLocalRes = naverClient.localSearch(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            SearchLocalRes.SearchLocalItem localItem = searchLocalRes.getItems().stream().findFirst().get();
            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            //이미지 검색
            SearchImageRes searchImageRes = naverClient.imageSearch(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                SearchImageRes.SearchImageItem imageItem = searchImageRes.getItems().stream().findFirst().get();

                //결과를 리턴
                RestaurantDto result =  new RestaurantDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;

            }
        }

        return new RestaurantDto();
    }


    public RestaurantDto add(RestaurantDto restaurantDto) {
        RestaurantEntity entity = dtoToEntity(restaurantDto);
        RestaurantEntity saveEntity = restaurantRepository.save(entity);
        return entityToDto(saveEntity);
    }

    private RestaurantEntity dtoToEntity(RestaurantDto restaurantDto){
        RestaurantEntity entity = new RestaurantEntity();
        entity.setSeq(restaurantDto.getIndex());
        entity.setTitle(restaurantDto.getTitle());
        entity.setCategory(restaurantDto.getCategory());
        entity.setAddress(restaurantDto.getAddress());
        entity.setRoadAddress(restaurantDto.getRoadAddress());
        entity.setImageLink(restaurantDto.getImageLink());
        entity.setHomePageLink(restaurantDto.getHomePageLink());
        entity.setVisitCnt(restaurantDto.getVisitCnt());
        entity.setVisitYn(restaurantDto.getVisitYn());
        entity.setLastVisitDate(restaurantDto.getLastVisitDate());
        return entity;
    }

    private RestaurantDto entityToDto(RestaurantEntity restaurantEntity){
        RestaurantDto dto = new RestaurantDto();
        dto.setIndex(restaurantEntity.getSeq());
        dto.setTitle(restaurantEntity.getTitle());
        dto.setCategory(restaurantEntity.getCategory());
        dto.setAddress(restaurantEntity.getAddress());
        dto.setRoadAddress(restaurantEntity.getRoadAddress());
        dto.setImageLink(restaurantEntity.getImageLink());
        dto.setHomePageLink(restaurantEntity.getHomePageLink());
        dto.setVisitCnt(restaurantEntity.getVisitCnt());
        dto.setVisitYn(restaurantEntity.getVisitYn());
        dto.setLastVisitDate(restaurantEntity.getLastVisitDate());
        return dto;
    }

    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(Long index) {
        restaurantRepository.deleteById(index);
    }

    @Transactional
    public void addVisit(Long index){
        Optional<RestaurantEntity> restaurantItem = restaurantRepository.findById(index);
        if(restaurantItem.isPresent()){
            RestaurantEntity item = restaurantItem.get();
            item.setVisitYn("Y");
            item.setVisitCnt(item.getVisitCnt()+1);
            item.setLastVisitDate(LocalDate.now());
        }
    }
}
