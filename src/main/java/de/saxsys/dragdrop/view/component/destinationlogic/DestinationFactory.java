package de.saxsys.dragdrop.view.component.destinationlogic;

import de.saxsys.dragdrop.model.States;

import java.util.EnumMap;
import java.util.Map;

public class DestinationFactory {

    private static final Map<States, Destination> strategies = new EnumMap<>(States.class);
    private static final Destination DEFAULT_DESTINATION = () -> "";

    public DestinationFactory() {
        strategies.put(States.OPEN, new OpenDestination());
        strategies.put(States.IN_PROGRESS, new InProgessDestination());
    }

    public Destination getDestination(States state) {
        return strategies.get(state);
    }
}
