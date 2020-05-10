package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.DBEcole;
import ecole.Classe;
import ecole.Matiere;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import user.Etudiant;
import user.Professeur;

public class ControllerProfesseur extends MereCtrl implements Initializable {

	private Stage stage = new Stage();
	private Professeur prof = MereCtrl.prof;
	private Matiere[] listeCours;
	private Classe[] listeClasses;
	private String matiereChoisie;

	@FXML
	private HBox conteneur3;
	@FXML
	private TabPane tabPane = new TabPane();
	@FXML
	private Tab tab1 = new Tab();
	@FXML
	private TableView<ProfesseurTableViewModel1> tableau1 = new TableView<ProfesseurTableViewModel1>();
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_nom1;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_prenom1;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_tp1;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_cc1;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_ds1;

	@FXML
	private Tab tab2 = new Tab();
	@FXML
	private TableView<ProfesseurTableViewModel1> tableau2 = new TableView<ProfesseurTableViewModel1>();
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_nom2;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_prenom2;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_tp2;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_cc2;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_ds2;

	@FXML
	private Tab tab3 = new Tab();
	@FXML
	private TableView<ProfesseurTableViewModel1> tableau3 = new TableView<ProfesseurTableViewModel1>();
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_nom3;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_prenom3;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_tp3;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_cc3;
	@FXML
	private TableColumn<ProfesseurTableViewModel1, String> col_ds3;

	@FXML
	private Tab tabRec = new Tab();
	@FXML
	private TableView<ProfesseurTableViewModel2> tableauRec = new TableView<ProfesseurTableViewModel2>();
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_nom;
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_prenom;
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_classe;
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_type;
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_note;
	@FXML
	private TableColumn<ProfesseurTableViewModel2, String> col_rec;

	@FXML
	private Button deconnecterBtn;
	@FXML
	private MenuButton menuMatBtn;
	@FXML
	private MenuItem choixMat1, choixMat2;
	@FXML
	private Label labelNom, labelPrenom;
	@FXML
	private Text nomMat;

	public ObservableList<ProfesseurTableViewModel1> list1, list2, list3;
	public ObservableList<ProfesseurTableViewModel2> listRec;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelNom.setText(prof.getNom());
		labelPrenom.setText(prof.getPrenom());
		nomMat.setFill(Color.WHITE);
		nomMat.setVisible(false);

		ImageView deconnecterImage = new ImageView(
				getClass().getResource("resources/images/disconnectBtnImage.png").toExternalForm());
		deconnecterBtn.setGraphic(deconnecterImage);

		prof.constructListeClasses();
		prof.constructListeCours();

		listeCours = prof.getListeCours();
		listeClasses = prof.getListeClasses();

		choixMat1.setText(listeCours[0].getNom());
		if (listeCours.length == 1)
			menuMatBtn.getItems().remove(choixMat2);
		else
			choixMat2.setText(listeCours[1].getNom());

		if (listeClasses.length < 3) {
			tabPane.getTabs().remove(tab3);
			if (listeClasses.length == 1)
				tabPane.getTabs().remove(tab2);
		}

		if (listeClasses.length >= 1) {
			tab1.setText(listeClasses[0].getNom());
			list1 = FXCollections.observableArrayList();
			initialiserTab123(col_nom1, col_prenom1, col_tp1, col_cc1, col_ds1);
		}

		if (listeClasses.length >= 2) {
			tab2.setText(listeClasses[1].getNom());
			list2 = FXCollections.observableArrayList();
			initialiserTab123(col_nom2, col_prenom2, col_tp2, col_cc2, col_ds2);
		}

		if (listeClasses.length == 3) {
			tab3.setText(listeClasses[2].getNom());
			list3 = FXCollections.observableArrayList();
			initialiserTab123(col_nom3, col_prenom3, col_tp3, col_cc3, col_ds3);
		}

