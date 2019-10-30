package pl.codinglab.dragdrop;

import pl.codinglab.dragdrop.view.main.MainView;
import pl.codinglab.dragdrop.view.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends MvvmfxCdiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    @Inject
    private ResourceBundle resourceBundle;


    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMAN);
        launch(args);

    }

    @Override
    public void startMvvmfx(Stage stage) throws Exception {
        LOG.info("Starting the Application");
        MvvmFX.setGlobalResourceBundle(resourceBundle);
        stage.setTitle(resourceBundle.getString("window.title"));
        ViewTuple<MainView, MainViewModel> main = FluentViewLoader.fxmlView(MainView.class).load();
        Scene scene = new Scene(main.getView());
        stage.setScene(scene);
        stage.show();
    }
}
