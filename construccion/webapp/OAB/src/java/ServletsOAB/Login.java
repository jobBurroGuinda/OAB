/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.ConexionDB;
import LogicaOAB.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Job
 */
public class Login extends HttpServlet {

    private String username = null;
    private String password = null;
    private String privilegioOAB = null;
    
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privEspecialista = u.getEspecialista();
    private final String privRecepcionista = u.getRecepcionista();
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        username = request.getParameter("username");
        password = request.getParameter("passw");
        ConexionDB db = new ConexionDB();
        privilegioOAB = db.login(username, password);
        
        try (PrintWriter out = response.getWriter()) {
            
            if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin) || privilegioOAB.equals(privEspecialista)) {
                HttpSession sesion = request.getSession();
                sesion.getAttribute(username);
                sesion.setAttribute("username", username);
                request.setAttribute("Privilegio", privilegioOAB);
                RequestDispatcher rd = request.getRequestDispatcher("Home");
                rd.forward(request, response);
            } 
            else {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Contraseña incorrecta</title>"); 
                out.println("<link rel='stylesheet' href='assets/css/main.css' />");     
                out.println("</head>");
                out.println("<body class='landing'>");
                out.println("<header id='header'>");
                out.println("    <h1 id='logo'><a href='#'></a></h1>");
                out.println("</header>");
                    out.println("<div id='page-wrapper'>");
                        out.println("<section id='banner'>");
                            out.println("<div class='content'>");
                                out.println("<header>");
                                    out.println("<h2>AppointmentBook</h2>");
                                    out.println("<h3>Iniciar sesión</h3>");
                                    out.println("<h1 style='color:red; font-style:italic;'>Nombre de usuario y/o contraseña incorrectos</h1>");
                                    out.println("<form method='post' action='Login'>");
                                        out.println("<input type='text' name='username' id='username' placeholder='Nombre de usuario' autocomplete='on' required>");
                                        out.println("<input type='password' name='passw' id='passw' placeholder='Contraseña' required>");
                                        out.println("<input class='button special' type='submit' value='Entrar'>");
                                    out.println("</form>");
                                    out.println("</header>");
                                out.println("<span class='image'><img src='images/logo_muse.png' alt='' /></span>");

                                out.println("<script src='assets/js/jquery.min.js'></script>");
                                out.println("<script src='assets/js/jquery.scrolly.min.js'></script>");
                                out.println("<script src='assets/js/jquery.dropotron.min.js'></script>");
                                out.println("<script src='assets/js/jquery.scrollex.min.js'></script>");
                                out.println("<script src='assets/js/skel.min.js'></script>");
                                out.println("<script src='assets/js/util.js'></script>");
                                out.println("<!--[if lte IE 8]><script src='assets/js/ie/respond.min.js'></script><![endif]-->");
                                out.println("<script src='assets/js/main.js'></script>");
                            out.println("</div>");
                        out.println("</section>");
                    out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
            
                
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
