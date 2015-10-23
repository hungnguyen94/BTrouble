//package com.sem.btrouble;
//
//import static org.junit.Assert.assertEquals;
//
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.sem.btrouble.model.LifePowerUpAbstract;
//import com.sem.btrouble.model.Model;
//import com.sem.btrouble.model.PowerUpFactory;
//import com.sem.btrouble.model.SlowPowerUp;
//import com.sem.btrouble.model.TimePowerUp;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PowerUpGeneratorTest {
//
//    @Mock private TimePowerUp time;
//    @Mock private SlowPowerUp slow;
//    @Mock private LifePowerUpAbstract life;
//
//    @Before
//    public void setUp() {
//        Model.init(1, 1);
//    }
//
//    @Test
//    public void typeTimeTest() {
//        Model.addShortPowerUp(time);
//        int[] types = {1, 0, 0};
//        assertEquals(PowerUpFactory.getTypes()[0], types[0]);
//    }
//
//    @Test
//    public void typeSlowTest() {
//        Model.addShortPowerUp(slow);
//        int[] types = {0, 1, 0};
//        assertEquals(PowerUpFactory.getTypes()[1], types[1]);
//    }
//
//    @Test
//    public void typeLifeTest() {
//        Model.addShortPowerUp(life);
//        int[] types = {0, 0, 1};
//        assertEquals(PowerUpFactory.getTypes()[2], types[2]);
//    }
//
//    @Test
//    public void generateTimeTest() {
//        Model.clearShortPower();
//        assertEquals(PowerUpFactory.generate(1, 1, 0.05), new TimePowerUp(1, 1));
//    }
//
//    @Test
//    public void generateSlowTest() {
//        Model.clearShortPower();
//        assertEquals(PowerUpFactory.generate(1, 1, 0.15), new SlowPowerUp(1, 1));
//    }
//
//    @Test
//    public void generateLifeTest() {
//        Model.clearShortPower();
//        assertEquals(PowerUpFactory.generate(1, 1, 0.25), new LifePowerUpAbstract(1, 1));
//    }
//
//    @Test
//    public void generateNoneTest() {
//        assertEquals(PowerUpFactory.generate(1, 1, -1), null);
//    }
//
//    @Test
//    public void generateNone2Test() {
//        assertEquals(PowerUpFactory.generate(1, 1, 0.5), null);
//    }
//
//    @Test
//    public void generateFalseTimeTest() {
//        Model.addShortPowerUp(time);
//        assertEquals(PowerUpFactory.generate(1, 1, 0.05), null);
//    }
//
//    @Test
//    public void generateFalseSlowTest() {
//        Model.addShortPowerUp(slow);
//        assertEquals(PowerUpFactory.generate(1, 1, 0.15), null);
//    }
//
//    @Test
//    public void generateFalseLifeTest() {
//        Model.addShortPowerUp(life);
//        assertEquals(PowerUpFactory.generate(1, 1, 0.25), null);
//    }
//
//}
