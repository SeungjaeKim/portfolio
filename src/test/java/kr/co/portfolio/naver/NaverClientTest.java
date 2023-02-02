package kr.co.portfolio.naver;

import kr.co.portfolio.naver.dto.SearchImageReq;
import kr.co.portfolio.naver.dto.SearchImageRes;
import kr.co.portfolio.naver.dto.SearchLocalReq;
import kr.co.portfolio.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){

        SearchLocalReq search = new SearchLocalReq();
        search.setQuery("갈비집");

        SearchLocalRes result = naverClient.localSearch(search);

        System.out.println(result.toString());


    }

    @Test
    public void searchImageTest(){
        SearchImageReq searchImageReq = new SearchImageReq();
        searchImageReq.setQuery("갈비집");

        SearchImageRes result = naverClient.imageSearch(searchImageReq);
        System.out.println(result);
    }


}
