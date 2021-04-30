package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.controller.KweetController;
import fhict.kwetter.kweetservice.dto.CreateKweetDto;
import fhict.kwetter.kweetservice.dto.KweetCreatedDto;
import fhict.kwetter.kweetservice.model.Kweet;
import fhict.kwetter.kweetservice.repository.KweetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateKweetService implements KweetController.CreateKweetDelegate
{
    private final KweetRepository kweetRepository;

    @Override
    public KweetCreatedDto createKweet(CreateKweetDto createKweetDto)
    {
        Kweet kweet = mapCreateKweetDtoToKweet(createKweetDto);

        Kweet result = kweetRepository.save(kweet);

        return new KweetCreatedDto(result);
    }

    private Kweet mapCreateKweetDtoToKweet(CreateKweetDto createKweetDto)
    {
        return Kweet.builder()
                .userId(Long.valueOf(createKweetDto.getUserId()))
                .message(createKweetDto.getMessage())
                .hashtags(extractHashtags(createKweetDto.getMessage(), new ArrayList<>()))
                .build();
    }

    private List<String> extractHashtags(String message, List<String> hashtags)
    {
        log.info(">> CreateKweetService.extractHashtags");
        log.info("message: " + message);

        if(message.contains("#"))
        {
            int beginIndex = message.indexOf("#");

            log.info("# index: " + beginIndex);

            message = message.substring(beginIndex);

            log.info("message: " + message);

            int endIndex = message.indexOf(" ");

            String hashtag;

            if(endIndex != -1)
            {
                hashtag = message.substring(0, endIndex);
            } else
            {
                //endIndex = message.length()-1;
                hashtag = message.substring(0);
            }

            log.info("hashtag: " + hashtag);

            hashtags.add(hashtag);

            try
            {
                message = message.substring(endIndex);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException)
            {
                return hashtags;
            }

            hashtags = extractHashtags(message, hashtags);
        }

        log.info("<< CreateKweetService.extractHashtags");

        return hashtags;
    }
}
