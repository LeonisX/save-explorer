package md.leonis.save.explorer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    //TODO buttons to add address to map; save/load map
    private Map<Integer, String> knownAddresses = new HashMap<Integer, String>() {{
        put(0x401, "X coordinate on map");
        put(0x402, "X coordinate on map");
        put(0x405, "Y coordinate on map");
        put(0x406, "Y coordinate on map");
        // 407
        put(0x408, "Map ID");
        put(0x409, "Map ID");
        put(0x40A, "Direction in dungeon");
        // 40B
        put(0x40C, "Room # in dungeon");
        // 40D 02 → 00 вошёл в подземелье медузы
        // 40E
        // 40F
        // 410
        put(0x411, "Y coordinate on map");
        put(0x412, "Y coordinate on map");
        put(0x413, "X coordinate on map");
        put(0x414, "X coordinate on map");
        //put(0x415, "");
        //put(0x416, "");
        // 417 - Church ID
        put(0x500, "Alisa - alive");
        put(0x501, "Alisa - current HP");
        put(0x502, "Alisa - current MP");
        put(0x503, "Alisa - experience");
        put(0x504, "Alisa - experience");
        put(0x505, "Alisa - level");
        put(0x506, "Alisa - max HP");
        put(0x507, "Alisa - max MP");
        put(0x508, "Alisa - attack");
        put(0x509, "Alisa - defense");
        put(0x50A, "Alisa - weapon ID");
        put(0x50B, "Alisa - armor ID");
        put(0x50C, "Alisa - shield ID");
        //put(0x50D, "Alisa - ");
        put(0x50E, "Alisa - combat spells count");
        put(0x50F, "Alisa - non-combat spells count");

        put(0x510, "Myau - alive");
        put(0x511, "Myau - current HP");
        put(0x512, "Myau - current MP");
        put(0x513, "Myau - experience");
        put(0x514, "Myau - experience");
        put(0x515, "Myau - level");
        put(0x516, "Myau - max HP");
        put(0x517, "Myau - max MP");
        put(0x518, "Myau - attack");
        put(0x519, "Myau - defense");
        put(0x51A, "Myau - weapon ID");
        put(0x51B, "Myau - armor ID");
        put(0x51C, "Myau - shield ID");
        //put(0x51D, "Myau - ");
        put(0x51E, "Myau - combat spells count");
        put(0x51F, "Myau - non-combat spells count");

        put(0x520, "Tylon - alive");
        put(0x521, "Tylon - current HP");
        put(0x522, "Tylon - current MP");
        put(0x523, "Tylon - experience");
        put(0x524, "Tylon - experience");
        put(0x525, "Tylon - level");
        put(0x526, "Tylon - max HP");
        put(0x527, "Tylon - max MP");
        put(0x528, "Tylon - attack");
        put(0x529, "Tylon - defense");
        put(0x52A, "Tylon - weapon ID");
        put(0x52B, "Tylon - armor ID");
        put(0x52C, "Tylon - shield ID");
        //put(0x52D, "Tylon - ");
        put(0x52E, "Tylon - combat spells count");
        put(0x52F, "Tylon - non-combat spells count");

        put(0x530, "Lutz - alive");
        put(0x531, "Lutz - current HP");
        put(0x532, "Lutz - current MP");
        put(0x533, "Lutz - experience");
        put(0x534, "Lutz - experience");
        put(0x535, "Lutz - level");
        put(0x536, "Lutz - max HP");
        put(0x537, "Lutz - max MP");
        put(0x538, "Lutz - attack");
        put(0x539, "Lutz - defense");
        put(0x53A, "Lutz - weapon ID");
        put(0x53B, "Lutz - armor ID");
        put(0x53C, "Lutz - shield ID");
        //put(0x53D, "Lutz - ");
        put(0x53E, "Lutz - combat spells count");
        put(0x53F, "Lutz - non-combat spells count");

        // 0x540-0x5BF ????? cities??? variables???
        put(0x546, "Enemy #1");
        put(0x548, "Enemy #1");
        put(0x549, "Enemy #1");
        put(0x556, "Enemy #2");
        put(0x558, "Enemy #2");
        put(0x559, "Enemy #2");
        put(0x566, "Enemy #3");
        put(0x568, "Enemy #3");
        put(0x569, "Enemy #3");
        put(0x576, "Enemy #4");
        put(0x578, "Enemy #4");
        put(0x579, "Enemy #4");
        put(0x586, "Enemy #5");
        put(0x588, "Enemy #5");
        put(0x589, "Enemy #5");
        
        
        put(0x5C0, "Item");
        put(0x5C1, "Item");
        put(0x5C2, "Item");
        put(0x5C3, "Item");
        put(0x5C4, "Item");
        put(0x5C5, "Item");
        put(0x5C6, "Item");
        put(0x5C7, "Item");
        put(0x5C8, "Item");
        put(0x5C9, "Item");
        put(0x5CA, "Item");
        put(0x5CB, "Item");
        put(0x5CC, "Item");
        put(0x5CD, "Item");
        put(0x5CE, "Item");
        put(0x5CF, "Item");
        put(0x5D0, "Item");
        put(0x5D1, "Item");
        put(0x5D2, "Item");
        put(0x5D3, "Item");
        put(0x5D4, "Item");
        put(0x5D5, "Item");
        put(0x5D6, "Item");
        put(0x5D7, "Item");

        // 0x5D8-0x5DF ???

        put(0x5E0, "Mesetas");
        put(0x5E1, "Mesetas");
        put(0x5E2, "Items count");
        // 0x5E3-0x5EF ???

        //put(0x5F0, "Companions count");

        // 0x600... - events 00 -> 01

        // 0x700... - items in dungeons 00 -> FF

    }};

    private String workDir = "/home/leonis/.Kega Fusion/";
    private String ext = ".sav";

    @FXML
    public ScrollPane mainScrollPane;
    @FXML
    public TextField commentTextField;
    @FXML
    public CheckBox showKnownAddressesCheckBox;

    private byte[] currentSave;
    private byte[] previousSave;

    private File saveStateFile;

    @FXML
    private VBox mainVBox;
    @FXML
    public Label saveFileNameLabel;
    @FXML
    public ListView<String> savesListView;

    @FXML
    private void initialize() throws IOException {
        //System.out.println(hex(255));
        mainVBox = new VBox();
        saveStateFile = new File(workDir + "ps.ssm");
        processFile();
        addSaveToList(saveStateFile.getName());
    }


    @FXML
    public void openButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(workDir));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All saves", "*.sav", "*.ssm"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        saveStateFile = fileChooser.showOpenDialog(savesListView.getScene().getWindow());
        processFile();
        addSaveToList(saveStateFile.getName());
    }


    public void reloadButtonClick() throws IOException {
        processFile();
    }

    private void processFile() throws IOException {
        if (saveStateFile == null) return;
        saveFileNameLabel.setText(saveStateFile.getName());
        currentSave = Files.readAllBytes(saveStateFile.toPath());
        commentTextField.clear();
    }

    public void compareButtonClick() throws IOException {
        if (savesListView.getItems().isEmpty()) return;
        int index = 0;
        if (!savesListView.getSelectionModel().isEmpty()) index = savesListView.getSelectionModel().getSelectedIndex();
        previousSave = Files.readAllBytes(Paths.get(workDir + savesListView.getItems().get(index)));
        compare();
    }

    private void compare() {
        mainVBox.getChildren().clear();
        int size = Math.min(currentSave.length, previousSave.length);
        for (int i = 0; i < size; i++) {
            if (currentSave[i] != previousSave[i]) addAddress(i);
        }
        /*compareRange(0x540, 16);
        compareRange(0x550, 16);
        compareRange(0x560, 16);
        compareRange(0x570, 16);
        compareRange(0x580, 16);
        compareRange(0x590, 16);
        compareRange(0x5A0, 16);
        compareRange(0x5B0, 16);
        compareRange(0x5B0, 16);*/
        mainScrollPane.setContent(mainVBox);
    }

    private void compareRange(int start, int length) {
        String currentRange = range(start, length, currentSave);
        String previousRange = range(start, length, previousSave);
        if (!currentRange.equals(previousRange)) {
            String hexIndex = hex(start, 4);
            String text = hexIndex + ": " + previousRange + " -> " + currentRange;
            TextField textField = new TextField(text);
            textField.setId(hexIndex + "RTextField");
            textField.setPrefWidth(440.0);
            HBox hBox = new HBox(5.0, textField);
            mainVBox.getChildren().add(hBox);
        }
    }

    private void addAddress(int i) {
        if (knownAddresses.containsKey(i) && !showKnownAddressesCheckBox.isSelected()) return;
        String hexIndex = hex(i, 4);
        String previousHexValue = hex(previousSave[i]);
        String currentHexValue = hex(currentSave[i]);
        String text = hexIndex + ": " + previousHexValue + " -> " + currentHexValue;
        TextField textField = new TextField(text);
        textField.setId(hexIndex + "TextField");
        Label label = new Label();
        if (knownAddresses.containsKey(i)) label.setText(knownAddresses.get(i));
        HBox hBox = new HBox(5.0, textField, label);
        mainVBox.getChildren().add(hBox);
    }

    private String generateFileName() {
        String mapX = "x" + hex(currentSave[0x401]) + hex(currentSave[0x402]);
        String mapY = "y" + hex(currentSave[0x405]) + hex(currentSave[0x406]);
        String mapIndex = "m" + hex(currentSave[0x408]) + hex(currentSave[0x409]);
        String dungDirection = "d" + hex(currentSave[0x40A]);
        String unk = "u" + hex(currentSave[0x40B]);
        String dungRoom = "r" + hex(currentSave[0x40C]);
        String unk2 = "u" + hex(currentSave[0x40D]);
        String unk3 = "u" + hex(currentSave[0x415]) + hex(currentSave[0x416]);
        String[] dataArray = {mapX, mapY, mapIndex, dungDirection, unk, dungRoom, unk2, unk3};
        return Arrays.stream(dataArray).collect(Collectors.joining("-"));
    }

    private String hex(byte value) {
        String result = Integer.toHexString(value & 255).toUpperCase();
        if (result.length() == 1) result = "0" + result;
        return result;
    }


    private String hex(int value, int count) {
        String result = Integer.toHexString(value).toUpperCase();
        int length = result.length();
        if (length < count) {
            for (int i = length; i < count; i++) result = "0" + result;
        }
        return result;
    }

    private String range(int start, int length, byte[] data) {
        List<String> result = new LinkedList<>();
        for (int i = start; i < start + length; i++) {
            result.add(hex(data[i]));
        }
        return result.stream().collect(Collectors.joining(" "));
    }


    @FXML
    public void saveButtonClick() throws IOException {
        String fileName = generateFileName();
        if (!commentTextField.getText().isEmpty()) fileName += " - " + commentTextField.getText();
        fileName += ext;
        Files.write(Paths.get(workDir + fileName), currentSave);
        addSaveToList(fileName);
    }

    private void addSaveToList(String fileName) {
        savesListView.getItems().add(0, fileName);
        int count = 10;
        if (savesListView.getItems().size() > count) {
            savesListView.getItems().remove(count);
        }
    }

}
