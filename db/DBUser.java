//package db;

import db.DBConnect;
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
			state1 = conn.createStatement();
			state2 = conn.createStatement();
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


	public static String DBgetParam(String nomParam, String nomTable, String nomIdentifiant, String valeurIdentifiant){
		String param = "";
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement();
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

	public static String[] DBgetListeNomClassesProfesseur(String log){
		int i = 0;
		String[] liste = new String[5];

		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement();
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}
		
		for(i = 0; i < 5; i++)
			liste[i] = null;

		try{
			for(i=0; i<5; i++){
				result = state.executeQuery("SELECT classe"+i+1+" FROM Professeur WHERE login = "+log);

				if(result.next())
					liste[i] = result.getObject(1).toString();
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


	public static String[] DBgetListeNomMatieresProfesseur(String log){
		int i = 0;
		String[] liste = new String[5];

		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement();
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}
		
		for(i = 0; i < 2; i++)
			liste[i] = null;

		try{
			for(i=0; i<2; i++){
				result = state.executeQuery("SELECT cours"+i+1+" FROM Professeur WHERE login = "+log);

				if(result.next())
					liste[i] = result.getObject(1).toString();
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

	public static void main(String[] args) {
		int a = DBrechercherUser("lmzo", "qsdfjklm");
		System.out.println(a);
	}
}