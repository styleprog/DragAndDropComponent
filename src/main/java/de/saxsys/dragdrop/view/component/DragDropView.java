package de.saxsys.dragdrop.view.component;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class DragDropView extends Control implements FxmlView<DragDropViewModel> {

    private static final Logger LOG = LoggerFactory.getLogger(DragDropView.class);

    private static final FileChooser.ExtensionFilter FILTER = new FileChooser.ExtensionFilter("Allowed Files", "xml", "csv");

    private static final String BORDER_STYLE = "-fx-border-color: green;";

    @FXML
    private AnchorPane dropTarget;

    @InjectViewModel
    private DragDropViewModel viewModel;

    public void initialize() {
        dropTarget.setOnDragOver(this::handleDragOver);
        dropTarget.setOnDragEntered(this::handleDragEntered);
        dropTarget.setOnDragExited(this::handleDragExited);
        dropTarget.setOnDragDropped(this::handleDragDropped);
    }

    private void handleDragOver(DragEvent event) {
        if (checkIfDragOverPossible(event)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void handleDragEntered(DragEvent event) {
        if (checkIfDragOverPossible(event)) {
            dropTarget.styleProperty().setValue(BORDER_STYLE);
        }
        event.consume();
    }

    private void handleDragExited(DragEvent event) {
        dropTarget.styleProperty().setValue("");
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        event.getDragboard().getFiles().forEach(file -> {
            try {
                viewModel.saveFile(file);
            } catch (IOException e) {
                LOG.error("Error handling the drag dropped event", e);
            }
        });
        event.setDropCompleted(true);
        viewModel.uploadSuccessfullProperty().setValue(true);
        event.getDragboard().clear();
        event.consume();
    }

    boolean checkIfDragOverPossible(DragEvent event) {
        return event.getDragboard().hasFiles() && checkIfFileExtensionMatches(event);
    }

    boolean checkIfFileExtensionMatches(DragEvent event) {
        boolean matches = false;
        for (File file : event.getDragboard().getFiles()) {
            if (FILTER.getExtensions().contains(FilenameUtils.getExtension(file.getName()))) {
                matches = true;
            } else {
                matches = false;
                break;
            }
        }
        return matches;
    }
}
