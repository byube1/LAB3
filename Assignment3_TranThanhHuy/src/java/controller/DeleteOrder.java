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
@WebServlet(name = "DeleteOrder", urlPatterns = {"/DeleteOrder"})
public class DeleteOrder extends HttpServlet {

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
        ArrayList<CarIsRenting> listOrderDetail = (ArrayList<CarIsRenting>) session.getAttribute("listOrderDetail");
        if (listOrderDetail == null || listOrderDetail.isEmpty()) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "List Empty");
            response.sendRedirect("loadHistoryOrder");
            return;
        }
        LocalDate timeNow = LocalDate.now();
        try {
            Date now = new SimpleDateFormat("yyyy-MM-dd").parse(timeNow.toString());
            for (CarIsRenting carIsRenting : listOrderDetail) {
                Date rentDate = new SimpleDateFormat("yyyy-MM-dd").parse(carIsRenting.getRentDate());
                if (now.compareTo(rentDate) >= 0) {
                    session.setAttribute("notification", true);
                    session.setAttribute("failed", "Can not delete order when car is renting");
                    response.sendRedirect("LoadDetailHistory?idOrder=" + carIsRenting.getOrderID());
                    return;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(DeleteOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        OrderDAO orderDB = new OrderDAO();
        try {
            orderDB.setStatusOrder(listOrderDetail.get(0).getOrderID());
            orderDB.setStatusOrderDetail(listOrderDetail.get(0).getOrderID());
        } catch (SQLException ex) {
            Logger.getLogger(DeleteOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("notification", true);
        session.setAttribute("failed", "Delete successfully");
        response.sendRedirect("LoadHistoryOrder");

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
