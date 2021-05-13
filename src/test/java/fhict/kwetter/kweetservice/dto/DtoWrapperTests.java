package fhict.kwetter.kweetservice.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DtoWrapperTests// extends BaseUnitTests
{
    @Mock
    private HandshakeDto expectedHandshakeDTO;

    @Before
    public void init()
    {
        expectedHandshakeDTO = mock(HandshakeDto.class);
        when(expectedHandshakeDTO.getMESSAGE()).thenReturn("Hello there!");
    }

    @Test
    public void getAndSetTest()
    {
        //ARRANGE
        DtoWrapper<HandshakeDto> dtoWrapper;

        //ACT
        dtoWrapper = DtoWrapper.<HandshakeDto>builder().build();
        dtoWrapper.setData(expectedHandshakeDTO);

        HandshakeDto actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }

    @Test
    public void allArgsConstructorTest()
    {
        //ARRANGE
        DtoWrapper<HandshakeDto> dtoWrapper;
        HandshakeDto actualHandshakeDTO;

        //ACT
        dtoWrapper = new DtoWrapper(expectedHandshakeDTO);

        actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }

    @Test
    public void builderTest()
    {
        //ARRANGE
        DtoWrapper<HandshakeDto> dtoWrapper;
        HandshakeDto actualHandshakeDTO;

        //ACT
        dtoWrapper = DtoWrapper.<HandshakeDto>builder()
                .data(expectedHandshakeDTO)
                .build();

        actualHandshakeDTO = dtoWrapper.getData();

        //ASSERT
        assertEquals(expectedHandshakeDTO, actualHandshakeDTO);
    }
}
