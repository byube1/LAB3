/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
@WebServlet(name = "SearchCar", urlPatterns = {"/SearchCar"})
public class SearchCar extends HttpServlet {

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
        LocalDate timeNow = LocalDate.now();
        String catelogy = request.getParameter("catelogySearch");
        String nameCar = request.getParameter("nameSearch");
        String retalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String amount = request.getParameter("amountCar");
        try {
            Date renDate = new SimpleDateFormat("yyyy-MM-dd").parse(retalDate);
            Date reDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDate);
            Date now = new SimpleDateFormat("yyyy-MM-dd").parse(timeNow.toString());          
           if(renDate.compareTo(now)<0){
              session.setAttribute("notification", true);
              session.setAttribute("failed", "Rental date must be greater or equal: "+timeNow.toString());
              response.sendRedirect("LoadMainPage");
              return;            
           }            
          if(reDate.compareTo(renDate)<=0){
              session.setAttribute("notification", true);
              session.setAttribute("failed", "Return date must be greater than rental date");
              response.sendRedirect("LoadMainPage");
              return;            
          }                              
        } catch (ParseException ex) {
            Logger.getLogger(SearchCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("searchcatelogyCar", catelogy);
        session.setAttribute("searchNameCar", nameCar);
        session.setAttribute("retalDate", retalDate);
        session.setAttribute("returnDate", returnDate);
        session.setAttribute("realAmountCar", amount);
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
