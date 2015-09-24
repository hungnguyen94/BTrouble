package com.sem.btrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.tools.Logger;

@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {

	@Mock private GameEvent event;
	
	@Before
	public void test() {
		Logger.initLog();
		when(event.toString()).thenReturn("Log");
	}
	
	@Test
	public void logTest() {
		Logger.log(event);
	}

}
