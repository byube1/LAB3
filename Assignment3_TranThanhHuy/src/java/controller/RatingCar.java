/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dto.CarIsRenting;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "RatingCar", urlPatterns = {"/RatingCar"})
public class RatingCar extends HttpServlet {

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
        int idRenting = Integer.parseInt(request.getParameter("idRental"));
        ArrayList<CarIsRenting> listOrderDetail = (ArrayList<CarIsRenting>) session.getAttribute("listOrderDetail");
        CarIsRenting thisCar = listOrderDetail.stream().filter(i -> i.getIdRenting() == idRenting).findFirst().orElse(null);
        LocalDate timeNow = LocalDate.now();
        try {
            Date now = new SimpleDateFormat("yyyy-MM-dd").parse(timeNow.toString());
            Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(thisCar.getReturnDate());
            if (now.compareTo(returnDate) < 1) {
                session.setAttribute("notification", true);
                session.setAttribute("failed", "Cannot be assessed during the rental period");
                response.sendRedirect("LoadDetailHistory?idOrder=" + thisCar.getOrderID());
                return;
            }
        } catch (ParseException ex) {
            Logger.getLogger(RatingCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (request.getParameter("valueRating") == null || request.getParameter("valueRating").isEmpty()) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Please input value");
            response.sendRedirect("LoadDetailHistory?idOrder=" + thisCar.getOrderID());
            return;
        }
        float valueRate = Float.parseFloat(request.getParameter("valueRating"));
        OrderDAO orderDB = new OrderDAO();
        try {
            orderDB.setRate(idRenting, valueRate);
        } catch (SQLException ex) {
            Logger.getLogger(RatingCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("notification", true);
        session.setAttribute("failed", "Rate successfully");
        response.sendRedirect("LoadDetailHistory?idOrder=" + thisCar.getOrderID());
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
