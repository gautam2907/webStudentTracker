package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
		
	}
	
	//Method to list the Students
	public List<Student> getStudents() throws Exception{
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql ="Select * from student order by last_name";
		
		myStmt = myConn.createStatement();
		
		//execute query
		myRs = myStmt.executeQuery(sql);
		
		//process result set
		while(myRs.next()) {
			// retrieve data from result set row
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			//create new student object
			Student tempStudent = new Student(id,firstName,lastName,email);
			
			//add it to the list of students
			students.add(tempStudent);
		}
			
			
		return students;
		}
		finally {
		//close the jdbc objects
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close(); // doesn't really close it....put back to connection pool
			}
		}
		catch(Exception e){
			e.getStackTrace();
			
		}
		
	}

	public void addStudent(Student theStudent) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
	try {		
		// get db datebase 
		myConn = dataSource.getConnection();
		
		//create sql statements
		String sql = "insert into student " 
				+ "(first_name, last_name , email )" 
				+ "values(?,?,?)" ;
		
		myStmt = myConn.prepareStatement(sql);
					
		//set the parameter values for the student
		myStmt.setString(1, theStudent.getFirstName());
		myStmt.setString(2, theStudent.getLastName());
		myStmt.setString(3, theStudent.getEmail());
		
		
		
		
		//execute sql insert
		myStmt.execute();
		}
	
		finally {
			//close JDBc objects
			close(myConn,myStmt,null);
		}
		
		
	}

}
