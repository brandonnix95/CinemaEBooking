package datahandlers;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class ResetPasswordData {
	private Connection connection;
	private ResultSet results;
	public ResetPasswordData(String dbName, String uname, String password) {
      String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, password);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void resetPassword (String code, String password) {
		byte [] salt = generateSalt(16);
		byte[] hashedPass = hash(password,salt);
		String query = "UPDATE users SET password = ?, salt = ? WHERE resetCode = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setBytes(1,hashedPass);
			ps.setBytes(2, salt);
			ps.setString(3,code);
			ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	private byte[] generateSalt(int len) {
		Random rand = new SecureRandom();
		byte[] salt = new byte[len];
		rand.nextBytes(salt);
		return salt;
	} // generateSalt
	
	/**
	 * Generates a random number of finite length -- cannot be greater than 9
	 * @param len
	 * @return
	 */
	public String generateRandomCode() {
		return UUID.randomUUID().toString();
	} 
	
	private byte[] hash(String password, byte[] salt) {
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 10000, 512);
		SecretKeyFactory secretKeyFactory = null;
		byte[] hash = null;
		
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} // try/catch/catch
		return hash;
	
}
	
	public ResultSet userExists(String email) {
		String query = "SELECT * FROM users where email = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,email);
			this.results=ps.executeQuery();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return results;
} 
	public void addCode(String email, String code) {
		String query = "UPDATE users SET resetCode = ? WHERE email = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			String newEmail = "'" + email + "'";
			System.out.println(code);
			
			ps.setString(1, code);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
