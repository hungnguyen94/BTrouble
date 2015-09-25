package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.sem.btrouble.model.Resolution;

/**
 * Class which tests the Resolution class.
 * @author Martin
 *
 */
public class ResolutionTest {

	private Resolution resolution = new Resolution(1, 1, "Test");
	
	/**
	 * Test the getBackground method.
	 */
	@Test
	public void getBackgroundTest() {
		assertEquals("Test", resolution.getBackground());
	}
	
	/**
	 * Test the setScreenWidth method.
	 */
	@Test
	public void setScreenWidthTest() {
		resolution.setScreenWidth(2);
		assertEquals(2, resolution.getScreenWidth());
	}
	
	/**
	 * Test the setScreenHeight method.
	 */
	@Test
	public void setScreenHeightTest() {
		resolution.setScreenHeight(2);
		assertEquals(2, resolution.getScreenHeight());
	}
	
	/**
	 * Test the equals method with two equal Resolutions.
	 */
	@Test
	public void equalsTrueTest() {
		assertTrue(resolution.equals(resolution));
	}
	
	/**
	 * Test the equals method with another type.
	 */
	@Test
	public void equalsOtherTest() {
		assertFalse(resolution.equals(new String("Test")));
	}
	
	/**
	 * Test the equals method with a false Height.
	 */
	@Test
	public void equalsHeightTest() {
		assertFalse(resolution.equals(new Resolution(1, 2, "Test")));
	}

	/**
	 * Test the equals method with a false width.
	 */
	@Test
	public void equalsWidthTest() {
		assertFalse(resolution.equals(new Resolution(2, 1, "Test")));
	}
	
	/**
	 * Test the equals method with a false background.
	 */
	@Test
	public void equalsBackgroundTest() {
		assertFalse(resolution.equals(new Resolution(1, 1, "test")));
	}
	
}
