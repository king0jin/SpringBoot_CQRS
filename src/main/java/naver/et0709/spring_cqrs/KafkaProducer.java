package naver.et0709.spring_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//KafkaProducer - 메세지 송신 클래스
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    //토픽 생성
    private static final String TOPIC = "cqrs-topic";

    //의존성 주입 어노테이션 : Bean 자동 연결
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //Spring이 관리하는 KafkaTemplate 객체를 자동으로 이 클래스의 kafkaTemplate 필드에 주입

    //토픽과 함께 메세지 송신
    public void sendMessage(BookDTO bookDTO){
        String message =
                "{\"bid\":" + "\"" + bookDTO.getBid() + "\""
                        + ", \"title\":" + "\"" + bookDTO.getTitle() +  "\""
                        + ", \"author\":" + "\"" + bookDTO.getAuthor() +  "\""
                        + ", \"category\":" + "\"" + bookDTO.getCategory() +  "\""
                        + ", \"pages\":" + "\"" + bookDTO.getPages() +  "\""
                        + ", \"price\":" + "\"" + bookDTO.getPrice() +  "\""
                        + ", \"published_date\":" + "\"" + bookDTO.getPublished_date().toString() +  "\""
                        + ", \"description\":" + "\"" + bookDTO.getDescription() +  "\""
                        + "}";

        kafkaTemplate.send(TOPIC, message);
    }
}
