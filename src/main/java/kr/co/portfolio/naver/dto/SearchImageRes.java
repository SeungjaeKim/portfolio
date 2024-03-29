package kr.co.portfolio.naver.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class SearchImageRes {

    private String lastBuildDate;

    private int total;

    private int start;

    private int display;

    private List<SearchImageItem> items;


    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem{
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
