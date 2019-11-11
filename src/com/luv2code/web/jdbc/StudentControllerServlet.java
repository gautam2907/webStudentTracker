package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create instance of student db util...and pass in the  datasource object
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
			
		}
		catch(Exception exc){
			throw new ServletException(exc);
			
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//if the command is missing, then default to listing students
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			
			switch(theCommand) {
			
			case "LIST":
				listStudents(request,response);
				break;
				
			case "ADD":
				addStudent(request,response);
				break;
				
			default:
				listStudents(request,response);
			}
			//list the students ....in MVC fashion
			listStudents(request,response);
		} catch (Exception e) {
			
			throw new ServletException(e);	
		}
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read the student info from the data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		//create a new students object
		Student theStudent = new Student(firstName, lastName , email);
		
		//add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		//send back to the main page(the Students list)
		listStudents(request,response);
		
		
	
		
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		
		//add  students to  the request
		request.setAttribute("STUDENT_LIST", students);
		
		//send to jsp(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}

}
