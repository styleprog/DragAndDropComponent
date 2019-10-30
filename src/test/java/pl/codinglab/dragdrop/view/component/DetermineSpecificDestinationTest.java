package pl.codinglab.dragdrop.view.component;

import pl.codinglab.dragdrop.model.States;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class DetermineSpecificDestinationTest {

    private static final String LOCATION_OPEN = "open\\";
    private static final String LOCATION_IN_PROGRESS = "in_progress\\";

    private DragDropViewModel classUnderTest = new DragDropViewModel();

    @Test
    public void testDetermineSpecificDestinationPositive() {
        String specificLocationOpen = classUnderTest.determineSpecificDestination(States.OPEN);
        String specificLocationInProgress = classUnderTest.determineSpecificDestination(States.IN_PROGRESS);
        assertSoftly((softly) -> {
            softly.assertThat(specificLocationOpen).isEqualTo(LOCATION_OPEN);
            softly.assertThat(specificLocationInProgress).isEqualTo(LOCATION_IN_PROGRESS);
        });
    }

    @Test
    public void testDetermineSpecificDestinationNegative() {
        String specificLocation = classUnderTest.determineSpecificDestination(null);
        assertThat(specificLocation).isEmpty();
    }

    @Test
    public void testBindings() {
        IntegerProperty num1 = new SimpleIntegerProperty(5);
        IntegerProperty num2 = new SimpleIntegerProperty(3);
        IntegerProperty num3 = new SimpleIntegerProperty(2);

        final NumberBinding sum = Bindings.add(num1.add(num2), num3);

        num1.addListener((observable, oldValue, newValue) -> System.out.println("number changed"));

        num1.set(22);

    }
}