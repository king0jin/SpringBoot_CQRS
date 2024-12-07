package naver.et0709.spring_cqrs;

import lombok.Data;

//Controller와 Service사이에 데이터 전달 클래스
@Data
public class BookDTO {
    private String title;
    private String author;
    private String category;
    private int pages;
    private int price;
    private String published_date;
    private String description;
}
