package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ecole.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import user.Etudiant;

public class ControllerEtudiant extends MereCtrl implements Initializable {

	private Etudiant etu = MereCtrl.etu;
	private Stage stage;

	@FXML
	private AnchorPane conteneur2;

	@FXML
	private Button validerBtn;

	@FXML
	private Button deconnecterBtn;

	@FXML
	private TextArea zoneRec;

	@FXML
	private TextArea zoneId;

	@FXML
	private Label labelNom = new Label(), labelPrenom = new Label();

	@FXML
	private TableView<EtudiantTableViewModel> tableau = new TableView<EtudiantTableViewModel>();

	@FXML
	private TableColumn<EtudiantTableViewModel, Integer> col_id;

	@FXML
	private TableColumn<EtudiantTableViewModel, String> col_mat;

	@FXML
	private TableColumn<EtudiantTableViewModel, String> col_typ;

	@FXML
	private TableColumn<EtudiantTableViewModel, Double> col_not;

	@FXML
	private TableColumn<EtudiantTableViewModel, String> col_rec;

	public ObservableList<EtudiantTableViewModel> list;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelNom.setText(etu.getNom());
		labelPrenom.setText(etu.getPrenom());

		ImageView validerImage = new ImageView(
				getClass().getResource("resources/images/confirmBtnImage.png").toExternalForm());
		ImageView deconnecterImage = new ImageView(
				getClass().getResource("resources/images/disconnectBtnImage.png").toExternalForm());
		validerBtn.setGraphic(validerImage);
		deconnecterBtn.setGraphic(deconnecterImage);

		list = FXCollections.observableArrayList();

		col_id.setCellValueFactory(new PropertyValueFactory<EtudiantTableViewModel, Integer>("id"));
		col_mat.setCellValueFactory(new PropertyValueFactory<EtudiantTableViewModel, String>("matiere"));
		col_typ.setCellValueFactory(new PropertyValueFactory<EtudiantTableViewModel, String>("type"));
		col_not.setCellValueFactory(new PropertyValueFactory<EtudiantTableViewModel, Double>("note"));
		col_rec.setCellValueFactory(new PropertyValueFactory<EtudiantTableViewModel, String>("reclamation"));

		etu.constructListeNotes();
		Note[] notes = etu.getListeNotes();

		for (Note elt : notes) {
			elt.constructMatiere();
			list.add(new EtudiantTableViewModel(elt.getId(), elt.getMatiere().getNom(), String.valueOf(elt.getType()),
					elt.getValeur(), elt.getReclamation()));
		}

		tableau.setItems(list);
	}

	@FXML
	protected void handleValiderButtonAction(ActionEvent event) throws IOException {
		Window owner = validerBtn.getScene().getWindow();
		int id;
		String reclamation;
		String errorMsg = new String();

		if (zoneId.getText().isEmpty()) {
			errorMsg = "Le champ \"id\" est obligatoire pour faire une réclamation!\n";
			if (zoneRec.getText().isEmpty()) {
				errorMsg = "Les champs \"id\" et \"Reclamation\" sont obligatoires pour faire une réclamation!\n";
			}
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", errorMsg);
			return;
		}

		if (zoneRec.getText().isEmpty()) {
			errorMsg = "Le champ \"Reclamation\" est obligatoire pour faire une réclamation!\n";
			if (zoneId.getText().isEmpty()) {
				errorMsg = "Les champs \"id\" et \"Reclamation\" sont obligatoires pour faire une réclamation!\n";
			}
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", errorMsg);
			return;
		}

		try {
			id = Integer.parseInt(zoneId.getText());
		} catch (NumberFormatException e) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Le champ \"id\" doit contenir un entier !");
			return;
		}

		boolean parmi = false;
		for (Note elt : etu.getListeNotes()) {
			parmi = parmi || (id == elt.getId());
		}

		if (!parmi) {
			errorMsg = "Cet \"id\" ne référence aucune de vos notes !";
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", errorMsg);
			return;
		}

		reclamation = zoneRec.getText();

		etu.faireReclamation(reclamation, new Note(id));
		tableau.getItems().removeAll(list);
		etu.constructListeNotes();
		Note[] notes = etu.getListeNotes();

		for (Note elt : notes) {
			elt.constructMatiere();
			list.add(new EtudiantTableViewModel(elt.getId(), elt.getMatiere().getNom(), String.valueOf(elt.getType()),
					elt.getValeur(), elt.getReclamation()));
		}
		AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Operation reussie!",
				"Reclamation enregistrée avec succès! Le Professeur concerné en sera notifié.");
		return;
	}

	@FXML
	protected void handleDeconnecterButtonAction(ActionEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("resources/fxml/Authentification.fxml"));
		fxml.getStylesheets().add(getClass().getResource("/css/styleAuthentification.css").toString());
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(fxml, 800, 500));
		stage.setResizable(false);
		stage.show();
	}

}
