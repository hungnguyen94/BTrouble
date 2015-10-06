package com.sem.btrouble;

//import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.ExceptionEvent;

/**
 * Class which tests the ExceptionEvent class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionEventTest extends GameEventTest {

    @Mock
    private Object object;
    private ExceptionEvent event;
    // @Mock private Class class1;

    /**
     * Set up the objects to use the tests of GameEventTest.
     */
    @Before
    public void setUp() {
        event = new ExceptionEvent(object, "Test");
        setEvent(event);
        setObject(object);
        setId(0);
        setString("Test");
        // when(object.getClass()).thenReturn(class1);
        // when(class1.getName()).thenReturn("Test");
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void toStringTest() {
        // assertEquals("### <EXCEPTION: Test; Test >", event.toString());
    }

}
