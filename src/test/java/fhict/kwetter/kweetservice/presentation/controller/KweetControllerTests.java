package fhict.kwetter.kweetservice.presentation.controller;

import fhict.kwetter.kweetservice.logic.services.CreateKweetDelegate;
import fhict.kwetter.kweetservice.logic.services.GetKweetsDelegate;
import fhict.kwetter.kweetservice.presentation.dto.DtoWrapper;
import fhict.kwetter.kweetservice.presentation.dto.HandshakeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class KweetControllerTests// extends BaseUnitTests
{
//    @Mock
//    private HandshakeDelegate handshakeDelegate;

    @Mock
    private CreateKweetDelegate createKweetDelegate;

    @Mock
    private GetKweetsDelegate getKweetsDelegate;

    private KweetController kweetController;
    private KweetController emptyKweetController;

    @Before
    public void init()
    {
//        handshakeDelegate = mock(HandshakeDelegate.class);

        kweetController = new KweetController(/*Optional.of(handshakeDelegate), */Optional.of(createKweetDelegate), Optional.of(getKweetsDelegate));
        //emptyKweetController = new KweetController(/*Optional.empty(), */Optional.empty(), Optional.empty());

        HandshakeDto handshakeDTO = HandshakeDto.builder()
                .build();

//        when(handshakeDelegate.doHandshake()).thenReturn(handshakeDTO);
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

//    @Test
//    public void handshakeNoDelegateTest()
//    {
//        //ARRANGE
//        HttpStatus expectedStatus = HttpStatus.NOT_IMPLEMENTED;
//
//        HttpStatus actualStatus;
//
//        //ACT
//        ResponseEntity<DtoWrapper> response = emptyKweetController.getHandshake();
//
//        //ASSERT
//        actualStatus = response.getStatusCode();
//
//        assertEquals(expectedStatus, actualStatus);
//    }

    @Test
    public void extractHashtagsTest()
    {
        //ARRANGE
        String messageInsert = "This is a #test for #kweets";
        List<String> listInsert = new ArrayList<>();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("#test");
        expectedResult.add("#kweets");

        List<String> actualResult;

        //ACT
        actualResult = kweetController.extractHashtags(messageInsert, listInsert);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void extractHashtagsNoTagsTest()
    {
        //ARRANGE
        String messageInsert = "This is a test for kweets";
        List<String> listInsert = new ArrayList<>();

        List<String> expectedResult = new ArrayList<>();
        List<String> actualResult;

        //ACT
        actualResult = kweetController.extractHashtags(messageInsert, listInsert);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void extractHashtagsMessageNullTest()
    {
        //ARRANGE
        String messageInsert = null;
        List<String> listInsert = new ArrayList<>();

        //ACT
        kweetController.extractHashtags(messageInsert, listInsert);
    }

    @Test(expected = NullPointerException.class)
    public void extractHashtagsListNullTest()
    {
        //ARRANGE
        String messageInsert = "This is a #test for #kweets";
        List<String> listInsert = null;

        //ACT
        kweetController.extractHashtags(messageInsert, listInsert);
    }

    @Test(expected = NullPointerException.class)
    public void extractHashtagsMessageNullListNullTest()
    {
        //ARRANGE
        String messageInsert = null;
        List<String> listInsert = null;

        //ACT
        kweetController.extractHashtags(messageInsert, listInsert);
    }

    @Test
    public void extractHashtagsMessageEmptyTest()
    {
        //ARRANGE
        String messageInsert = "";
        List<String> listInsert = new ArrayList<>();

        List<String> expectedResult = new ArrayList<>();
        List<String> actualResult;

        //ACT
        actualResult = kweetController.extractHashtags(messageInsert, listInsert);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void extractHashtagsMessageHashtagTest()
    {
        //ARRANGE
        String messageInsert = "#";
        List<String> listInsert = new ArrayList<>();

        List<String> expectedResult = new ArrayList<>();
        List<String> actualResult;

        //ACT
        actualResult = kweetController.extractHashtags(messageInsert, listInsert);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}
