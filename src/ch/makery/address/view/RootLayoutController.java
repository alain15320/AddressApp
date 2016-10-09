package ch.makery.address.view;

import java.io.File;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {


	private MainApp mainApp;






	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	



	@FXML
	private void handleNew() {
		mainApp.getPersonData().clear();
		mainApp.setPersonFilePath(null);
	}
	



	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();


		FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("XML file (*.xml)", "*.xml");

		fileChooser.getExtensionFilters().add(extFiler);


		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file!=null){
			mainApp.loadPersonDataFromFile(file);
		}
	}
	




	@FXML
	private void handleSave(){
		File personFile = mainApp.getPersonFilePath();
		if (personFile != null){
			mainApp.savePersonDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}




	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();


		FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("XML file (*.xml)", "*.xml");

		fileChooser.getExtensionFilters().add(extFiler);


		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());		

		if (file != null) {
			if (!file.getPath().endsWith(".xml")){

				file = new File(file.getPath()+".xml");
			}
			mainApp.savePersonDataToFile(file);			
		}

	}
	
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");
		alert.showAndWait();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
