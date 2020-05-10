package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import user.Etudiant;
import user.Professeur;
import user.Utilisateur;

public class ControllerAuthentification extends MereCtrl implements Initializable {

	private Stage stage;
	private String log, pass;

	@FXML
	private TextField loginField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private AnchorPane conteneur;

	@FXML
	private GridPane gridPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ImageView registerImage = new ImageView(
				getClass().getResource("resources/images/registerBtnImage.png").toExternalForm());
		submitButton.setGraphic(registerImage);
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
		Window owner = submitButton.getScene().getWindow();
		if (loginField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Veuillez entrer votre login");
			return;
		}
		if (passwordField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Veuillez saisir votre mot de passe");
			return;
		}

		log = loginField.getText();
		pass = passwordField.getText();

		int typeUser = Utilisateur.connecterUser(log, pass);

		if (typeUser != 0)
			loadUI(typeUser, event);

		else {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
					"Aucun n'utilisateur ne correspond à ces identifiants !");
			loginField.clear();
			passwordField.clear();
			return;
		}
	}

	private void loadUI(int typeUser, ActionEvent event) throws IOException {
		if (typeUser == 1)
			loadEtudiantUI(event);
		else if (typeUser == 2)
			loadProfesseurUI(event);
	}

	private void loadEtudiantUI(ActionEvent event) throws IOException {
		MereCtrl.etu = new Etudiant(log);
		Parent fxml = FXMLLoader.load(getClass().getResource("resources/fxml/EtudiantUI.fxml"));
		fxml.getStylesheets().add(getClass().getResource("/css/styleEtudiant.css").toString());
		switchScene(fxml, event);
	}

	private void loadProfesseurUI(ActionEvent event) throws IOException {
		MereCtrl.prof = new Professeur(log);
		Parent fxml = FXMLLoader.load(getClass().getResource("resources/fxml/ProfesseurUI.fxml"));
		fxml.getStylesheets().add(getClass().getResource("/css/styleProfesseur.css").toString());
		switchScene(fxml, event);
		stage.setResizable(true);
	}

	private void switchScene(Parent fxmlResource, ActionEvent event) throws IOException {

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(fxmlResource);
		stage.setScene(scene);
		stage.show();
	}
}