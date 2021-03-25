package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.dto.HandshakeDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HandshakeServiceTests
{
    private HandshakeService handshakeService;

    @Before
    public void init()
    {
        handshakeService = new HandshakeService();
    }

    @Test
    public void doHandshakeTest()
    {
        //ARRANGE
        HandshakeDTO expectedHandshakeDTO = HandshakeDTO.builder().build();

        HandshakeDTO actualHandshakeDTO;

        //ACT
        actualHandshakeDTO = handshakeService.doHandshake();

        //ASSERT
        assertEquals(expectedHandshakeDTO.getMESSAGE(), actualHandshakeDTO.getMESSAGE());
    }
}
