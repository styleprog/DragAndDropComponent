package de.saxsys.dragdrop.view.component.destinationlogic;

public class OpenDestination implements Destination {

    private static final String OPEN = "open\\";

    @Override
    public String createDestination() {
        return OPEN;
    }
}
