<%-- 
    Document   : viewCartPage
    Created on : Jan 15, 2021, 9:10:43 PM
    Author     : admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>
    <body>
        <div class="alert alert-dismissible alert-light" style="display: ${notification?"block":"none"}">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Warning!</h4>       
            <p class="alert-heading">${failed}</p>
        </div>      
        <button type="button" class="btn btn-secondary"><a  href="LoadMainPage?page=1" style="color: white">Back</a></button>
        <h1>Your Order Hisory</h1>
        <div class="container m-5">

            <form action="LoadHistoryOrder" method="POST">
                <div class="row">        
                    <div class="col-4">
                        <div class="row">        
                            <label class="col-6" for="sname" style="display: inline;color: black">Search Name</label>
                            <input class="col-6" class="form-check-input"  onchange="chooseSearch()" type="radio" name="or"  value="content" checked="" id="sname">
                            <label class="col-6" for="screatedate" style="display: inline;color: black">Search Date Create</label>
                            <input class="col-6" class="form-check-input"  onchange="chooseSearch()" type="radio" value="subject" name="or"  id="screatedate">
                        </div>
                    </div>
                    <div class="col-4">
                        <input class="form-control mr-sm-2" value=""  type="text" placeholder="Search name" name="historyCarName" id="searchName">  
                        <input class="form-control" style="display: none"  type="date"   name="historyCreateDate" id="searchDate">            
                    </div>
                    <div class="col-2">
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>                     
                    </div>
                </div>
            </form>


            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">ID</th>
                        <th scope="col">Total</th>
                        <th scope="col">Payment method</th>
                        <th scope="col">Date create</th>                    
                        <th scope="col">Option</th>
                    </tr>
                </thead>
                <tbody>

                    <c:if test = "${listHistoryOrder.size() == 0}">
                        <tr>
                            <td>
                                <h4> Empty ...  </h4>
                            </td>
                        </tr>
                    </c:if>



                    <c:forEach var="Item" items="${listHistoryOrder}" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index+1}</th>
                            <td>${Item.getIdOrder()}</td>
                            <td>${Item.getTotal()}</td>
                            <td>${Item.getPaymentMethod()}</td>
                            <td>${Item.getDateCreate()}</td>
                            <td>                              
                                <button type="button" style="display: ${Item.getStatus() == "Active"?"inline-block":"none"}    "   class="btn btn-success" >
                                <a  href="LoadDetailHistory?idOrder=${Item.getIdOrder()}" style="color: white"> Detail </a></button>
                                <p style="display:${Item.getStatus() == "Active"?"none":"block"} ">Canceled</p>
                            </td>
                        </tr>
                    </c:forEach>                       
                </tbody>
            </table>


        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Notification</h5>                     
                    </div>
                    <div class="modal-body">
                        <h5>Are you sure to delete this product</h5>
                        <p id="test"> </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="btnSave"> <a href="#" id="saveChanges" style="color: white">Save changes</a> </button>
                    </div>
                </div>
            </div>
        </div>


    </body>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <script>
                                    function DeleteSingleItem(id) {
                                        document.getElementById("saveChanges").href = "DeleteItem?idCar=" + id;

                                    }

                                    function chooseSearch() {
                                        var questionSearch = document.getElementById("sname").checked;
                                        var subjectSearch = document.getElementById("screatedate").checked;

                                        document.getElementById("searchName").style.display = "none";
                                        document.getElementById("searchName").value = "";

                                        document.getElementById("searchDate").style.display = "none";
                                        document.getElementById("searchDate").value = "";


                                        if (questionSearch)
                                            document.getElementById("searchName").style.display = "block";
                                        if (subjectSearch)
                                            document.getElementById("searchDate").style.display = "block";

                                    }

    </script>
    <c:remove var="warningProduct"/>
    <c:remove var="notification"/>
    <c:remove var="failed"/>
</html>
