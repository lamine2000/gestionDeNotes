package db;

import java.io.FileInputStream;
import java.util.Properties;
import java.sql.*;

public class DBConnect {
	private volatile static DBConnect single;
	private Connection conn = null;
  
	private DBConnect(){
		  String[] tab = chargerProprietes("jdbc.properties");
		  conn = DBconnecter(tab);
	}
	 
	public static DBConnect getInstance(){
	  if(single == null){
		synchronized(DBConnect.class){
		  if(single == null)
			single = new DBConnect();
		}
	  }      
	  return single;
	}

	public Connection getConn(){
		return this.conn;
	}

	private static String[] chargerProprietes(String fichierProperties){
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

	private static Connection DBconnecter(String[] tab){
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
  }