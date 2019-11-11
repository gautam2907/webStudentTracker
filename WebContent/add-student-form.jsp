<!DOCTYPE html>
<html>
<head>
<title> Add Student</title>

<link type="text/css" rel="stylesheet" href="css/style.css" >
<link type="text/css" rel="stylesheet" href="css/add-student-style.css" >

</head>

<body>
	<div id="wrapper">
		<div id="header" > 
			<h2>XYZ University </h2>
		</div>
	</div>
	
	<div id="container">
		<h3> Add Student</h3>
		
		<form action="StudentControllerServlet" method="Get">
		`<input type="hidden" name="command" value="ADD">
		<table>
		
			<tbody>
				<tr>
					<td><label>Fist Name :</label></td>
					<td><input type="text" name="firstname" /></td>
				</tr>
				
				<tr>
					<td><label>Last Name :  </label></td>
					<td><input type="text" name="lastname" /> </td>
 				</tr>
 				
 				<tr>
 					<td><label>Email : </label></td>
 					<td><input type="text" name="email" /></td>		
 					
 				</tr>
 				
 				<tr>
 					<td><label></label></td>
 					<td><input type="submit" value="Save" class="save" /></td>
 				</tr> 
			</tbody>
		</table>
		</form>
		
		<p>
			<a href="StudentControllerServlet">Back to the List</a>
		</p>
	</div>

</body>
</html>