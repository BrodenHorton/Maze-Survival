package me.brody.mazesurvival.event;

import me.brody.mazesurvival.event.eventargs.EventArgs;

import java.io.Serializable;

public interface IListener<T extends EventArgs> extends Serializable {
    void execute(Object sender, T args);
}
