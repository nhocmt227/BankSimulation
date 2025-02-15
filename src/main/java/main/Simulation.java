package src.main.java.main;

import src.main.java.events.Event;

import java.util.List;

/**
 * This class is a general abstract class that
 * encapsulates a simulation.  To implement a
 * simulation, inherit from this class and implement
 * the `getInitialEvents` method.
 */
public abstract class Simulation {
    /**
     * An abstract method to return an array of events
     * used to initialize the simulation.
     *
     * @return An array of initial events that the
     * simulator can use to kick-start the
     * simulation.
     */
    public abstract List<Event> getInitialEvents();
}
