package me.brody.mazesurvival.event.eventargs;

import java.io.Serializable;

public class EventArgs implements Serializable {
    public static EventArgs EMPTY = new EventArgs();

    public EventArgs() {}

}