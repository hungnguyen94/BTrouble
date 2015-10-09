package com.sem.btrouble.observering;

import com.sem.btrouble.event.GameEvent;

public interface EventSubject {
    
    void fireEvent(GameEvent gameEvent);

    void registerObserver(EventObserver observer);

    void removeObserver(EventObserver observer);
    
}
