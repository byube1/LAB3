/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarDAO;
import dto.Car;
import dto.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.HandleInput;

/**
 *
 * @author admin
 */
@WebServlet(name = "LoadMainPage", urlPatterns = {"/LoadMainPage"})
public class LoadMainPage extends HttpServlet {

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
        User verifyUser = (User) session.getAttribute("user");
        String nameCar = (String) session.getAttribute("searchNameCar");       
        String catelogyCar = (String) session.getAttribute("searchcatelogyCar");      
        String rentalDate = (String) session.getAttribute("retalDate");       
        String returnDate = (String) session.getAttribute("returnDate");      
        String tmpAmount = (String) session.getAttribute("realAmountCar");
        int realAmount = (tmpAmount == null) ? 1 : Integer.parseInt(tmpAmount);            
        if (verifyUser == null) {
            String page = request.getParameter("page");          
            getDataWithPage(request, response, page, 6, nameCar, catelogyCar, rentalDate, returnDate, realAmount);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return;
        } else if (verifyUser.getStatus().equals("Active")) {
            String page = request.getParameter("page");
            getDataWithPage(request, response, page, 6, nameCar, catelogyCar, rentalDate, returnDate, realAmount);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return;
        } else if (verifyUser.getStatus().equals("New")) {
            response.sendRedirect("LoadVerifyPage");
            return;
        }

    }

    private ArrayList<Car> getDataWithPage(HttpServletRequest request, HttpServletResponse response, String tmppage, int pageSize,
            String nameCar, String catelogyCar, String retalDate, String returnCar, int amountCar)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        HandleInput check = new HandleInput();
        int page = check.handleAmount(tmppage) ? Integer.parseInt(tmppage) : 1;
        CarDAO dbCar = new CarDAO();
        ArrayList<Car> listProduct = null;
        int realPage = 1;
        try {
            int totalProduct = dbCar.getTotalCar(nameCar, catelogyCar, retalDate, returnCar, amountCar);
            realPage = (totalProduct - 1) / pageSize + 1;
            page = (page > realPage || page < 1) ? realPage : page;
            int offset = (page > realPage || page <= 1) ? 0 : (page * pageSize) - pageSize;
            listProduct = dbCar.getCarSearch(offset, pageSize, nameCar, catelogyCar, retalDate, returnCar, amountCar);
        } catch (SQLException ex) {
            Logger.getLogger(LoadMainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("listProduct", listProduct);
        session.setAttribute("totalPage", realPage);
        session.setAttribute("page", page);
        return listProduct;
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
