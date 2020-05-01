package datahandlers;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import users.Address;
import users.Card;
import users.Customer;
import users.CustomerStatus;
import users.UserType;

public class CustomerDataHandler {
	private Customer customer;
	private boolean successful;
	private int userID;
	private String confirmCode;
	
	public int getID() { return userID; }
	
	/**
	 * Confirms an account
	 * @param code String confirm
	 */
	public static void confirm(String code) {
		Connection conn = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			st = conn.createStatement();
			st.execute("UPDATE users SET status='ACTIVE' where confirmationCode='"+code+"'");
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	} // confirm
	
	/**
	 * Constructor for CustomerDataHandler. Accesses the DB and attempts to retrieve customer information based on the given email/password combination
	 * @param email String email entered
	 * @param password String password entered
	 */
	public CustomerDataHandler(Customer customer, String email, String password) {
		successful = false;
		this.customer = customer;
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		PreparedStatement prst = null;
		ResultSet prrs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users");
			
			while(rs.next()) {
				int entryID = rs.getInt("UserID");
				String entryEmail = rs.getString("email");
				byte[] entryPassword = rs.getBytes("password");
				byte[] hashedPassword = hash(password, rs.getBytes("salt"));
				
				if(entryEmail.equals(email) && Arrays.equals(entryPassword, hashedPassword)) {
					userID = entryID;
					confirmCode = rs.getString("confirmationCode");
					customer.setFirstName(rs.getString("firstName"), false);
					customer.setLastName(rs.getString("lastName"), false);
					customer.setEmail(email, false);
					customer.setPhone(rs.getString("phone"), false);
					customer.setEnrollForPromotions(rs.getBoolean("enrollForPromotions"), false);
					customer.setStatus(CustomerStatus.valueOf(rs.getString("status")), false);
					customer.setType(UserType.values()[rs.getInt("userType") - 1], false);
					
					String streetAddress = rs.getString("address");
					String suite = rs.getString("addressSuite");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");
					customer.setAddress(new Address(streetAddress, suite, city, state, zip));
					
					prst = conn.prepareStatement("SELECT * FROM creditcard WHERE userID = ?");
					prst.setInt(1, userID);
					prrs = prst.executeQuery();
					
					// NOTE: ONLY GETS FIRST AVAILIBLE CARD--WILL HAVE TO CHANGE IF WE WANT TO GET _ALL_ CARDS
					if(prrs.next()) {
						String name = prrs.getString("ccName");
						Date date = prrs.getDate("expDate");
						
						SecretKeySpec key = new SecretKeySpec(rs.getBytes("salt"), "AES");
						String number = decrypt(prrs.getBytes("ccNumber"), key);
						String zipCode = decrypt(prrs.getBytes("zipCode"), key);
						String cvv = decrypt(prrs.getBytes("cvv"), key);
						
						customer.setCard(new Card(name, number, cvv, date, zipCode));
					} // if
					
					successful = true;
					break;
				} // if
			} // while
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	} // constructor (load from db)
	
