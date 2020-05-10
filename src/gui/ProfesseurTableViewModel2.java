package gui;

import javafx.beans.property.SimpleStringProperty;

public class ProfesseurTableViewModel2 {
	private SimpleStringProperty nom, prenom, classe, type, reclamation, note;
	// private final Double note;

	public ProfesseurTableViewModel2(String nom, String prenom, String classe, String type, String note,
			String reclamation) {
		super();
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.classe = new SimpleStringProperty(classe);
		this.type = new SimpleStringProperty(type);
		this.reclamation = new SimpleStringProperty(reclamation);
		this.note = new SimpleStringProperty(note);
	}

	public String getNom() {
		return nom.get();
	}

	public String getPrenom() {
		return prenom.get();
	}

	public String getClasse() {
		return classe.get();
	}

	public String getType() {
		return type.get();
	}

	public String getReclamation() {
		return reclamation.get();
	}

	public String getNote() {
		return note.get();
	}

	public void setNote(String note) {
		this.note = new SimpleStringProperty(note);
	}

}
