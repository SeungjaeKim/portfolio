package kr.co.portfolio.naver;

import kr.co.portfolio.naver.dto.SearchImageReq;
import kr.co.portfolio.naver.dto.SearchImageRes;
import kr.co.portfolio.naver.dto.SearchLocalReq;
import kr.co.portfolio.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes localSearch(SearchLocalReq searchLocalReq){
        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchLocalRes> responseType = new ParameterizedTypeReference<SearchLocalRes>() {};

        ResponseEntity<SearchLocalRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    public SearchImageRes imageSearch(SearchImageReq searchImageReq){
        URI uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchImageRes> responseType = new ParameterizedTypeReference<SearchImageRes>() {};

        ResponseEntity<SearchImageRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