	/**
	 * Registers a new Customer to the database
	 * @param customer
	 * @param password
	 */
	public CustomerDataHandler(Customer customer, String password) {
		successful = false;
		this.customer = customer;
		
		Connection conn = null;
		Statement st1 = null;
		Statement st2 = null;
		ResultSet rs = null;
		PreparedStatement prstPass = null;
		PreparedStatement prstSalt = null;
		PreparedStatement prst = null;
		PreparedStatement prstCard = null;
		
		try {
			byte[] salt = generateSalt(16);
			confirmCode = generateConfirmationCode();
			byte[] hashedPassword = hash(password, salt);
			String enrollForPromotions = customer.isEnrolledForPromotions() ? "True" : "False";
			Address address = customer.getAddress();
			Card card = customer.getCard();
			SecretKeySpec key = new SecretKeySpec(salt, "AES");
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			
			prst = conn.prepareStatement("INSERT INTO users (firstName, lastName, email, phone, status, enrollForPromotions, userType, password, salt, confirmationCode, address, city, state, zip, addressSuite) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			prst.setString(1, customer.getFirstName());
			prst.setString(2, customer.getLastName());
			prst.setString(3, customer.getEmail());
			prst.setString(4, customer.getPhone());
			prst.setString(5, customer.getStatus().toString());
			prst.setString(6, enrollForPromotions);
			prst.setInt(7, customer.getType().index);
			prst.setBytes(8, hashedPassword);
			prst.setBytes(9, salt);
			prst.setString(10, confirmCode);
			if(address != null) {
				prst.setString(11, address.getAddress());
				prst.setString(12, address.getCity());
				prst.setString(13, address.getState());
				prst.setString(14, address.getZip());
				prst.setString(15, address.getSuite());
			} else {
				prst.setString(11, "");
				prst.setString(12, "");
				prst.setString(13, "");
				prst.setString(14, "");
				prst.setString(15, "");
			}
			prst.execute();
			
			if(card != null) {
				st1 = conn.createStatement();
				rs = st1.executeQuery("SELECT MAX(UserID) FROM users");
				rs.next();
				int userID = rs.getInt("MAX(UserID)");
				
				prstCard = conn.prepareStatement("INSERT INTO creditcard (ccNumber, userID, expDate, zipCode, ccName, cvv) VALUES (?, ?, ?, ?, ?, ?)");
				prstCard.setBytes(1, encrypt(card.getNumber(), key));
				prstCard.setInt(2, userID);
				prstCard.setDate(3, card.getExp());
				prstCard.setBytes(4, encrypt(card.getZip(), key));
				prstCard.setString(5, card.getName());
				prstCard.setBytes(6, encrypt(card.getCvv(), key));
				prstCard.execute();
			} // if
			
			successful = true;
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
			System.out.println("cnfe error");
			// cnfe likes to not have any messages....
		} finally {
			try {
				if(st1 != null) st1.close();
				if(st2 != null) st2.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(prstPass != null) prstPass.close();
				if(prstSalt != null) prstSalt.close();
				if(prstCard != null) prstCard.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	} // constructor (create)
	
	public boolean verifyPassword(String password) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			
			prst = conn.prepareStatement("SELECT * FROM users WHERE UserID=?");
			prst.setInt(1, userID);
			rs = prst.executeQuery();
			
			if(rs.next()) {
				byte[] entryPassword = rs.getBytes("password");
				byte[] hashedPassword = hash(password, rs.getBytes("salt"));
				
				try {
					return new String(entryPassword, "UTF-8").equals(new String(hashedPassword, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return false;
			} // if
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
			System.out.println("cnfe error");
			// cnfe likes to not have any messages....
		} finally {
			try {
				if(prst != null) prst.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // finally
		
		return false;
	} // verifyPassword
	
	public void updatePassword(String password) {
		Connection conn = null;
		PreparedStatement prst = null;
		PreparedStatement uptd = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			
			prst = conn.prepareStatement("SELECT * FROM users WHERE UserID=?");
			prst.setInt(1, userID);
			rs = prst.executeQuery();
			
			if(rs.next()) {
				byte[] hashedPassword = hash(password, rs.getBytes("salt"));
				uptd = conn.prepareStatement("UPDATE users SET password = ? WHERE UserID=?");
				uptd.setBytes(1, hashedPassword);
				uptd.setInt(2, userID);
				
				uptd.execute();
			} // if
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
			System.out.println("cnfe error");
			// cnfe likes to not have any messages....
		} finally {
			try {
				if(prst != null) prst.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(uptd != null) uptd.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // finally
	} // updatePassword
	
	/**
	 * Updates most variables (first and last names, email, phone#, status, enrollment for promotions, and usertype)
	 */
	public void update() {
		Connection conn = null;
		PreparedStatement prst = null;
		PreparedStatement cardprst = null;
		PreparedStatement keyprst = null;
		PreparedStatement existprst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			String enrollForPromotions = customer.isEnrolledForPromotions() ? "True" : "False";
			
			prst = conn.prepareStatement("UPDATE users SET firstName=?, lastName=?, email=?, phone=?, enrollForPromotions=?, address=?, city=?, state=?, zip=?, addressSuite=? WHERE UserID=?");
			prst.setString(1, customer.getFirstName());
			prst.setString(2, customer.getLastName());
			prst.setString(3, customer.getEmail());
			prst.setString(4, customer.getPhone());
			prst.setString(5, enrollForPromotions);
			prst.setString(6, customer.getAddress().getAddress());
			prst.setString(7, customer.getAddress().getCity());
			prst.setString(8, customer.getAddress().getState());
			prst.setString(9, customer.getAddress().getZip());
			prst.setString(10, customer.getAddress().getSuite());
			prst.setInt(11, userID);
			
			prst.execute();
			
			if(customer.getCard() != null) {
				keyprst = conn.prepareStatement("SELECT * FROM users WHERE UserID=?");
				keyprst.setInt(1, userID);
				rs = keyprst.executeQuery();
				rs.next();
				byte[] salt = rs.getBytes("salt");
				SecretKeySpec key = new SecretKeySpec(salt, "AES");
				
				existprst = conn.prepareStatement("SELECT * FROM creditcard WHERE userID=?");
				existprst.setInt(1, userID);
				
				if (existprst.execute() == true) {
					cardprst = conn.prepareStatement("UPDATE creditcard SET ccNumber=?, expDate=?, zipCode=?, ccName=?, cvv=? WHERE userID=?");
					cardprst.setBytes(1, encrypt(customer.getCard().getNumber(), key));
					cardprst.setDate(2, customer.getCard().getExp());
					cardprst.setBytes(3, encrypt(customer.getCard().getZip(), key));
					cardprst.setString(4, customer.getCard().getName());
					cardprst.setBytes(5, encrypt(customer.getCard().getCvv(), key));
					cardprst.setInt(6, userID);
				}
				else {
					cardprst = conn.prepareStatement("INSERT INTO creditcard (ccNumber, userID, expDate, zipCode, ccName, cvv) VALUES (?, ?, ?, ?, ?, ?)");
					cardprst.setBytes(1, encrypt(customer.getCard().getNumber(), key));
					cardprst.setInt(2, userID);
					cardprst.setDate(3, customer.getCard().getExp());
					cardprst.setBytes(4, encrypt(customer.getCard().getZip(), key));
					cardprst.setString(5, customer.getCard().getName());
					cardprst.setBytes(6, encrypt(customer.getCard().getCvv(), key));
				}
				
				cardprst.execute();
			} // if
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(prst != null) prst.close();
				if(cardprst != null) cardprst.close();
				if(keyprst != null) keyprst.close();
				if(rs != null) rs.close();
				if(conn != null) conn.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	} // update
	
	public boolean isSuccessful() { return successful; }
	public int getUserID() { return userID; }
	public String getConfirmCode() { return confirmCode; }
	
	/**
	 * Hashes a String password with a given byte[] salt
	 * @param password String password to hash
	 * @param salt byte[] salt to hash with
	 * @return String hashed password
	 */
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
	} // hash
	
	/**
	 * Generates a random salt of the given length
	 * @return byte[] salt
	 */
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
	private String generateConfirmationCode() {
		return UUID.randomUUID().toString();
	} // generateConfirmationCode
	
	private byte[] encrypt(String value, Key key) {
		byte[] result = new byte[0];
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			result = cipher.doFinal(value.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} // try/catch
		
		return result;
	} // encrypt
	
	static String decrypt(byte[] value, Key key) {
		String result = "";
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = new String(cipher.doFinal(value), "UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} // try/catch
		
		return result;
	} // encrypt
} // CustomerDataHandler
