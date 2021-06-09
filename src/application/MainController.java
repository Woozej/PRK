package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	ObservableList<PaintingClass> localPaintings = FXCollections.observableArrayList();
		
    @FXML
    private TableView<PaintingClass> paintingsTable;

    @FXML
    private TableColumn<PaintingClass, String> titleColumn;

    @FXML
    private TableColumn<PaintingClass, String> authorColumn;

    @FXML
    private TableColumn<PaintingClass, String> sizeColumn;

    @FXML
    private TableColumn<PaintingClass, Double> surfaceColumn;

    @FXML
    private TableColumn<PaintingClass, Double> weightColumn;

    @FXML
    private TableColumn<PaintingClass, Double> priceColumn;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField authorTF;

    @FXML
    private TextField sizeTF;

    @FXML
    private TextField weightTF;

    @FXML
    private TextField priceTF;
	
    @FXML
    void AddPainting(ActionEvent event) {
    	
    	localPaintings.add(new PaintingClass(titleTF.getText(),authorTF.getText(),sizeTF.getText(),Double.parseDouble(weightTF.getText()),Double.parseDouble(priceTF.getText())));
    	updatePaintings();
    }

    private void updatePaintings() {
    	paintingsTable.setItems(localPaintings);

    }
    @FXML
    void Exit(ActionEvent event) {
    	ViewLoader<GridPane, MainController> viewLoader = new ViewLoader<>("ExitView.fxml");
    	Stage popUpWindow = new Stage();
    	Scene scene = new Scene(viewLoader.getLayout());
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	popUpWindow.setScene(scene);
    	popUpWindow.initOwner(windowStage);
    	popUpWindow.initModality(Modality.APPLICATION_MODAL);
    	popUpWindow.showAndWait();
    }

    @FXML
   	private void initialize () {
       	paintingsTable.setTableMenuButtonVisible(true);
       	titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
       	authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
       	sizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
       	surfaceColumn.setCellValueFactory(cellData -> cellData.getValue().surfaceProperty().asObject());
       	weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
       	priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
    }
	
    @FXML
    void LoadFile(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	File file =fileChooser.showOpenDialog(windowStage);
    	if(file != null) {
			String COMMA_DELIMITER = ",";
			List<List<String>> records = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(COMMA_DELIMITER);
			        records.add(Arrays.asList(values));
			    }
			    for(int i = 0;i<records.size();i++) {
			    	localPaintings.add(new PaintingClass(records.get(i).get(0),records.get(i).get(1),records.get(i).get(2),Double.parseDouble(records.get(i).get(3)),Double.parseDouble(records.get(i).get(4))));
			    }
			    
			}catch (Exception e) {
	
		        System.out.println("Error in CsvFileWriter !!!");
		        e.printStackTrace();
			}
			updatePaintings();
    	}
    }

    @FXML
    void SaveFile(ActionEvent event) throws IOException {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save File");
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	File file = fileChooser.showSaveDialog(windowStage);
    	if(file != null) {
	    	List<String[]> dataLines = new ArrayList<>();
	    	for(int i =0;i<localPaintings.size();i++) {
	    		dataLines.add(new String[] 
	    		    	  { localPaintings.get(i).getTitle(), localPaintings.get(i).getAuthor(), localPaintings.get(i).getSize(), localPaintings.get(i).getWeight().toString(), localPaintings.get(i).getPrice().toString(),""});
	    	}
	    	
	    	try (PrintWriter pw = new PrintWriter(file)) {
	            dataLines.stream()
	              .map(this::convertToCSV)
	              .forEach(pw::println);
	        }
    	}
    }
    private String convertToCSV(String[] data) {
        return Stream.of(data)
          .map(this::escapeSpecialCharacters)
          .collect(Collectors.joining(","));
    }
    
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
