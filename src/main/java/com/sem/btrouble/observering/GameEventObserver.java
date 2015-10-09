//package com.sem.btrouble.observering;
//
//import com.sem.btrouble.event.GameEvent;
//import com.sem.btrouble.tools.Logger;
//
///**
// * Observe the GameEvents thrown in the game.
// *
// */
//public class GameEventObserver implements EventObserver {
//
//    private boolean consoleLog;
//
//    /**
//     * GameObserver, used to log the observed GameEvents to a file.
//     * 
//     * @param consoleLog
//     *            should be a boolean representing whether the observed
//     *            GameEvents should be logged in the console.
//     */
//    public GameEventObserver(boolean consoleLog) {
//        this.consoleLog = consoleLog;
//        Logger.initLog();
//    }
//    
//    @Override
//    public void update(GameEvent event) {
//        Logger.log(event);
//        if (consoleLog) {
//            System.out.println("Log: " + event.toString());
//        }
//    }
//}
