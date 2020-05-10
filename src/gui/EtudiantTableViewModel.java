package gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EtudiantTableViewModel {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty matiere;
	private final SimpleStringProperty type;
	private final Double note;
	private final SimpleStringProperty reclamation;

	public EtudiantTableViewModel(int id, String matiere, String type, double note, String reclamation) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.matiere = new SimpleStringProperty(matiere);
		this.type = new SimpleStringProperty(type);
		this.note = note;
		this.reclamation = new SimpleStringProperty(reclamation);
	}

	public Integer getId() {
		return id.get();
	}

	public String getMatiere() {
		return matiere.get();
	}

	public String getType() {
		return type.get();
	}

	public Double getNote() {
		return note;
	}

	public String getReclamation() {
		return reclamation.get();
	}

}
