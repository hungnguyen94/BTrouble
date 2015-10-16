package com.sem.btrouble.event;

/**
 * ControllerEvent, holds different kinds of controller events (e.g. gameover)
 * which can be used to write events to log files.
 */
public enum ControllerEvent implements Event {
    GAMEOVER, GAMESTART, NEXTROOM, RESTARTROOM, OUTOFTIME, GAMEWON, GAMELOST;
}
