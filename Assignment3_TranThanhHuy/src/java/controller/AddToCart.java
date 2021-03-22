/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Car;
import dto.CarIsRenting;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<CarIsRenting> cart = new ArrayList<CarIsRenting>();
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    HashMap<Integer, String> mapCarDate = new HashMap<Integer, String>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User verifyUser = (User) session.getAttribute("user");
        ArrayList<Car> listProduct = (ArrayList<Car>) session.getAttribute("listProduct");
        if (verifyUser == null) {
            response.sendRedirect("LoadFormLogin");
            return;
        }
        String nameCar = request.getParameter("nameCar");
        int idCar = Integer.parseInt(request.getParameter("idCar"));
        float price = Float.parseFloat(request.getParameter("price"));
        String renDate = request.getParameter("renDate");
        String reDate = request.getParameter("reDate");
        String carInDate = idCar + "-" + renDate + "-" + reDate;
        if (mapCarDate.containsKey(idCar) && !mapCarDate.get(idCar).equals(carInDate)) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "This car is existing in your cart in another day");
            response.sendRedirect("LoadMainPage");
            return;
        }
        Car productInDB = listProduct.stream().filter(i -> i.getIdCar() == idCar).findFirst().orElse(null);
        if (map.containsKey(idCar) && productInDB.getAmount() == map.get(idCar)) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Out of stock");
            response.sendRedirect("LoadMainPage");
            return;
        }
        if (map.containsKey(idCar)) {
            map.put(idCar, map.get(idCar) + 1);
            CarIsRenting thisProduct = cart.stream().filter(i -> i.getCarId() == (idCar)).findFirst().orElse(null);
            thisProduct.setAmountRenting(map.get(idCar));
        } else {
            map.put(idCar, 1);
            mapCarDate.put(idCar, carInDate);
            CarIsRenting newCart = new CarIsRenting(nameCar, idCar, 1, price, renDate, reDate);
            cart.add(newCart);
        }
        float total = 0;
        for (CarIsRenting productInCart : cart) {
            total += productInCart.getTotal();
        }
        session.setAttribute("listCart", cart);
        session.setAttribute("listMap", map);
        session.setAttribute("listMapCarDate", mapCarDate);
        session.setAttribute("total", total);
        response.sendRedirect("LoadMainPage");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
