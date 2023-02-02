package kr.co.portfolio.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@ToString
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

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
