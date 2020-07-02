package net.TntClient.event.events;

import net.TntClient.event.Event;

public class EventReceiverMessage extends Event {

    public final String text;

    public EventReceiverMessage(final String text) {
        this.text = text;
    }

}
