<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Student Tracker App</title>
<link type="css/text" rel="stylesheet" href="css/style.css" >
</head>  



<body>

	<div id="wrapper">
		<div id="header">
			<h2>XYZ University</h2>
		</div>
	
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Add Student Button -->
			<input type="button" value="Add Student"
				onclick="window.location.href='add-student-form.jsp';return false;"
				class="add-student-button" />
			<table border="1">
				<tr>
					<th>First Name</th>
					<th>Last Name </th>
					<th>Email </th>
				</tr>
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
					<tr>
						<td> ${tempStudent.firstName } </td>
						<td> ${tempStudent.lastName } </td>
						<td> ${tempStudent.email } </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	
	</div>

</body>
</html>