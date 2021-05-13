package fhict.kwetter.kweetservice.controller;

import fhict.kwetter.kweetservice.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/kweet")
@RequiredArgsConstructor
public class KweetController extends AbstractController
{
    public interface HandshakeDelegate
    {
        HandshakeDto doHandshake();
    }

    public interface CreateKweetDelegate
    {
        KweetCreatedDto createKweet(CreateKweetDto createKweetDto);
    }

    public interface RetrieveKweetsDelegate
    {
        List<KweetCreatedDto> retrieveKweets();
    }

    private final Optional<HandshakeDelegate> getHandshakeDelegateOptional;
    private final Optional<CreateKweetDelegate> postKweetDelegate;
    private final Optional<RetrieveKweetsDelegate> getKweetsDelegate;

    @GetMapping("/handshake")
    public ResponseEntity<DtoWrapper> getHandshake()
    {
        if(getHandshakeDelegateOptional.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        HandshakeDto handshakeDTO = call("KweetService.getHandshake", () -> getHandshakeDelegateOptional.get().doHandshake());

        DtoWrapper dtoWrapper = DtoWrapper.builder()
                .data(handshakeDTO)
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

        KweetCreatedDto kweetCreatedDto = call("KweetService.createKweet", () -> postKweetDelegate.get().createKweet(createKweetDto));

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

        List<KweetCreatedDto> result = call("KweetService.retrieveKweets", () -> getKweetsDelegate.get().retrieveKweets());

        DtoList dtoList = DtoList.builder()
                .data(result)
                .build();

        DtoWrapper dtoWrapper = DtoWrapper.builder()
                .data(dtoList)
                .build();

        return ResponseEntity.ok(dtoWrapper);
    }

//    public ResponseEntity<DtoWrapper> getKweet(@RequestParam String id)
//    {
//
//    }
}
