package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.ArrayList;

import Group_24.BubbleTrouble.Model;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {

	private ArrayList<Room> rooms;
	
	@Before
	public void setUp() {
		Model.init();
	}
	
	@Test
	public void getRoomHeightTest() {
		assertEquals(Model.getRoomHeight(), 921);
	}
	
	@Test
	public void getRoomWidthTest() {
		assertEquals(Model.getRoomWidth(), 1123);
	}


}
