package ecole;

import db.DBEcole;

public class Note {
	private int id;
	private double valeur;
	private EnumTypeNote type;
	private Matiere matiere;
	private String loginEtudiant;
	private String reclamation = new String();

	public Note() {
	}

	public Note(int identifiant) {
		this.id = identifiant;
		this.valeur = Double.parseDouble(DBEcole.DBgetParam2("valeur", "Note", "id", identifiant));
		this.type = EnumTypeNote.valueOf(DBEcole.DBgetParam2("typeNote", "Note", "id", identifiant));

		this.loginEtudiant = DBEcole.DBgetParam2("loginEtudiant", "Note", "id", identifiant);
		this.reclamation = DBEcole.DBgetParam2("reclamation", "Note", "id", identifiant);
	}

	public void constructMatiere() {
		String nomMatiere = DBEcole.DBgetParam2("matiere", "Note", "id", this.id);
		this.matiere = new Matiere(nomMatiere);
	}

	public void setId(int vid) {
		this.id = vid;
	}

	public void setValeur(double vvaleur) {
		this.valeur = vvaleur;
	}

	public void setType(EnumTypeNote vtype) {
		this.type = vtype;
	}

	public void setMatiere(Matiere vmatiere) {
		this.matiere = vmatiere;
	}

	public void setLogin(String vloginEtudiant) {
		this.loginEtudiant = vloginEtudiant;
	}

	public void setReclamation(String vreclamation) {
		this.reclamation = vreclamation;
	}

	public int getId() {
		return this.id;
	}

	public double getValeur() {
		return this.valeur;
	}

	public EnumTypeNote getType() {
		return this.type;
	}

	public Matiere getMatiere() {
		return this.matiere;
	}

	public String getLoginEtudiant() {
		return this.loginEtudiant;
	}

	public String getReclamation() {
		return this.reclamation;
	}

}