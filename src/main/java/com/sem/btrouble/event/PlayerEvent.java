package com.sem.btrouble.event;

/**
 * PlayerEvent, holds different kinds of player events (e.g. life lost)
 */
public enum PlayerEvent implements Event {
    COLLISION_BUBBLE, COLLISION_LEFTWALL, COLLISION_RIGHTWALL,
        POPBUBBLE, LIFE_LOST, LIFE_GAINED, SHOOT;
}
