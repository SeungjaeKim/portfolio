package kr.co.portfolio.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RestaurantDto {

    private Long index;

    private String title;               //음식명, 장소명

    private String category;            //카테고리

    private String address;             //주소

    private String roadAddress;         //도로명

    private String homePageLink;        //홈페이지

    private String imageLink;           //이미지

    private String visitYn;             //방문여부

    private int visitCnt;               //방문횟수

    private LocalDate lastVisitDate;    //최종방문일

}
