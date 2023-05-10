/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.ConexionDB;
import LogicaOAB.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class BusqCitaspEspecialista extends HttpServlet {

    private String nomEspecialista = "";
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    private String usernameOAB;
    private boolean verifIdentidadAdmin;
    
    
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
        HttpSession sesion = request.getSession(false);
        if(sesion != null){
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Buscar citas por especialista</title>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1' />");
                out.println("<meta charset='utf-8' />");
                out.println("<link rel='stylesheet' href='assets/css/main.css' />");           
                out.println("</head>");
                out.println("<body class='landing'>");
                out.println("<div id='page-wrapper'>");
                // Inicio del encabezado
                        out.println("<header id='header'>");
                        out.println("<h1 id='logo'><a href='VerCitasdHoy'>AppointmentBook Muse</a></h1>");
                        out.println("<nav id='nav'>");
                        out.println("<ul>");
                        out.println("<li><a href='VerCitasdHoy'>Home</a></li>");
                        out.println("<li>");
                        out.println("<a href='#'>Menú</a>");
                        out.println("<ul>");
                        if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                            out.println("<li>");
                            out.println("   <a href='#'>Clientes</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='NvoCliente'>Nuevo cliente</a></li>");
                            out.println("       <li><a href='BuscarCliente'>Buscar</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                            out.println("<li>");
                            out.println("   <a href='#'>Citas</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='BuscarClientepCita'>Buscar por cliente</a></li>");
                            out.println("       <li><a href='BuscarCitapEsp'>Buscar por especialista</a></li>");
                            out.println("       <li><a href='VerCitasdHoy'>Ver citas de hoy</a></li>");
                            out.println("       <li><a href='VerCitaspFecha'>Ver citas por fecha</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                        }    
                        if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                            out.println("<li>");
                            out.println("   <a href='#'>Usuarios</a>");
                            out.println("   <ul>");
                                if(privilegioOAB.equals(privAdmin)){
                                    usernameOAB = (String)sesion.getAttribute("username");
                                    ConexionDB cn = new ConexionDB();
                                    verifIdentidadAdmin = cn.verificarIdentidadAdministrador(usernameOAB);
                                    out.println("           <li>");
                                    out.println("               <a href='#'>Administradores</a>");
                                    out.println("               <ul>");
                                    if(verifIdentidadAdmin != false){
                                        out.println("                   <li><a href='NvoAdmin'>Nuevo</a></li>");
                                    }
                                    out.println("                   <li><a href='BuscarAdmin'>Buscar</a></li>");
                                    out.println("               </ul>");
                                    out.println("           </li>");
                                    out.println("           <li>");
                                    out.println("               <a href='#'>Gerentes</a>");
                                    out.println("               <ul>");
                                    out.println("                   <li><a href='NvoGerente'>Nuevo</a></li>");
                                    out.println("                   <li><a href='BuscarGerente'>Buscar</a></li>");
                                    out.println("               </ul>");
                                    out.println("           </li>");
                                    out.println("           <li>");
                                }
                            out.println("               <a href='#'>Recepcionistas</a>");
                            out.println("               <ul>");
                            out.println("                   <li><a href='NvaRecepcionista'>Nueva</a></li>");
                            out.println("                   <li><a href='BuscarRecep'>Buscar</a></li>");
                            out.println("               </ul>");
                            out.println("           </li>");
                            out.println("           <li>");
                            out.println("               <a href='#'>Especialistas</a>");
                            out.println("               <ul>");
                            out.println("                   <li><a href='NvoEspecialista'>Nuevo</a></li>");
                            out.println("                   <li><a href='BuscarEspecialista'>Buscar</a></li>");
                            out.println("               </ul>");
                            out.println("           </li>");
                            out.println("   </ul>");
                            out.println("</li>");
                            out.println("<li>");
                            out.println("   <a href='#'>Servicios</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='AgregarServicio'>Agregar</a></li>");
                            out.println("       <li><a href='VerServicios'>Ver todos</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                        }
                        out.println("</ul>");
                        out.println("</li>");
                        out.println("<li><a href='Logout' class=\"button\">Cerrar sesión</a></li>");
                        out.println("</ul>");
                        out.println("</nav>");
                        out.println("</header>");
                // Fin del encabezado
                
                out.println("<div id='main' class='wrapper style1'>");
                out.println("<div class='container'>");
                out.println("<header class='major'>");
                List<Cita> citas = new ArrayList<>();
                ConexionDB cn = new ConexionDB();
                Cita c = new Cita();
                
                if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin))
                        {
                            if(request.getParameter("nomEspecialista") != null ){
                                nomEspecialista = request.getParameter("nomEspecialista");
                            }
                            citas = cn.verCitasPespecialistaHoyA(nomEspecialista);
                            int tCitas = citas.size();
                            if(tCitas > 0 ) {
                                    out.println("<h2>Buscar citas por especialista</h2>");
                                    out.println("<form method='POST' action='BusqCitaspEspecialista'>");
                                    out.println("<input type='text' id='nomEspecialista' name='nomEspecialista' autocomplete='on' placeholder='Introducir nombre del especialista' value='" + nomEspecialista + "' required>");
                                    out.println("<input class='button special' value='Buscar' type='submit'>");
                                    out.println("</form>");
                                    out.println("<br/>");
                                    out.println("<h1 style='font-weight: bold;'>Citas programadas para el especialista: '" + nomEspecialista + "', a partir del día de hoy.</h1>");
                                out.println("<table>");
                                out.println("<thead>");
                                out.println("<th>Especialista</th>");
                                out.println("<th>Cliente</th>");
                                out.println("<th>Servicio</th>");
                                out.println("<th>Fecha</th>");
                                out.println("<th>Hora</th>");
                                out.println("</thead>");
                                for(int i=0 ; i<tCitas ; i++) {
                                    out.println("<tr>");
                                        // Nombre del especialista
                                        out.println("<td>" + citas.get(i).getNombre() + "</td>");
                                        out.println("<td>" + citas.get(i).getNomCliente() + "</td>");
                                        out.println("<td>" + citas.get(i).getDescripcionServ() + "</td>");
                                        out.println("<td>" + citas.get(i).getFecha() + "</td>");
                                        out.println("<td>" + c.convertAformatHoraSinSegundos(citas.get(i).getHoraCita(), citas.get(i).getMinCita()) + "</td>");
                                        out.println("   <td><a class='button special small' href='PosponerCita?idCita=" + citas.get(i).getIdCita() + "'>Posponer</a></td>");
                                        out.println("   <td><a class='button special small' href='CancelarCita?idCita=" + citas.get(i).getIdCita() + "'>Cancelar</a></td>");
                                    out.println("</tr>");
                                }
                                out.println("</table>");
                                out.println("<h3><a class='button fit' href='BusqCitaspEspecialista?nomEspecialista=" + nomEspecialista + "'>Actualizar</a></h3>");
                            } else{
                                    out.println("<h2>Buscar citas por especialista</h2>");
                                    out.println("<form method='POST' action='BusqCitaspEspecialista'>");
                                    out.println("<input type='text' id='nomEspecialista' name='nomEspecialista' autocomplete='on' placeholder='Introducir nombre del especialista' value='" + nomEspecialista + "' required>");
                                    out.println("<input class='button special' value='Buscar' type='submit'>");
                                    out.println("</form>");
                                    out.println("<br/>");
                                    out.println("<h1 style='font-weight: bold;'>El especialista '" + nomEspecialista + "', aún no tiene citas programadas para el día de hoy, ni días posteriores</h2>");
                            }      
                        }
                else {
                        out.println("<h2 style='color: red;'>Usted no tiene permiso para acceder a ésta sección de la aplicación</h2>");
                }
                
                out.println("</header>");
                out.println("</div>");
                out.println("</div>");
                out.println("");
                out.println("");
                out.println("");
                out.println("");
                out.println("<script src='assets/js/jquery.min.js'></script>");
                out.println("<script src='assets/js/jquery.scrolly.min.js'></script>");
                out.println("<script src='assets/js/jquery.dropotron.min.js'></script>");
                out.println("<script src='assets/js/jquery.scrollex.min.js'></script>");
                out.println("<script src='assets/js/skel.min.js'></script>");
                out.println("<script src='assets/js/util.js'></script>");
                out.println("<!--[if lte IE 8]><script src='assets/js/ie/respond.min.js'></script><![endif]-->");
                out.println("<script src='assets/js/main.js'></script>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
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
            Logger.getLogger(BusqCitaspEspecialista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BusqCitaspEspecialista.class.getName()).log(Level.SEVERE, null, ex);
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
