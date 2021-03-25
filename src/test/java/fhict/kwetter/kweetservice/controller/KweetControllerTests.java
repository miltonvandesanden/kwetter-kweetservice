package fhict.kwetter.kweetservice.controller;

import fhict.kwetter.kweetservice.dto.DTOWrapper;
import fhict.kwetter.kweetservice.dto.HandshakeDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class KweetControllerTests
{
    @Mock
    private KweetController.HandshakeDelegate handshakeDelegate;

    private KweetController kweetController;
    private KweetController emptyKweetController;

    @Before
    public void init()
    {
        handshakeDelegate = mock(KweetController.HandshakeDelegate.class);

        kweetController = new KweetController(Optional.of(handshakeDelegate));
        emptyKweetController = new KweetController(Optional.empty());

        HandshakeDTO handshakeDTO = HandshakeDTO.builder()
                .build();

        when(handshakeDelegate.doHandshake()).thenReturn(handshakeDTO);
    }

    @Test
    public void handshakeTest()
    {
        //ARRANGE
        String expectedMessage = "Hello there!";
        HttpStatus expectedStatus = HttpStatus.OK;

        String actualMessage;
        HttpStatus actualStatus;

        //ACT
        ResponseEntity<DTOWrapper> responseEntity = kweetController.getHandshake();

        //ASSERT
        actualMessage = ((HandshakeDTO)responseEntity.getBody().getData()).getMESSAGE();
        actualStatus = responseEntity.getStatusCode();

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void handshakeNoDelegateTest()
    {
        //ARRANGE
        HttpStatus expectedStatus = HttpStatus.NOT_IMPLEMENTED;

        HttpStatus actualStatus;

        //ACT
        ResponseEntity<DTOWrapper> response = emptyKweetController.getHandshake();

        //ASSERT
        actualStatus = response.getStatusCode();

        assertEquals(expectedStatus, actualStatus);
    }
}
