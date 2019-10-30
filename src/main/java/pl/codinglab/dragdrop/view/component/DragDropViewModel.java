package pl.codinglab.dragdrop.view.component;

import pl.codinglab.dragdrop.model.States;
import pl.codinglab.dragdrop.view.component.destinationlogic.DestinationFactory;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DragDropViewModel implements ViewModel {

    private static final Logger LOG = LoggerFactory.getLogger(DragDropViewModel.class);
    private static final String BASIC_DESTINATION = "\\DDTool\\input\\";

    private ListProperty<File> files = new SimpleListProperty<>(
            FXCollections.observableArrayList());

    private BooleanProperty uploadSuccessfull = new SimpleBooleanProperty();

    private DestinationFactory destinationFactory = new DestinationFactory();

    void saveFile(File file) throws IOException {
        try {
            Path targetDirectory = Paths.get(System.getProperty("user.home") + BASIC_DESTINATION + determineSpecificDestination(States.OPEN) + file.getName());
            FileUtils.copyFile(file, new File(targetDirectory.toString()));
            LOG.info("File '{}' successfully transferred", file.toPath());
        } catch (IOException e) {
            LOG.error("Error transferring file", e);
            throw e;
        }
    }

    String determineSpecificDestination(States state) {
        return state != null ? destinationFactory.getDestination(state).createDestination() : "";
    }

    public ListProperty<File> filesProperty() {
        return files;
    }

    public BooleanProperty uploadSuccessfullProperty() {
        return uploadSuccessfull;
    }

}