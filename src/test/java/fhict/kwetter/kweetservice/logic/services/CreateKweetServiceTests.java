package fhict.kwetter.kweetservice.logic.services;

//import fhict.kwetter.kweetservice.presentation.dto.CreateKweetDto;
//import fhict.kwetter.kweetservice.presentation.dto.KweetCreatedDto;
import fhict.kwetter.kweetservice.messaging.Sender;
import fhict.kwetter.kweetservice.persistence.model.Kweet;
import fhict.kwetter.kweetservice.persistence.repository.KweetRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateKweetServiceTests// extends BaseUnitTests
{
    @Mock
    private KweetRepository kweetRepository;

    @Mock
    private Sender sender;

    private CreateKweetService createKweetService;

    @Before
    public void init()
    {
        kweetRepository = mock(KweetRepository.class);
        sender = mock(Sender.class);
        createKweetService = new CreateKweetService(kweetRepository, sender);
    }

//    @Test
//    public void extractHashtagsTest()
//    {
//        //ARRANGE
//        String messageInsert = "This is a #test for #kweets";
//        List<String> listInsert = new ArrayList<>();
//
//        List<String> expectedResult = new ArrayList<>();
//        expectedResult.add("#test");
//        expectedResult.add("#kweets");
//
//        List<String> actualResult;
//
//        //ACT
//        actualResult = createKweetService.extractHashtags(messageInsert, listInsert);
//
//        //ASSERT
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void extractHashtagsNoTagsTest()
//    {
//        //ARRANGE
//        String messageInsert = "This is a test for kweets";
//        List<String> listInsert = new ArrayList<>();
//
//        List<String> expectedResult = new ArrayList<>();
//        List<String> actualResult;
//
//        //ACT
//        actualResult = createKweetService.extractHashtags(messageInsert, listInsert);
//
//        //ASSERT
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void extractHashtagsMessageNullTest()
//    {
//        //ARRANGE
//        String messageInsert = null;
//        List<String> listInsert = new ArrayList<>();
//
//        //ACT
//        createKweetService.extractHashtags(messageInsert, listInsert);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void extractHashtagsListNullTest()
//    {
//        //ARRANGE
//        String messageInsert = "This is a #test for #kweets";
//        List<String> listInsert = null;
//
//        //ACT
//        createKweetService.extractHashtags(messageInsert, listInsert);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void extractHashtagsMessageNullListNullTest()
//    {
//        //ARRANGE
//        String messageInsert = null;
//        List<String> listInsert = null;
//
//        //ACT
//        createKweetService.extractHashtags(messageInsert, listInsert);
//    }
//
//    @Test
//    public void extractHashtagsMessageEmptyTest()
//    {
//        //ARRANGE
//        String messageInsert = "";
//        List<String> listInsert = new ArrayList<>();
//
//        List<String> expectedResult = new ArrayList<>();
//        List<String> actualResult;
//
//        //ACT
//        actualResult = createKweetService.extractHashtags(messageInsert, listInsert);
//
//        //ASSERT
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void extractHashtagsMessageHashtagTest()
//    {
//        //ARRANGE
//        String messageInsert = "#";
//        List<String> listInsert = new ArrayList<>();
//
//        List<String> expectedResult = new ArrayList<>();
//        List<String> actualResult;
//
//        //ACT
//        actualResult = createKweetService.extractHashtags(messageInsert, listInsert);
//
//        //ASSERT
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    public void createKweetTest()
    {
        //ARRANGE
//        CreateKweetDto createKweetDto = CreateKweetDto.builder()
//                .userId("1")
//                .message("This is a #Test for #Kweets")
//                .build();

        List<String> hashtags = new ArrayList<>();
        hashtags.add("#Test");
        hashtags.add("#Kweets");

        Kweet kweetInsert = Kweet.builder()
                .userId(1L)
                .message("This is a #Test for #Kweets")
                .hashtags(hashtags)
                .build();

//        KweetCreatedDto expectedResult = KweetCreatedDto.builder()
//                .userId("1")
//                .message("This is a #Test for #Kweets")
//                .hashtags(hashtags)
//                .build();

        Kweet expectedResult = Kweet.builder()
                .id(1L)
                .userId(1L)
                .message("This is a #Test for #Kweets")
                .hashtags(hashtags)
                .build();

        Kweet actualResult;

//        KweetCreatedDto actualResult;

//        Kweet kweetInsert = Kweet.builder()
//                .userId(1L)
//                .message("This is a #Test for #Kweets")
//                .hashtags(hashtags)
//                .build();
//
//        Kweet kweetResult = Kweet.builder()
//                .userId(1L)
//                .message("This is a #Test for #Kweets")
//                .hashtags(hashtags)
//                .build();

        when(kweetRepository.save(kweetInsert)).thenReturn(expectedResult);

        //ACT
        actualResult = createKweetService.createKweet(kweetInsert);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void createKweetNullTest()
    {
        //ARRANGE
//        CreateKweetDto createKweetDto = null;
        Kweet kweetInsert = null;

        //ACT
//        createKweetService.createKweet(createKweetDto);
        createKweetService.createKweet(kweetInsert);
    }
}
