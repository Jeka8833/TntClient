package net.TntClient.event;

import java.lang.reflect.InvocationTargetException;

import net.TntClient.TntClient;

public abstract class Event
{
    private boolean cancelled;

    public Event call()
    {
        this.cancelled = false;
        call(this);
        return this;
    }

    public boolean isCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    private static void call(Event event)
    {
        ArrayHelper<Data> dataList = TntClient.eventManager.get(event.getClass());

        if (dataList != null)
        {
            for (Data data : dataList)
            {
                try
                {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
