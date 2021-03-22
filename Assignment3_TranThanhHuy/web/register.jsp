<%-- 
    Document   : registrationForm.jsp
    Created on : Jan 21, 2021, 7:55:51 PM
    Author     : admin
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>Registration</title>
    </head>
    <body>
        <!--Warning-->
        <div class="alert alert-dismissible alert-danger" style="display: ${notification?"block":"none"}">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Warning!</h4>
            <p class="alert-heading">${failed}</p>
            <c:forEach var="Item" items="${message}">
                <p class="alert-heading">${Item}</p>               
            </c:forEach>
        </div>       
        <!--end-Warning-->
        <button type="button" class="btn btn-secondary"><a  href="LoadFormLogin" style="color: white">Back</a></button>



        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Registration</h5>

                            <form class="form-signin" action="Register" method="POST">
                                
                                <div class="form-label-group">
                                    <label for="inputEmail">UserID</label>
                                    <input type="text" id="inputEmail"  class="form-control"  name="userID" value="${sendAgain.get(0)}"  required autofocus>                                   
                                </div>
                                
                                <div class="form-label-group">
                                    <label for="inputEmail">Email</label>
                                    <input type="email" id="inputEmail"  class="form-control"  name="email" value="${sendAgain.get(1)}" >                                   
                                </div>

                                <div class="form-label-group">
                                    <label for="inputPassword">Pass</label>
                                    <input type="password" id="inputPassword" class="form-control" name="pass" value="${sendAgain.get(2)}"  required>                              
                                </div> 
                                
                                <div class="form-label-group">
                                    <label for="inputPassword">UserName</label>
                                    <input type="text" id="inputPassword" class="form-control"  name="userName" value="${sendAgain.get(3)}"  required>                              
                                </div>    
                                
                                
                                 <div class="form-label-group">
                                    <label for="inputPassword">Phone</label>
                                    <input type="text" id="inputPassword" class="form-control" name="phone" value="${sendAgain.get(4)}"  required>                              
                                </div> 
                                
                                 <div class="form-label-group">
                                    <label for="inputPassword">Address</label>
                                    <input type="text" id="inputPassword" class="form-control" name="address" value="${sendAgain.get(5)}"  required>                              
                                </div> 
                                                                                                                 
                                <div class="my-4">
                                    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign up</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <c:remove var="notification"/>
    <c:remove var="failed"/>
    <c:remove var="message"/>
    <c:remove var="sendAgain"/>

</html>