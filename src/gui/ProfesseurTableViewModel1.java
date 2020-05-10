package gui;

import javafx.beans.property.SimpleStringProperty;

public class ProfesseurTableViewModel1 {
	private SimpleStringProperty nom, prenom, tp, cc, ds;
	// private final Double tp, cc, ds;

	public ProfesseurTableViewModel1(String nom, String prenom, String tp, String cc, String ds) {
		super();
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.tp = new SimpleStringProperty(tp);
		this.cc = new SimpleStringProperty(cc);
		this.ds = new SimpleStringProperty(ds);
	}

	public String getNom() {
		return nom.get();
	}

	public String getPrenom() {
		return prenom.get();
	}

	public String getTp() {
		return tp.get();
	}

	public String getCc() {
		return cc.get();
	}

	public String getDs() {
		return ds.get();
	}

	public void setTp(String tp) {
		this.tp = new SimpleStringProperty(tp);
	}

	public void setCc(String cc) {
		this.cc = new SimpleStringProperty(cc);
	}

	public void setDs(String ds) {
		this.ds = new SimpleStringProperty(ds);
	}

}
