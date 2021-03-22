
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        <title>Login Page</title>
    </head>
    <body>
        <!--Warning-->
        <div class="alert alert-dismissible alert-light" style="display: ${notification?"block":"none"}">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Warning!</h4>
            <p class="alert-heading">${isRole}</p>
            <p class="alert-heading">${failed}</p>
            <p class="alert-heading">${success}</p>
            <c:forEach var="Item" items="${message}">
                <p class="alert-heading">${Item}</p>               
            </c:forEach>
        </div>       
        <!--end-Warning-->

        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Sign In</h5>


                            <form class="form-signin" action="Login" method="POST">
                                <div class="form-label-group">
                                    <label for="inputEmail">UserID</label>
                                    <input type="text" id="inputEmail"  class="form-control" value="${sendAgain.get(0)}" placeholder="Email address" name="userID"  required autofocus>                                   
                                </div>

                                <div class="form-label-group">
                                    <label for="inputPassword">Password</label>
                                    <input type="password" id="inputPassword" class="form-control" value="${sendAgain.get(1)}" placeholder="Password" name="pass" required>                                 
                                </div> 
                                <div class="form-label-group my-4">
                                    <div class="g-recaptcha" data-sitekey="6LfdGloaAAAAAIqmM4dt3T7Wdki3h0CNXOep0vEx"></div>
                                </div>
                                <div class="my-4">
                                    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                                </div>

                                <hr class="my-4">
                                <div class="my-4">
                                    <button class="btn btn-lg btn-success btn-block text-uppercase" type="submit"><a  href="LoadFormRegister" style="color: white">Sign up</a></button>
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
    <c:remove var="success"/>


</html>
