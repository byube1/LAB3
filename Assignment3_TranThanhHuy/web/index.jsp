<%-- 
    Document   : index
    Created on : Jan 7, 2021, 5:06:03 PM
    Author     : admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/litera/bootstrap.min.css" integrity="sha384-enpDwFISL6M3ZGZ50Tjo8m65q06uLVnyvkFO3rsoW0UC15ATBFz3QEhr3hmxpYsn" crossorigin="anonymous">

        <title>Home Page</title>
    </head>
    <body>
        <div class="alert alert-dismissible alert-light" style="display: ${notification?"block":"none"}">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Warning!</h4>       
            <p class="alert-heading">${failed}</p>
        </div>       
        <button type="button" class="btn btn-success" style="position: fixed;bottom: 10%;right: 0%;display: ${user.getRole()=="Admin"?'none':block} "> <a  href="ViewCart" style="color: white" >CART</a></button>
        <!--NavBar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">RentCar Shop</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="LoadMainPage">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>              
                    <li class="nav-item" style="display: ${user.getRole()!="Admin"?'block':'none'}">
                        <a class="nav-link" href="LoadHistoryOrder">View History
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul> 
                <div>
                    <p style="display: inline;color: white">Hello, ${user.getUserName()} </p>
                    <button type="button"  class="btn btn-success">${user == null?"<a  href=\"LoadFormLogin\" style=\"color: white\">Log in</a>":"<a  href=\"Logout\" style=\"color: white\">Log out</a>"}</button>

                </div>
            </div>
        </nav>
        <!--END-NavBar-->

        <!--Jumbotron-->
        <div class="jumbotron ">
            <h1 class="display-3">Welcome to Rent Car</h1>
            <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
            <hr class="my-4">
            <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>           
        </div>
        <!--END-Jumbotron-->

        <!--Nav-->
        <ul class="nav justify-content-between bg-primary " style="height: 60px">

            <form class="form-inline" style="width: 100% " action="SearchCar">
                <input  type="text"  name="page" value="0" hidden>  

                <div>
                    <label for="catelogy" style="display: inline;color: white">Catelogy</label>
                    <input class="form-check-input"  onchange="chooseSearch()" type="radio" name="search" value="content" checked="" id="catelogy">
                    <label for="nameCar" style="display: inline;color: white">Car Name</label>
                    <input class="form-check-input"  onchange="chooseSearch()" type="radio" value="subject" name="search" id="nameCar">
                </div>

                <div>
                    <select name="catelogySearch" class="form-control" id="catelogyCarSearch">
                        <option ${searchcatelogyCar == null?'selected="selected"':""} value="">Catelogy</option>
                        <option ${searchcatelogyCar == "Sedan"?'selected="selected"':""} value="Sedan">Sedan</option>
                        <option ${searchcatelogyCar == "Convertible"?'selected="selected"':""} value="Convertible">Convertible</option>

                    </select>
                </div>

                <div>
                    <input class="form-control mr-sm-2" value="${searchNameCar}" style="display: none" type="text" placeholder="Search name" name="nameSearch" id="nameCarSearch">  
                </div>

                <div style="width: 24%;margin: 0 5px;">
                    <label  style="display: inline;color: white">Rental date</label>
                    <input class="form-control" onchange="minDate(this.value)" type="date"  value="${retalDate == null ? "2021-10-03":retalDate}" id="example-date-input" name="rentalDate">                                       
                </div>
                <div style="width: 24%;margin: 0 5px;">
                    <label  style="display: inline;color: white">Return date</label>
                    <input class="form-control" type="date" value="${returnDate == null ? "2021-10-04":returnDate}" id="idReturnDate" name="returnDate">                                       
                </div>


                <div style="width: 13%;margin: 0 5px;">
                    <label style="color: white; display: inline" id="valueAmount" for="vol">Amount >= ${realAmountCar==null?1:realAmountCar}</label>
                    <input style="margin: 0px 3px" type="range" onchange="changeAmount(this.value)" id="vol" name="amountCar" min="1" max="50" value="${realAmountCar==null?1:realAmountCar}">                    
                </div>
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
            </form>                          
        </ul>

        <hr class="my-1">
        <!--end-NAV-->

        <!--ListItem-->
        <div class="container" id="Shopnow">
            <div class="row justify-content-md-start">

                <c:if test = "${listProduct.size() == 0}">
                    <h4>No car available, try to click search button or input another value </h4>
                </c:if>

                <c:forEach var="Item" items="${listProduct}" varStatus="i">

                    <div class="card text-black ml-4 mr-5 my-3 col-3" style="max-width: 18rem;">
                        <div class="card-header">${Item. getBard()}</div>
                        <img src="${Item.getImg()}" class="card-img-top" alt="..." style="height: 150px">
                        <div class="card-body">
                            <h5 class="card-title">${Item.getName()}</h5>
                            <p class="card-text">Type: ${Item.getType()}</p>
                            <p class="card-text">Color: ${Item.getColor()}</p>
                            <p class="card-text">Price: ${Item.getPrice()}$</p>


                            <c:forEach var="inCart" items="${listCart}">
                                <c:if test = "${inCart.getCarId() == Item.getIdCar()}">
                                    <p id="amount${i.index}"  class="card-text amount${i.index}">Rented: ${ inCart.getAmountRenting()}</p> 

                                </c:if>                        
                            </c:forEach>
                            <p id="amount${i.index}"  class="card-text amount${i.index}">Quantity: ${ Item.getAmount()}</p>

                            <a id="buy" href="AddToCart?idCar=${Item.getIdCar()}&nameCar=${Item.getName()}&price=${Item.getPrice()}&renDate=${retalDate}&reDate=${returnDate}" class="btn btn-outline-primary card-text  ${user.getRole()=="Admin"||Item.getAmount()==0?"disabled":null}" >
                                ${Item.getAmount()==0?'Out of stock':'Add to cart'} </a>
                        </div>            
                    </div>
                </c:forEach>


            </div>


            <div style="width: 100%;display: flex;justify-content: center;display: ${totalPage<=1?'none':'flex'}">
                <ul class="pagination">
                    <li class="page-item ">
                        <a class="page-link" href="LoadMainPage?page=1">&laquo;</a>
                    </li>                                      
                    <c:forEach begin="1" end="${totalPage}" varStatus="loop">
                        <li class="page-item ${loop.index == page ? 'active':null}">
                            <a class="page-link" href="LoadMainPage?page=${loop.index}">${loop.index}</a>
                        </li>   
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="LoadMainPage?page=${totalPage}">&raquo;</a>
                    </li>
                </ul>
            </div>


        </div>
        <!--end-ListItem-->

        <script>


            function chooseSearch() {
                var questionSearch = document.getElementById("catelogy").checked;
                var subjectSearch = document.getElementById("nameCar").checked;

                document.getElementById("catelogyCarSearch").style.display = "none";
                document.getElementById("catelogyCarSearch").value = "";

                document.getElementById("nameCarSearch").style.display = "none";
                document.getElementById("nameCarSearch").value = "";


                if (questionSearch)
                    document.getElementById("catelogyCarSearch").style.display = "block";
                if (subjectSearch)
                    document.getElementById("nameCarSearch").style.display = "block";

            }

            function changeAmount(valueAmount) {
                document.getElementById("valueAmount").innerHTML = "Amount: >= " + valueAmount + "";
            }

            function minDate(minD) {
                document.getElementById("idReturnDate").min = minD + "";
            }

           



        </script>
        <c:remove var="notification"/>
        <c:remove var="failed"/>

</html>
