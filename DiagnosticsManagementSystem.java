package com.diagnostics;

import java.sql.*;
import java.util.Scanner;

public class DiagnosticsManagementSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diagnostics", "root", "root");
			int operation;
			do {
				System.out.println("DIAGNOSTICS MANAGEMENT SYSTEM");
				System.out.println("1. Add Patient");
				System.out.println("2. Add Doctor");
				System.out.println("3. Add Test");
				System.out.println("4. Schedule Appointment");
				System.out.println("5. View Patients");
				System.out.println("6. View Doctors");
				System.out.println("7. View Test");
				System.out.println("8. View Appointments");
				System.out.println("9. Exit");
				System.out.println("Enter Operation number you want to perform:- ");
				operation = sc.nextInt();
				sc.nextLine();
				
				switch (operation) 
				{
				case 1:
					addPatient(connection, sc);
					break;
				case 2:
					addDoctor(connection, sc);
					break;
				case 3:
					addTest(connection, sc);
					break;
				case 4:
                    addAppointment(connection, sc);
                    break;
                case 5:
                    viewPatients(connection);
                    break;
                case 6:
                    viewDoctors(connection);
                    break;
                case 7:
                    viewTests(connection);
                    break;
                case 8:
                    viewAppointments(connection);
                    break;
                case 9:
					System.out.println("Exiting Program.");
					break;
                default:
					System.out.println("Invalid operation. Please enter a valid operation number.");
                    break;
				}
			} 
			while (operation !=9);
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println(" MySQL JDBC driver not found.");
			e.printStackTrace();
		}
		catch (SQLException e) 
        {
            System.out.println("Database error:");
            e.printStackTrace();
        }
		finally 
		{
			try 
			{
				if (connection !=null)
				{
					connection.close();
				}
				sc.close();
			}
			catch (SQLException e) 
			{
				System.out.println("Error closing connection: ");
				e.printStackTrace();
			}
		}
	}
	private static void addPatient(Connection connection, Scanner sc) throws SQLException
	{
		System.out.println("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Gender (M/F): ");
        String gender = sc.nextLine();
        System.out.println("Enter Address: ");
        String address = sc.nextLine();
        
        String insertSql = "INSERT INTO patients (name, phone, email, gender, address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
        insertStatement.setString(1, name);
        insertStatement.setString(2, phone);
        insertStatement.setString(3, email);
        insertStatement.setString(4, gender);
        insertStatement.setString(5, address);
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected > 0)
        {
        	System.out.println("Patient ADDED SUCCESSFULLY!");
        }
        else 
        {
        	System.out.println("FAILED TO ADD PATIENT");
		}
	}
	private static void addDoctor(Connection connection, Scanner sc) throws SQLException
	{
		System.out.println("Enter Doctor Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Gender (M/F): ");
        String gender = sc.nextLine();
        System.out.println("Enter Address: ");
        String address = sc.nextLine();
        System.out.println("Enter Qualification: ");
        String qualification = sc.nextLine();
        
        String insertSql = "INSERT INTO doctors (name, phone, email, gender, address, qualification) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
        insertStatement.setString(1, name);
        insertStatement.setString(2, phone);
        insertStatement.setString(3, email);
        insertStatement.setString(4, gender);
        insertStatement.setString(5, address);
        insertStatement.setString(6, qualification);
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected > 0)
        {
        	System.out.println("Doctor ADDED SUCCESSFULLY!");
        }
        else 
        {
        	System.out.println("FAILED TO ADD Doctor");
		}
	}
	 private static void addTest(Connection connection, Scanner sc) throws SQLException
	 {
		 System.out.println("Enter Test Name:");
	     String testName = sc.nextLine();
	     System.out.println("Enter Price:");
	     double price = sc.nextDouble();
	     sc.nextLine();  
	     System.out.println("Enter Description:"); // details 
	     String description = sc.nextLine();
	     
	     String insertSql = "INSERT INTO tests (test_name, price, description) VALUES (?, ?, ?)";
	     PreparedStatement insertStatement = connection.prepareStatement(insertSql);
	     insertStatement.setString(1, testName);
	     insertStatement.setDouble(2, price);
         insertStatement.setString(3, description);
	     int rowsAffected = insertStatement.executeUpdate();
	     if (rowsAffected > 0)
	     {
	    	 System.out.println("TEST ADDED SUCCESSFULLY!");
	     }
	     else 
	     {
	        System.out.println("FAILED TO ADD TEST");
		 }
	 }
	private static void addAppointment(Connection connection, Scanner sc) throws SQLException
	{
		viewPatients(connection);
        System.out.println("Enter Patient ID:");
        int patientId = sc.nextInt();
        sc.nextLine();  
        viewDoctors(connection);
        System.out.println("Enter Doctor ID:");
        int doctorId = sc.nextInt();
        sc.nextLine();  
        viewTests(connection);
        System.out.println("Enter Test ID:");
        int testId = sc.nextInt();
        sc.nextLine();  
        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
        String appointmentDate = sc.nextLine();
        System.out.println("Enter Appointment Time (HH:MM:SS):");
        String appointmentTime = sc.nextLine();
        
        String insertSql = "INSERT INTO appointments (patient_id, doctor_id, test_id, appointment_date, appointment_time, status) " + "VALUES (?, ?, ?, ?, ?, 'Scheduled')";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
        insertStatement.setInt(1, patientId);
        insertStatement.setInt(2, doctorId);
        insertStatement.setInt(3, testId);
        insertStatement.setString(4, appointmentDate);
        insertStatement.setString(5, appointmentTime);
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected > 0)
        {
        	System.out.println("APPOINTMENT ADDED SUCCESSFULLY!");
        }
        else 
        {
        	System.out.println("FAILED TO ADD APPOINTMENT");
		}
	}
	private static void viewPatients(Connection connection) throws SQLException 
	{
	    String retrieveSql = "SELECT * FROM patients";
	    try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(retrieveSql)) 
	    {
	        while (resultSet.next()) 
	        {
	        	int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String phone = resultSet.getString("phone");
	            String email = resultSet.getString("email");
	            String gender = resultSet.getString("gender");
	            String address = resultSet.getString("address");
	            System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Gender: " + gender + ", Address: " + address);
	        }
	    }
	}
	private static void viewDoctors(Connection connection) throws SQLException 
	{
	    String retrieveSql = "SELECT * FROM doctors";
	    try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(retrieveSql)) 
	    {
	        while (resultSet.next()) 
	        {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String phone = resultSet.getString("phone");
	            String email = resultSet.getString("email");
	            String gender = resultSet.getString("gender");
	            String address = resultSet.getString("address");
	            String qualification = resultSet.getString("qualification");
	            System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Gender: " + gender + ", Address: " + address + ", Qualification: " + qualification);
	        }
	    }
	}
	private static void viewTests(Connection connection) throws SQLException 
	{
	    String retrieveSql = "SELECT * FROM tests";
	    try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(retrieveSql)) 
	    {
	        while (resultSet.next()) 
	        {
	            int testId = resultSet.getInt("test_id");
	            String testName = resultSet.getString("test_name");
	            Double price = resultSet.getDouble("price");
	            String description = resultSet.getString("description");
	            System.out.println("Test ID: " + testId + ", Test Name: " + testName + ", Price: " + price + ", Description: " + description);
	        }
	    }
	}
	private static void viewAppointments(Connection connection) throws SQLException
	{
		String retrieveSql = "SELECT a.appointment_id, p.name AS patient_name, d.name AS doctor_name, t.test_name, a.appointment_date, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.id " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN tests t ON a.test_id = t.test_id";
		try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(retrieveSql))
		{
			while(resultSet.next())
			{
				int appointmentId = resultSet.getInt("appointment_id");
	            String patientName = resultSet.getString("patient_name");
	            String doctorName = resultSet.getString("doctor_name");
	            String testName = resultSet.getString("test_name");
	            Date appointmentDate = resultSet.getDate("appointment_date");
	            Time appointmentTime = resultSet.getTime("appointment_time");
	            String status = resultSet.getString("status");
	            System.out.println("Appointment ID: " + appointmentId + ", Patient: " + patientName + ", Doctor: " + doctorName + ", Test: " + testName + ", Date: " + appointmentDate + ", Time: " + appointmentTime + ", Status: " + status);
			}
		}
	}

}


