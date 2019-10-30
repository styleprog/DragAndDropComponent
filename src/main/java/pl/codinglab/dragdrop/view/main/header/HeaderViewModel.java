package pl.codinglab.dragdrop.view.main.header;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HeaderViewModel implements ViewModel {

    private StringProperty versionLabel = new SimpleStringProperty();

    public StringProperty versionLabelProperty() {
        return versionLabel;
    }
}