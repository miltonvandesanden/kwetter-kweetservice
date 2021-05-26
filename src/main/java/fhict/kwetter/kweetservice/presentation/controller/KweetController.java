package fhict.kwetter.kweetservice.presentation.controller;

import fhict.kwetter.kweetservice.logic.services.CreateKweetDelegate;
import fhict.kwetter.kweetservice.logic.services.GetKweetsDelegate;
import fhict.kwetter.kweetservice.persistence.model.Kweet;
import fhict.kwetter.kweetservice.presentation.dto.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/kweet")
@RequiredArgsConstructor
public class KweetController extends AbstractController
{
    private final Optional<CreateKweetDelegate> postKweetDelegate;
    private final Optional<GetKweetsDelegate> getKweetsDelegate;

    @GetMapping("/handshake")
    public ResponseEntity<DtoWrapper> getHandshake()
    {
        HandshakeDto handshakeDto = HandshakeDto.builder().build();

        DtoWrapper dtoWrapper = DtoWrapper.builder()
                .data(handshakeDto)
                .build();

        return ResponseEntity.ok(dtoWrapper);
    }

    @PostMapping
    public ResponseEntity<DtoWrapper> postKweet(@RequestBody CreateKweetDto createKweetDto)
    {
        if(postKweetDelegate.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        if(!createKweetDto.isValid())
        {
            return ResponseEntity.unprocessableEntity().build();
        }

        Kweet kweet = Kweet.builder()
                .userId(Long.valueOf(createKweetDto.getUserId()))
                .message(createKweetDto.getMessage())
                .hashtags(extractHashtags(createKweetDto.getMessage(), new ArrayList<>()))
                .build();

        Kweet result = null;
        try
        {
            result = call("KweetService.createKweet", () -> postKweetDelegate.get().createKweet(kweet));
        } catch (Throwable throwable)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        KweetCreatedDto kweetCreatedDto = KweetCreatedDto.builder()
                .userId(String.valueOf(result.getUserId()))
                .message(result.getMessage())
                .hashtags(result.getHashtags())
                .build();

        DtoWrapper dtoWrapper = DtoWrapper.builder()
                .data(kweetCreatedDto)
                .build();

        return new ResponseEntity(dtoWrapper, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<DtoWrapper> getKweets()
    {
        if(getKweetsDelegate.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        List<Kweet> result = null;
        try
        {
            result = call("KweetService.retrieveKweets", () -> getKweetsDelegate.get().retrieveKweets());
        } catch (Throwable throwable)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        DtoList dtoList = DtoList.builder()
                .data(kweetsToKweetDtos(result))
                .build();

        DtoWrapper dtoWrapper = DtoWrapper.builder()
                .data(dtoList)
                .build();

        return ResponseEntity.ok(dtoWrapper);
    }

    protected List<String> extractHashtags(String message, List<String> hashtags)
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
                hashtag = message.substring(0);
            }

            if(hashtag.length() > 1)
            {
                log.info("hashtag: " + hashtag);

                hashtags.add(hashtag);
            }

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

    //MAPPERS
    public List<KweetCreatedDto> kweetsToKweetDtos(List<Kweet> kweets)
    {
        List<KweetCreatedDto> kweetCreatedDtos = new ArrayList<>();

        for (Kweet kweet: kweets)
        {
            kweetCreatedDtos.add(kweetToKweetDto(kweet));
        }

        return kweetCreatedDtos;
    }

    public KweetCreatedDto kweetToKweetDto(Kweet kweet)
    {
        return KweetCreatedDto.builder()
                .userId(String.valueOf(kweet.getUserId()))
                .message(kweet.getMessage())
                .hashtags(kweet.getHashtags())
                .build();
    }
}
