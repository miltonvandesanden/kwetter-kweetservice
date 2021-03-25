package fhict.kwetter.kweetservice.controller;

import fhict.kwetter.kweetservice.dto.DTOWrapper;
import fhict.kwetter.kweetservice.dto.HandshakeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/kweets")
@RequiredArgsConstructor
public class KweetController extends AbstractController
{
    public interface HandshakeDelegate
    {
        HandshakeDTO doHandshake();
    }

    private final Optional<HandshakeDelegate> getHandshakeDelegateOptional;

    @GetMapping("/handshake")
    public ResponseEntity<DTOWrapper> getHandshake()
    {
        if(getHandshakeDelegateOptional.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        HandshakeDTO handshakeDTO = call("KweetService.getHandshake", () -> getHandshakeDelegateOptional.get().doHandshake());

        DTOWrapper dtoWrapper = DTOWrapper.builder()
                .data(handshakeDTO)
                .build();

        return ResponseEntity.ok(dtoWrapper);
    }
}
