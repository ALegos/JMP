<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h3>Welcome, Enter The Person Details</h3>
   <form:form method="POST" action="create" modelAttribute="person">
        <table>
           <tr>
               <td><form:label path="name">Name</form:label></td>
               <td><form:input path="name"/></td>
               <td><form:errors path="name" cssStyle="color:red;"/></td>
           </tr>
           <tr>
               <td><form:label path="email">Email</form:label></td>
               <td><form:input path="email"/></td>
               <td><form:errors path="email" cssStyle="color:red;"/></td>
           </tr>
           <tr>
               <td><form:label path="level">Level</form:label></td>
               <td><form:input path="level"/></td>
               <td><form:errors path="level" cssStyle="color:red;"/></td>
           </tr>
           <tr>
               <td><form:label path="primarySkill">Primary Skill</form:label></td>
               <td><form:input path="primarySkill"/></td>
           </tr>
           <tr>
               <td><form:label path="primarySkill">Primary Skill</form:label></td>
               <td><form:input path="primarySkill"/></td>
           </tr>           
           <tr>
               <td><input type="submit" value="Submit"/></td>
           </tr>
       </table>
   </form:form>
</body>
</html>