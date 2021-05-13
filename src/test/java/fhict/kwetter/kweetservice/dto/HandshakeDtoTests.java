package fhict.kwetter.kweetservice.dto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class HandshakeDtoTests
{
    private final String expectedMessage = "Service up and running!";

    @Test
    public void builderTest()
    {
        //ARRANGE
        String actualMessage;

        //ACT
        HandshakeDto handshakeDTO = HandshakeDto.builder()
                .build();

        actualMessage = handshakeDTO.getMESSAGE();

        //ASSERT
        assertEquals(expectedMessage, actualMessage);
    }
}
