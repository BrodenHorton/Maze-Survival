package me.brody.mazesurvival.event;

import me.brody.mazesurvival.event.eventargs.EventArgs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event<T extends EventArgs> implements Serializable {
    private List<IListener<T>> listeners;

    public Event() {
        listeners = new ArrayList<>();
    }

    public void invoke(Object sender, T args) {
        for(IListener listener : listeners)
            listener.execute(sender, args);
    }

    public void addListener(IListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(IListener<T> listener) {
        listeners.remove(listener);
    }
}
