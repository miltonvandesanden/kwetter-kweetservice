package fhict.kwetter.kweetservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AbstractControllerTests
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
        boolean actualResult;

        //ACT
        actualResult = abstractController.call("testMethod", this::testMethod);

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void callIsNullTest()
    {
        //ACT
        abstractController.call("", null);
    }
}
