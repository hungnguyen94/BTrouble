//package com.sem.btrouble;
//
//import com.sem.btrouble.model.Game;
//import com.sem.btrouble.model.Player;
//import com.sem.btrouble.model.Room;
//import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.GameContainer;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.state.StateBasedGame;
//
///**
// * Created by hung on 7-10-15.
// */
//public class Test extends StateBasedGame {
//
//    /**
//     * Init the Slickapp.
//     *
//     * @param gamename
//     *            is the name of the game
//     */
//    public Test(String gamename) {
//        super(gamename);
//    }
//
//    public static void main(String[] args) {
//        try {
//            AppGameContainer appgc;
//            appgc = new AppGameContainer(new Test("Bubble Trouble"));
//            appgc.setDisplayMode(1280, 720, false);
//            appgc.setShowFPS(false);
//            appgc.setVSync(true);
//            appgc.setTargetFrameRate(60);
//            appgc.setAlwaysRender(true);
//            appgc.start();
//        } catch (SlickException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void initStatesList(GameContainer gameContainer) throws SlickException {
//        this.addState(new TestState());
//    }
//}
