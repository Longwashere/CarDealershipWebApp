<%-- 
    Document   : car
    Created on : Jun 25, 2014, 4:16:14 PM
    Author     : long
--%>

<%@page import="model.Automobile"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Page</title>
                <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Hello World!</h1>
         <form method="POST" action="NewServlet">
            <select name="chooseAuto">  
                
                <jsp:forward page="NewServlet" />
                      
              
            </select>
                <h4>When you're done, click here:<input type="SUBMIT" value="DONE !"></h4>  
        </form>
    </body>
</html>
