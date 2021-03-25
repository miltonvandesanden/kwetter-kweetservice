package fhict.kwetter.kweetservice.dto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class HandshakeDTOTests
{
    private final String expectedMessage = "Hello there!";

    @Test
    public void builderTest()
    {
        //ARRANGE
        String actualMessage;

        //ACT
        HandshakeDTO handshakeDTO = HandshakeDTO.builder()
                .build();

        actualMessage = handshakeDTO.getMESSAGE();

        //ASSERT
        assertEquals(expectedMessage, actualMessage);
    }
}
