package ch.makery.address.view;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {

    }

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(DateUtil.format(person.getBirthday()));
		birthdayField.setPromptText("dd.MM.yyyy");
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()){
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());
			person.setBirthday(DateUtil.parse(birthdayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {

		String errorMessage = "";
		
		if(firstNameField==null || firstNameField.getText().length()==0){
			errorMessage += "Nom non valide ! \n";
		}
		if(lastNameField==null || lastNameField.getText().length()==0){
			errorMessage += "Prénom non valide ! \n";
		}
		if(streetField==null || streetField.getText().length()==0){
			errorMessage += "Rue non valide ! \n";
		}		
		if(postalCodeField==null || postalCodeField.getText().length()==0){
			errorMessage += "Code postal non valide ! \n";
		} else {
			try{
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e){
				errorMessage += "Code postal non valide (saisir un entier) ! \n";
			}
		}
		if(cityField==null || cityField.getText().length()==0){
			errorMessage += "Ville non valide ! \n";
		}		
		if(birthdayField==null || birthdayField.getText().length()==0){
			errorMessage += "Date de naissance non valide ! \n";
		} else {
			if(!DateUtil.validDate(birthdayField.getText())){
				errorMessage += "Date de naissance non valide (Utilisez le format dd.mm.yyy ! \n";
			}
		}
		
		if (errorMessage.length()==0){
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Champs invalide(s)");
			alert.setHeaderText("Veuillez corriger les erreurs");
    		alert.setContentText(errorMessage);
    		alert.showAndWait();			
			return false;
		}
	}
    
    
}
