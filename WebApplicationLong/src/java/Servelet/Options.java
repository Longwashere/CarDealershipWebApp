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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Automobile;

/**
 *
 * @author long
 */
@WebServlet(name = "Options", urlPatterns = {"/Options"})
public class Options extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Automobile a = new Automobile(1,2); //automobile temp varible
        connection();
                output.writeObject("getAutomobile");
                output.writeObject(request.getParameter("choice"));
                try{
                a = (Automobile)input.readObject();
                endConnnection();
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Options</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Options at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"Sum\">");
            out.println("<h1>Please select your options " + request.getContextPath() + "</h1>");
            out.println("<select name = \"car\">");
            out.println("  <option value=\""+request.getParameter("choice")+  "\">" + request.getParameter("choice") + "</option>\n");
            out.println("</select>");
            out.println("<BR>");
            for (int i = 0; i < a.getOptionSets().size(); i++) {
                 out.println(a.getOptionSetName(i));
                 out.println("<select name = \"optionset"+i+"\">\n");//VERY IMPORTANT
                 for(int j = 0; j<a.getOptionSetSize(i);j++)
                 {
                out.println("  <option value=\""+a.getOptionName(i, j, a.getOptionSetName(i))+  "\">" + a.getOptionName(i, j, a.getOptionSetName(i)) + "</option>\n");
                 }
             
                 out.print("</select> ");
                 out.println("<BR>");
            }
           
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            out.println(a.getOptionSetName(0));
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
