package db;

import db.DBConnect;
import ecole.Note;
import java.sql.*;

//ici sont definies l'ensemble des fonctions qui permettent aux utilisateurs de communiquer avec la base de données

public class DBUser{

	public static int DBrechercherUser(String log, String pass){
		
		int authentified = 0;
		Statement state1 = null;
		Statement state2 = null;
		ResultSet result1 = null;
		ResultSet result2 = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation des Statements");
		}
		
		try{
			result1 = state1.executeQuery("SELECT * FROM Etudiant where login='"+log+"' and password='"+pass+"'");
			result2 = state2.executeQuery("SELECT * FROM Professeur where login='"+log+"' and password='"+pass+"'");
			
			/*if(result1.next())
				authentified = 1;
			else if(result2.next())
				authentified = 2;*/

			authentified = result1.next() ? 1 : result2.next() ? 2 : 0;

			state1.close();
			state2.close();
			result1.close();
			result2.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		
		return authentified;
	}

	/**************************************************************************************************************** */

	public static String DBgetParam(String nomParam, String nomTable, String nomIdentifiant, String valeurIdentifiant){
		String param = "";
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}
		
		try{
			result = state.executeQuery("SELECT "+nomParam+" FROM "+nomTable+" where "+nomIdentifiant+"='"+valeurIdentifiant+"'");

			if(result.next())
				param = result.getObject(1).toString();

			state.close();
			result.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		
		return param;
	}


	/**************************************************************************************************************** */

	public static String[] DBgetListeNomClassesProfesseur(String log){
		int i = 0;
		String[] liste = new String[5];

		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			for(i=1; i<=5; i++){
	
				result = state.executeQuery("SELECT classe"+i+" FROM Professeur WHERE login ='"+log+"'");

				if(result.next() && result.getObject(1) != null)
					liste[i-1] = result.getObject(1).toString();
			}
			
			state.close();
			result.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		finally{
			return liste;
		}
	}

/**************************************************************************************************************** */

	public static String[] DBgetListeNomMatieresProfesseur(String log){
		int i = 0;
		String[] liste = new String[2];

		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			for(i=1; i<=2; i++){
				result = state.executeQuery("SELECT cours"+i+" FROM Professeur WHERE login ='"+log+"'");

				if(result.next() && result.getObject(1) != null)
					liste[i-1] = result.getObject(1).toString();
			}
			
			state.close();
			result.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		finally{
			return liste;
		}
	}


/**************************************************************************************************************** */

//voir dans openclassrooms comment se connecter en mode ecriture (constructeur avk parametres d'un objet statement)
public static void DBfaireReclamation(String reclamation, int idNote){
	Statement state = null;
	Connection conn = DBConnect.getInstance().getConn();

	try{
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}catch(Exception e){
		System.out.println("Erreur de creation du Statement");
	}

	try{
		state.executeUpdate("UPDATE Note SET reclamation = '"+reclamation+"' WHERE id = "+idNote+"");
		state.close();
	}catch(Exception e){
		System.out.println("Echec de communication avec la base de donnees");
	}
}



/**************************************************************************************************************** */


public static void DBsaisirNote(Note note){
	Statement state = null;
	Connection conn = DBConnect.getInstance().getConn();

	try{
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}catch(Exception e){
		System.out.println("Erreur de creation du Statement");
	}

	try{
		state.executeUpdate("INSERT INTO Note (valeur, typeNote, matiere, loginEtudiant) VALUES ("+note.getValeur()+",'"+String.valueOf(note.getType())+"','"+note.getMatiere().getNom()+"','"+note.getLoginEtudiant()+"')");
		state.close();
	}catch(Exception e){
		System.out.println("Echec de communication avec la base de donnees");
	}
}


/**************************************************************************************************************** */


public static void DBmodifierNote(int idNote, double bonus){
	Statement state = null;
	Connection conn = DBConnect.getInstance().getConn();

	try{
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}catch(Exception e){
		System.out.println("Erreur de creation du Statement");
	}

	try{
		state.executeQuery("UPDATE Note SET valeur = valeur+"+bonus+" WHERE id = "+idNote+"");
		state.close();
	}catch(Exception e){
		System.out.println("Echec de communication avec la base de donnees");
	}
}



/**************************************************************************************************************** */


public static void DBsupprimerNote(int idNote){
	Statement state = null;
	Connection conn = DBConnect.getInstance().getConn();

	try{
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}catch(Exception e){
		System.out.println("Erreur de creation du Statement");
	}

	try{
		state.executeUpdate("DELETE FROM Note WHERE id = "+idNote+"");
		state.close();
		result.close();
	}catch(Exception e){
		System.out.println("Echec de communication avec la base de donnees");
	}
}



/**************************************************************************************************************** */
}