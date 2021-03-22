/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import validation.HandleInput;

/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String userID = request.getParameter("userID");
        String pass = request.getParameter("pass");
        boolean validCaptcha = isCaptchaValid(request.getParameter("g-recaptcha-response"));
        HandleInput checkInput = new HandleInput();
        if (!checkInput.handleUser(userID) || !checkInput.handleUser(pass) || !validCaptcha) {
            handleValidation(request, response, userID, pass,validCaptcha);
            return;
        }       
        UserDAO userDB = new UserDAO();
        User user = null;      
         try {
             user = userDB.login(userID, pass);
        } catch (SQLException ex) {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Login Fail");
            sendAgain(request, response, userID, pass);
            response.sendRedirect("LoadFormLogin");
            return;
        }        
         if(user == null){
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Invalid email or password");
            sendAgain(request, response, userID, pass);
            response.sendRedirect("LoadFormLogin");
        }
         session.setAttribute("user", user);
         response.sendRedirect("LoadMainPage");                    
    }

    public synchronized boolean isCaptchaValid(String response) {
        try {
            String secretKey = "6LfdGloaAAAAAHd-_RPZ3l1EHWV2EYEgUaU5hCaL";
            String url = "https://www.google.com/recaptcha/api/siteverify",
                    params = "secret=" + secretKey + "&response=" + response;

            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();

            InputStream res = http.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            JSONObject json = new JSONObject(sb.toString());
            res.close();
            return json.getBoolean("success");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return false;
    }

    protected void sendAgain(HttpServletRequest request, HttpServletResponse response, String userID, String pass)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> sendAgain = new ArrayList<>();
        sendAgain.add(userID);
        sendAgain.add(pass);
        session.setAttribute("sendAgain", sendAgain);
    }

    protected void handleValidation(HttpServletRequest request, HttpServletResponse response, String userID, String pass,boolean validCaptcha)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HandleInput checkInput = new HandleInput();
        ArrayList<String> message = new ArrayList<>();
        if (!checkInput.handleUser(userID)) {
            message.add("UserID must required characters and <30 characters");
        }
        if (!checkInput.handleUser(pass)) {
            message.add("Password must required characters and <30 characters");
        }
        if(!validCaptcha){
             message.add("Required check captcha");
        }
        sendAgain(request, response, userID, pass);
        session.setAttribute("notification", true);
        session.setAttribute("message", message);
        response.sendRedirect("LoadFormLogin");
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
