package fhict.kwetter.kweetservice.controller;

import fhict.kwetter.kweetservice.dto.DtoWrapper;
import fhict.kwetter.kweetservice.dto.HandshakeDto;
import fhict.kwetter.kweetservice.model.Kweet;
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

    @Mock
    private KweetController.CreateKweetDelegate createKweetDelegate;

    @Mock
    private KweetController.RetrieveKweetsDelegate retrieveKweetsDelegate;

    private KweetController kweetController;
    private KweetController emptyKweetController;

    @Before
    public void init()
    {
        handshakeDelegate = mock(KweetController.HandshakeDelegate.class);

        kweetController = new KweetController(Optional.of(handshakeDelegate), Optional.of(createKweetDelegate), Optional.of(retrieveKweetsDelegate));
        emptyKweetController = new KweetController(Optional.empty(), Optional.empty(), Optional.empty());

        HandshakeDto handshakeDTO = HandshakeDto.builder()
                .build();

        when(handshakeDelegate.doHandshake()).thenReturn(handshakeDTO);
    }

    @Test
    public void handshakeTest()
    {
        //ARRANGE
        String expectedMessage = "Service up and running!";
        HttpStatus expectedStatus = HttpStatus.OK;

        String actualMessage;
        HttpStatus actualStatus;

        //ACT
        ResponseEntity<DtoWrapper> responseEntity = kweetController.getHandshake();

        //ASSERT
        actualMessage = ((HandshakeDto)responseEntity.getBody().getData()).getMESSAGE();
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
        ResponseEntity<DtoWrapper> response = emptyKweetController.getHandshake();

        //ASSERT
        actualStatus = response.getStatusCode();

        assertEquals(expectedStatus, actualStatus);
    }
}
