/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servelet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Automobile;
import java.util.ArrayList;

/**
 *
 * @author long
 */
@WebServlet(name = "Sum", urlPatterns = {"/Sum"})
public class Sum extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket socket;
    Automobile a =new Automobile(1,2);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double total=0;
            connection();
                output.writeObject("getAutomobile");
                output.writeObject(request.getParameter("car"));
                try{
                a = (Automobile)input.readObject();
                endConnnection();
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                
                a.setOptionChoice("Color", request.getParameter("optionset0"));
                a.setOptionChoice("Transmission", request.getParameter("optionset1"));
                a.setOptionChoice("Brakes/traction", request.getParameter("optionset2"));
                a.setOptionChoice("SideImpactAirBags", request.getParameter("optionset3"));
                a.setOptionChoice("PowerMoonroof", request.getParameter("optionset4"));
                System.out.println(a.getTotalPrice());
                
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
             out.println("<h1>The Sum" + "</h1>");
            out.println("<title>Servlet Sum</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(request.getParameter("car"));
            out.println(request.getParameter("optionset1"));
            for(int i = 0;i<a.getOptionSets().size();i++)
            {
                out.println(request.getParameter("optionset"+i));
                out.println(" Price: " + a.webGetOptionPrice(request.getParameter("optionset"+i)));
                out.println("<BR>");
            }
                out.println("^Moon roof yes/no^<BR>");
                out.println("The base price is"+a.getBasePrice());
            total = (a.getBasePrice()+a.getTotalPrice());
            out.println("<BR>The total price is " + total);
      
            out.println("</body>");
            out.println("</html>");
        }
    }
   public void endConnnection()
    {
        try
        {
        output.writeObject("quit");
        output.close();
        input.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    public void connection()
    {

            try {
             socket = new Socket(InetAddress.getLocalHost(), 7777);
             output = new ObjectOutputStream(socket.getOutputStream());
             input = new ObjectInputStream(socket.getInputStream());

              
              

            } catch (EOFException eofException) {
                System.out.println("Client terminated connection");
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
