package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.dto.HandshakeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HandshakeServiceTests// extends BaseUnitTests
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
        HandshakeDto expectedHandshakeDTO = HandshakeDto.builder().build();

        HandshakeDto actualHandshakeDTO;

        //ACT
        actualHandshakeDTO = handshakeService.doHandshake();

        //ASSERT
        assertEquals(expectedHandshakeDTO.getMESSAGE(), actualHandshakeDTO.getMESSAGE());
    }
}
