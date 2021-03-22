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
        <h1>Your cart</h1>
        <div class="container m-5">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Name Product</th>
                        <th scope="col">Start rent</th>
                        <th scope="col">End rent</th>
                        <th scope="col">Price</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Option</th>
                    </tr>
                </thead>
                <tbody>

                    <c:if test = "${listCart.size() == 0}">
                        <tr>
                            <td>
                                <h4> Empty ...  </h4>
                            </td>
                        </tr>
                    </c:if>



                    <c:forEach var="Item" items="${listCart}" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index+1}</th>
                            <td>${Item.getNameCar()}</td>

                            <td>
                                <div class="col-11">
                                    <input class="form-control" readonly type="date" value="${Item.getRentDate()}" id="example-date-input">
                                </div>
                            </td>

                            <td>
                                <div class="col-11">
                                    <input class="form-control" readonly type="date" value="${Item.getReturnDate()}" id="example-date-input">
                                </div>
                            </td>
                            <td>${Item.getPrice()}</td>
                            <td>
                                <input type="text" id="Input${Item.getCarId()}" value=" ${Item.getAmountRenting()}" readonly="" style="width: 20%;"/>
                                <button type="button" class="btn btn-secondary"><a  href="UpItem?idCar=${Item.getCarId()}" style="color: white"> + </a></button>
                                <button type="button" class="btn btn-secondary"><a  href="DownItem?idCar=${Item.getCarId()}" style="color: white"> - </a></button>
                            </td>
                            <td>
                                <button type="button"  id="${Item.getCarId()}" onclick="DeleteSingleItem(this.id)" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" >Delete</button>
                            </td>
                        </tr>
                    </c:forEach>                       
                </tbody>
            </table>

            <form style="width: 20%" action="AddDiscount" method="POST">
                <label>Discount</label>
                <input class="form-control mr-sm-2" required  type="text" name="discount" value="${sendTextdiscount}"  id="">
                <p style="font-size: 12px;font-style: italic">Input GIAM10 (6-3-2021 to 8-3-2021) to 10% off the order price</p>
                <p style="font-size: 12px;font-style: italic">Input GIAM50 (6-3-2021 to 2-4-2021) to 50% off the order price</p>
                <button type="submit" class="btn btn-success"> Accept</button>
            </form>

            <form action="ConfirmOrder" method="POST">
                <label>Payment menthod</label>
                <select style="width: 30%; " class="custom-select" name="paymentMenthod">             
                    <option value="Cash payment upon delivery"> Cash payment upon delivery.</option>
                    <option value="Credit"> Credit.</option>                   
                </select>
                <h3 id="Total">Total: ${discount == 0 ||discount==null?total:total-(total*discount) }$</h3>
                <button type="submit" class="btn btn-success"> Confirm</button>
            </form>
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

    <c:remove var="warningProduct"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <script>
                    function DeleteSingleItem(id) {
                        document.getElementById("saveChanges").href = "DeleteItem?idCar=" + id;

                    }

    </script>

    <c:remove var="notification"/>
    <c:remove var="failed"/>
</html>
