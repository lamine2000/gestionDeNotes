package ecole;
import db.DBEcole;
import ecole.EnumTypeNote;
import ecole.Matiere;

public class Note{
	private int id;
    private double valeur;
    private EnumTypeNote type;
    private Matiere matiere;
    private String loginEtudiant;
	private String reclamation;
    
    public Note() {}
    public Note(int identifiant/*double vvaleur, EnumTypeNote vtype, Matiere vmatiere, String vloginEtudiant*/) {
		this.id = identifiant;
		this.valeur = Double.parseDouble(DBEcole.DBgetParam2("valeur", "Note", "id", identifiant));
		this.type = EnumTypeNote.valueOf(DBEcole.DBgetParam2("typeNote", "Note", "id" ,identifiant));
		
		String nomMatiere = DBEcole.DBgetParam2("matiere", "Note", "id" ,identifiant);
		this.matiere = new Matiere(nomMatiere);
		
		this.loginEtudiant = DBEcole.DBgetParam2("loginEtudiant", "Note", "id" ,identifiant);
		this.reclamation = DBEcole.DBgetParam2("reclamation", "Note", "id" ,identifiant);
	}
	
	public int getId(){return this.id;}
	public double getValeur(){return this.valeur;}
	public EnumTypeNote getType(){return this.type;}
	public Matiere getMatiere(){return this.matiere;}
	public String getLoginEtudiant(){return this.loginEtudiant;}
	public String getReclamation(){return this.reclamation;}

}