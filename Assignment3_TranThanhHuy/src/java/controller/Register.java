/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.HandleInput;
import validation.MyTool;

/**
 *
 * @author admin
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        String hostEmail = "tieurua1@gmail.com";
        String passHost = "01699382011asd";
        String userID = request.getParameter("userID");
        String toEmail = request.getParameter("email");
        String pass = request.getParameter("pass");
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");            
        HandleInput checkInput = new HandleInput();
        if (!checkInput.handleUser(userID) || !checkInput.handleEmail(toEmail)
                || !checkInput.handleUser(pass) || !checkInput.handleUser(userName)
                || !checkInput.handlePhone(phone) || !checkInput.handleText(address, 100)) {
            handleValidation(request, response, userID, toEmail, pass, userName, phone, address);
            return;
        }
        UserDAO userDB = new UserDAO();
        int result = -1;
        MyTool tool = new MyTool();
        int userCode = tool.autoCreateNumberID()%10000;
        try {
            result = userDB.registrationUser(userID, toEmail, pass, userName, phone, address,userCode);
        } catch (SQLException ex) {
            ex.printStackTrace();
            String error = ex.toString().contains("UNIQUE KEY")?"UserID ":"Email";
            session.setAttribute("notification", true);
            session.setAttribute("failed", error+" was registered");
            sendAgain(request, response, userID, toEmail, pass, userName, phone, address);
            response.sendRedirect("LoadFormRegister");
            return;
        }
        if (result > -1) {
            session.setAttribute("notification", true);
            session.setAttribute("success", "Sign Up Success, Verify code will send your email: "+toEmail);           
            send(toEmail, "Active your account", "Code: "+userCode, hostEmail, passHost);
            response.sendRedirect("LoadFormLogin");       
        } else {
            session.setAttribute("notification", true);
            session.setAttribute("failed", "Registration failed");
            sendAgain(request, response, userID, toEmail, pass, userName, phone, address);
            response.sendRedirect("LoadFormRegister");        
        }

    }

    protected void handleValidation(HttpServletRequest request, HttpServletResponse response, String userID, String email,
            String pass, String userName, String phone, String address)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HandleInput checkInput = new HandleInput();
        ArrayList<String> message = new ArrayList<>();
        if (!checkInput.handleUser(userID)) {
            message.add("userID must required characters and <30 characters");
        }
        if (!checkInput.handleEmail(email)) {
            message.add("Email must required characters and <30 characters");
        }
        if (!checkInput.handleUser(pass)) {
            message.add("Password must required characters , <30 characters, no special characters ");
        }
        if (!checkInput.handleUser(userName)) {
            message.add("Username must required characters and <30 characters");
        }
        if (!checkInput.handlePhone(phone)) {
            message.add("Phone must required 10 numbers ");
        }
        if (!checkInput.handleText(address, 100)) {
            message.add("Username must required min 1  characters, max 100 characters ");
        }
        sendAgain(request, response, userID, email, pass, userName, phone, address);
        session.setAttribute("notification", true);
        session.setAttribute("message", message);
        response.sendRedirect("LoadFormRegister");

    }

    protected void sendAgain(HttpServletRequest request, HttpServletResponse response, String userID, String email,
            String pass, String userName, String phone, String address)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> sendAgain = new ArrayList<>();
        sendAgain.add(userID);
        sendAgain.add(email);
        sendAgain.add(pass);
        sendAgain.add(userName);
        sendAgain.add(phone);
        sendAgain.add(address);
        session.setAttribute("sendAgain", sendAgain);
    }

    public static void send(String to, String sub, String msg, final String user, final String pass) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        // below mentioned mail.smtp.port is optional 
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
