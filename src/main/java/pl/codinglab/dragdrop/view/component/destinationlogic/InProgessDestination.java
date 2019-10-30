package pl.codinglab.dragdrop.view.component.destinationlogic;

public class InProgessDestination implements Destination {

    private static final String IN_PROGRESS = "in_progress\\";

    @Override
    public String createDestination() {
        return IN_PROGRESS;
    }
}
