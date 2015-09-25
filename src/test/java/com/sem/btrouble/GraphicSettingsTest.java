package com.sem.btrouble;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.model.GraphicSettings;

/**
 * Class which tests the GraphicSettings class.
 * @author Martin
 *
 */
public class GraphicSettingsTest {
	
	private GraphicSettings settings;
	
	/**
	 * Set up the GraphicSettings object.
	 * @throws SlickException which occur when a incorrect background is used
	 */
	@Before
	public void setUp() throws SlickException {
		settings = new GraphicSettings(true, true, 1);
	}

	/**
	 * Test the setCurrentResolution method.
	 */
	@Test
	public void setcurrentResolutionTest() {
		settings.setCurrentResolution(2);
		assertEquals(2, settings.getCurrentResolution());
	}
	
	/**
	 * Test the setAntialiasing method.
	 */
	@Test
	public void setAntialiasingTest() {
		settings.setAntialiasing(false);
		assertFalse(settings.isAntialiasing());
	}
	
	/**
	 * Test the setFullscreen method.
	 */
	@Test
	public void setFullscreenTest() {
		settings.setFullscreen(false);
		assertFalse(settings.isFullscreen());
	}

}
