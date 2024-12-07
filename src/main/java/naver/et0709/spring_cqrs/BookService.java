package naver.et0709.spring_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

//사용자 요청 처리를 위한 Service클래스
@Service
@RequiredArgsConstructor
public class BookService {
    //CURD 작업을 하기위한 인스턴스 생성
    private final BookRepository bookRepository;
    //메세지 전송을 하기위한 KafkaProducer 생성
    private final KafkaProducer kafkaProducer;

    //파라미터로 데이터를 전달받아 저장하기
    public void saveBook(BookDTO bookDTO) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //ParseException처리를 강제한다
            Date published_date = formatter.parse(bookDTO.getPublished_date());
            //빌더 패턴을 이용한 Entity생성
            Book book = Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .category(bookDTO.getCategory())
                    .pages(bookDTO.getPages())
                    .price(bookDTO.getPrice())
                    .published_date(published_date)
                    .description(bookDTO.getDescription())
                    .build();
            //데이터 삽입
            bookRepository.save(book);
            //데이터 삽입이 성공하면 book 객체에서 bid 값을 가져와 bookDTO객체에 저장
            bookDTO.setBid(book.getBid());
            //KafkaProducer로 KafKa에 메세지 전송
            kafkaProducer.sendMessage(bookDTO);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
