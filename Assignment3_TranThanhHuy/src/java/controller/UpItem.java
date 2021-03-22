/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Car;
import dto.CarIsRenting;
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
@WebServlet(name = "UpItem", urlPatterns = {"/UpItem"})
public class UpItem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) session.getAttribute("listMap");
        ArrayList<CarIsRenting> cart = (ArrayList<CarIsRenting>) session.getAttribute("listCart");
        ArrayList<Car> listProduct = (ArrayList<Car>) session.getAttribute("listProduct");
        int idCar = Integer.parseInt(request.getParameter("idCar"));
        Car productInDB = listProduct.stream().filter(i -> i.getIdCar() == idCar).findFirst().orElse(null);
        if (map.containsKey(idCar) && productInDB.getAmount() == map.get(idCar)) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Out of stock");
            response.sendRedirect("ViewCart");
            return;
        }
        map.put(idCar, map.get(idCar) + 1);
        CarIsRenting thisProduct = cart.stream().filter(i -> i.getCarId() == (idCar)).findFirst().orElse(null);
        thisProduct.setAmountRenting(map.get(idCar));
        float total = 0;
        for (CarIsRenting productInCart : cart) {
            total += productInCart.getTotal();
        }
        session.setAttribute("listCart", cart);
        session.setAttribute("listMap", map);
        session.setAttribute("total", total);       
        response.sendRedirect("ViewCart");

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