		listRec = FXCollections.observableArrayList();
		col_nom.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("nom"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("prenom"));
		col_classe.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("classe"));
		col_type.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("type"));
		col_note.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("note"));
		col_rec.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel2, String>("reclamation"));

		tableauRec.setEditable(true);
		col_note.setCellFactory(TextFieldTableCell.forTableColumn());

		tableau1.setEditable(true);
		col_cc1.setCellFactory(TextFieldTableCell.forTableColumn());
		col_tp1.setCellFactory(TextFieldTableCell.forTableColumn());
		col_ds1.setCellFactory(TextFieldTableCell.forTableColumn());

		tableau2.setEditable(true);
		col_cc2.setCellFactory(TextFieldTableCell.forTableColumn());
		col_tp2.setCellFactory(TextFieldTableCell.forTableColumn());
		col_ds2.setCellFactory(TextFieldTableCell.forTableColumn());

		tableau3.setEditable(true);
		col_cc3.setCellFactory(TextFieldTableCell.forTableColumn());
		col_tp3.setCellFactory(TextFieldTableCell.forTableColumn());
		col_ds3.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	// Event Listener on Button[#deconnecterBtn].onAction
	@FXML
	public void handleDeconnecterButtonAction(ActionEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("resources/fxml/Authentification.fxml"));
		fxml.getStylesheets().add(getClass().getResource("/css/styleAuthentification.css").toString());
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(fxml, 800, 500));
		stage.setResizable(false);
		stage.show();
	}

	// Event Listener on MenuButton[#menuMatBtn].onAction
	@FXML
	public void handleChoixCoursButtonAction(ActionEvent event) {

		matiereChoisie = event.getSource().equals(choixMat1) ? choixMat1.getText() : choixMat2.getText();

		nomMat.setVisible(true);
		nomMat.setText(matiereChoisie);
		remplirTabRec(matiereChoisie);

		remplirTab123(matiereChoisie, tableau1, tab1, list1);
		if (listeClasses.length >= 2) {
			remplirTab123(matiereChoisie, tableau2, tab2, list2);
			if (listeClasses.length == 3)
				remplirTab123(matiereChoisie, tableau3, tab3, list3);
		}
	}

	private void initialiserTab123(TableColumn<ProfesseurTableViewModel1, String> a,
			TableColumn<ProfesseurTableViewModel1, String> b, TableColumn<ProfesseurTableViewModel1, String> c,
			TableColumn<ProfesseurTableViewModel1, String> d, TableColumn<ProfesseurTableViewModel1, String> e) {
		a.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel1, String>("nom"));
		b.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel1, String>("prenom"));
		c.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel1, String>("tp"));
		d.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel1, String>("cc"));
		e.setCellValueFactory(new PropertyValueFactory<ProfesseurTableViewModel1, String>("ds"));
	}

	private void remplirTabRec(String nomMenuItem) {
		tableauRec.getItems().removeAll(listRec);
		for (Classe classe : listeClasses) {
			classe.constructListeEtudiants();
			for (Etudiant etudiant : classe.getListeEtudiants()) {
				etudiant.constructListeNotes();
				etudiant.constructClasse();
				for (Note note : etudiant.getListeNotes()) {
					note.constructMatiere();
					if (note.getMatiere().getNom().equals(nomMenuItem) && !note.getReclamation().isEmpty()) {
						listRec.add(new ProfesseurTableViewModel2(etudiant.getNom(), etudiant.getPrenom(),
								etudiant.getClasse().getNom(), String.valueOf(note.getType()),
								String.valueOf(note.getValeur()), note.getReclamation()));
					}
				}

			}
		}
		tableauRec.setItems(listRec);
	}

	private void remplirTab123(String nomMenuItem, TableView<ProfesseurTableViewModel1> tableau, Tab tab,
			ObservableList<ProfesseurTableViewModel1> list) {
		tableau.getItems().removeAll(list);
		Classe classe = new Classe(tab.getText());
		classe.constructListeEtudiants();
		String[] array1 = new String[2];
		String[] array2 = new String[3];

		Etudiant etudiant;

		for (int i = 0; i < classe.getListeEtudiants().length; i++) {
			etudiant = classe.getListeEtudiants()[i];

			array1 = new String[2];
			array1[0] = etudiant.getNom();
			array1[1] = etudiant.getPrenom();

			array2 = new String[3];
			array2 = DBEcole.DBgetNotes(etudiant.getLogin(), matiereChoisie);

			list.add(new ProfesseurTableViewModel1(array1[0], array1[1], array2[0], array2[1], array2[2]));
		}

		tableau.setItems(list);
	}

	// Event Listener on TableColumn[#col_note].onEditCommit
	@FXML
	public void handleModifierNote(CellEditEvent<?, ?> edittedCell) {
		String type;
		String valeur;

		if (edittedCell.getSource().equals(col_note)) {
			ProfesseurTableViewModel2 noteRec = (ProfesseurTableViewModel2) (edittedCell.getTableView())
					.getSelectionModel().getSelectedItem();
			noteRec.setNote(edittedCell.getNewValue().toString());
			try {
				DBEcole.DBSaveNote(noteRec.getNom(), noteRec.getPrenom(), noteRec.getNote(), noteRec.getType(),
						matiereChoisie);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		else {
			Window owner = labelNom.getScene().getWindow();
			boolean ok = true;

			ProfesseurTableViewModel1 note = (ProfesseurTableViewModel1) (edittedCell.getTableView())
					.getSelectionModel().getSelectedItem();

			try {
				Double.parseDouble((String) edittedCell.getNewValue());
			} catch (NumberFormatException e) {
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "La note saisie doit etre un nombre !");
				ok = false;
				return;
			}

			if (ok) {
				if (Double.parseDouble((String) edittedCell.getNewValue()) > 20
						|| Double.parseDouble((String) edittedCell.getNewValue()) < 0) {
					AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
							"La note saisie doit etre comprise entre 0 et 20 !");
					return;
				}

				if (edittedCell.getSource().equals(col_cc1) || edittedCell.getSource().equals(col_cc2)
						|| edittedCell.getSource().equals(col_cc3)) {
					note.setCc(edittedCell.getNewValue().toString());
					type = "cc";
					valeur = note.getCc();
				}

				else if (edittedCell.getSource().equals(col_tp1) || edittedCell.getSource().equals(col_tp2)
						|| edittedCell.getSource().equals(col_tp3)) {
					note.setTp(edittedCell.getNewValue().toString());
					type = "tp";
					valeur = note.getTp();
				}

				else {
					note.setDs(edittedCell.getNewValue().toString());
					type = "ds";
					valeur = note.getDs();
				}

				try {
					DBEcole.DBSaveNote(note.getNom(), note.getPrenom(), valeur, type, matiereChoisie);
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}

		}
	}

}
