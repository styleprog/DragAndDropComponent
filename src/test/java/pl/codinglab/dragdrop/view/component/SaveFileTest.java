package pl.codinglab.dragdrop.view.component;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class SaveFileTest {

    private static final String BASIC_DESTINATION = "\\DDTool\\input\\open";

    private DragDropViewModel classUnderTest = new DragDropViewModel();

    @Test
    public void testSaveFilePositive() throws IOException {
        final File fileToBeSaved = new File("src/test/resources/test");
        classUnderTest.saveFile(fileToBeSaved);
        Path savedFile = Paths.get(System.getProperty("user.home") + BASIC_DESTINATION, "test");
        assertSoftly((softly) -> {
            softly.assertThat(savedFile).isNotNull();
            softly.assertThat(savedFile.toFile().getName()).isEqualTo(fileToBeSaved.getName());
            try {
                softly.assertThat(FileUtils.contentEquals(savedFile.toFile(), fileToBeSaved)).isTrue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testSaveFileNegative() {
        final File notExistingFile = new File("src/test/resources/notExistingFile");
        Path fileSameDestination = Paths.get(System.getProperty("user.home") + BASIC_DESTINATION, "test");
        assertThatThrownBy(() -> classUnderTest.saveFile(fileSameDestination.toFile())).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> classUnderTest.saveFile(notExistingFile)).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> classUnderTest.saveFile(null)).isInstanceOf(RuntimeException.class);

    }
}
