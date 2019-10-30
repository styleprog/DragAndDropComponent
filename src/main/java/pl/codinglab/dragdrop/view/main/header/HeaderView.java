package pl.codinglab.dragdrop.view.main.header;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderView implements FxmlView<HeaderViewModel> {

    @InjectViewModel
    private HeaderViewModel viewModel;

    @FXML
    private Label version;

    public void initialize() {
        version.textProperty().bind(viewModel.versionLabelProperty());
    }
}