package fhict.kwetter.kweetservice.messaging;

import fhict.kwetter.kweetservice.messaging.dto.HashtagsDto;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class SenderImpl implements Sender
{
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.messaging.exchange}")
    private String exchange;

    @Value("${spring.messaging.routingkey}")
    private String routingkey;

    public SenderImpl(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(List<String> hashtags)
    {
        HashtagsDto hashtagsDto = HashtagsDto.builder()
                .hashtags(hashtags)
                .build();

        rabbitTemplate.convertAndSend(exchange, routingkey, hashtagsDto);
    }
}
