package naver.et0709.spring_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//서비스와 URL매핑을 위한 Controller클래스
@RestController
@RequiredArgsConstructor
public class BookController {
    //데이터 삽입 서비스 인스턴스 생성
    private final BookService bookService;
    
    //삽입 서비스와 URL매핑
    @PostMapping("/cqrs/book")
    public String saveBook(@RequestBody BookDTO bookDTO){
        bookService.saveBook(bookDTO);
        return "success";
    }
}
