/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dto.CarIsRenting;
import dto.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.MyTool;

/**
 *
 * @author admin
 */
@WebServlet(name = "ConfirmOrder", urlPatterns = {"/ConfirmOrder"})
public class ConfirmOrder extends HttpServlet {

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
        User thisUser = (User) session.getAttribute("user");
        ArrayList<CarIsRenting> cart = (ArrayList<CarIsRenting>) session.getAttribute("listCart");
        if (cart == null) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Empty Cart");
            response.sendRedirect("ViewCart");
            return;
        }
        if (cart.isEmpty()) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Empty Cart");
            response.sendRedirect("ViewCart");
            return;
        }
        String paymentMenthod = request.getParameter("paymentMenthod");
        HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) session.getAttribute("listMap");
        HashMap<Integer, String> mapCarDate = (HashMap<Integer, String>) session.getAttribute("listMapCarDate");
        float total = (float) session.getAttribute("total");
        float valueDiscount = session.getAttribute("discount") == null ? 0 : (float) session.getAttribute("discount");
        float finalTotal = total - (total * valueDiscount);
        String discountName = (String) session.getAttribute("sendTextdiscount");
        OrderDAO orderDB = new OrderDAO();
        try {
            MyTool tool = new MyTool();
            int orderID = tool.autoCreateNumberID();

            orderDB.confirmOrder(orderID, thisUser.getEmail(), paymentMenthod, discountName, finalTotal);
            for (CarIsRenting itemInCart : cart) {
                orderDB.confirmCarRenting(orderID, itemInCart.getCarId(), itemInCart.getAmountRenting(),
                itemInCart.getRentDate(), itemInCart.getReturnDate(), itemInCart.getPrice());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.clear();
        cart.clear();
        mapCarDate.clear();
        session.setAttribute("listMap", map);
        session.setAttribute("listCart", cart);
        session.setAttribute("listMapCarDate", mapCarDate);
        session.setAttribute("total", 0);
        session.removeAttribute("discount");
        session.removeAttribute("sendTextdiscount");      
        session.setAttribute("notification", true);
        session.setAttribute("failed", "Successful");
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
