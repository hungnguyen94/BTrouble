package com.sem.btrouble.event;

/**
 * BubbleEvent, holds different kinds of bubble events which can be used to
 * write events to log files. Should be used by the model to communicate with
 * the controller.
 */
public enum BubbleEvent implements Event {
    COLLISION_ROPE
}