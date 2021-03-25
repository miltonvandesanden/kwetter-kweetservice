package fhict.kwetter.kweetservice.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DTOWrapperTests
{
    @Mock
    private HandshakeDTO expectedHandshakeDTO;

    @Before
    public void init()
    {
        expectedHandshakeDTO = mock(HandshakeDTO.class);
        when(expectedHandshakeDTO.getMESSAGE()).thenReturn("Hello there!");
    }

    @Test
    public void getAndSetTest()
    {
        //ARRANGE
        DTOWrapper<HandshakeDTO> dtoWrapper;

        //ACT
        dtoWrapper = DTOWrapper.<HandshakeDTO>builder().build();
        dtoWrapper.setData(expectedHandshakeDTO);

        HandshakeDTO actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }

    @Test
    public void allArgsConstructorTest()
    {
        //ARRANGE
        DTOWrapper<HandshakeDTO> dtoWrapper;
        HandshakeDTO actualHandshakeDTO;

        //ACT
        dtoWrapper = new DTOWrapper<>(expectedHandshakeDTO);

        actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }

    @Test
    public void builderTest()
    {
        //ARRANGE
        DTOWrapper<HandshakeDTO> dtoWrapper;
        HandshakeDTO actualHandshakeDTO;

        //ACT
        dtoWrapper = DTOWrapper.<HandshakeDTO>builder()
                .data(expectedHandshakeDTO)
                .build();

        actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }
}
