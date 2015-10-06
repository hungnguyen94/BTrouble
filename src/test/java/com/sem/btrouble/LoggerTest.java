package com.sem.btrouble;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.tools.Logger;

/**
 * Class which test the Logger class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {

    @Mock
    private GameEvent event;

    /**
     * Setup the logger.
     */
    @Before
    public void test() {
	Logger.initLog();
	when(event.toString()).thenReturn("Log");
    }

    /**
     * Verify that the log method, log something.
     */
    @Test
    public void logTest() {
	Logger.log(event);
    }

}
