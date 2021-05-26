package fhict.kwetter.kweetservice.presentation.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
public class AbstractControllerTests// extends BaseUnitTests
{
    private AbstractController abstractController;

    private boolean testMethod()
    {
        return true;
    }

    @Before
    public void init()
    {
        abstractController = new AbstractController();
    }

    @Test
    public void callSuccessTest()
    {
        //ARRANGE
        boolean expectedResult = true;
        boolean actualResult = false;

        //ACT
        try
        {
            actualResult = abstractController.call("testMethod", this::testMethod);
        } catch (Throwable throwable)
        {
            fail();
        }

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void callIsNullTest() throws Throwable
    {
        //ACT
        abstractController.call("", null);
    }
}
