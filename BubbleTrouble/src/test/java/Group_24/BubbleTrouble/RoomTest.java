package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {
	
	@Mock private RoomData data;
	private Room room;
	@Mock private int startingposition;
	@Mock private ArrayList<Bubble> bubbles;

	@Before
	public void setUp() {
		when(data.getStartingposition()).thenReturn(startingposition);
		when(data.getBubbles()).thenReturn(bubbles);
		room = new Room(data);
	}

}
