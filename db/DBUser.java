package db;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.*;

//ici sont definies l'ensemble des fonctions qui permettent aux utilisateurs de communiquer avec la base de données

public class DBUser{

	public static String[] chargerProprietes(String fichierProperties){
		//charge le fichier de proprietes et renvoie ces dernieres dans un tableau de chaines de caracteres

		Properties prop = null;
		FileInputStream in = null;
		String url, user, password, driver, proprietes;

		try{
			prop = new Properties();
			in = new FileInputStream(fichierProperties);
			prop.load(in);
			in.close();
		} catch(Exception e) {
			System.out.println("Echec de chargement du fichier jdbc.properties");
		}

		url = prop.getProperty("jdbc.url");
		user = prop.getProperty("jdbc.user");
		password = prop.getProperty("jdbc.password");
		driver = prop.getProperty("jdbc.driver");
		proprietes = url+"°"+user+"°"+password+"°"+driver;
		return proprietes.split("°");
	}



	public static Connection DBconnect(String[] tab){
		//se sert des parametres de connexion pour se connecter a la base de donnees et
		//renvoie l'objet Connection obtenu
		
		Connection conn = null;
		
		try{
			Class.forName(tab[3]);
			conn = DriverManager.getConnection(tab[0], tab[1], tab[2]);
		} catch(Exception e){
			System.out.println("Echec de connection a la base de donnees");
		}
		return conn;
	}



	public static int DBrechercherUser(String log, String pass){
		
		int authentified = 0;
		Statement state1 = null;
		Statement state2 = null;
		ResultSet result1 = null;
		ResultSet result2 = null;

		String[] tabProprietes = chargerProprietes("jdbc.properties");
		Connection conn = DBconnect(tabProprietes);
		
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
}