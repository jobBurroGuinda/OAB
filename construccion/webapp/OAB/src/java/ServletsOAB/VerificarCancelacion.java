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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Job
 */
public class VerificarCancelacion extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private boolean verificador = false;
    private int idCita = 0;
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
        if(sesion != null) {
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            ConexionDB cn = new ConexionDB();
            List<Cita> citas = new ArrayList<>();
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cancelación de la cita</title>");   
                out.println("<link rel='stylesheet' href='assets/css/main.css' />");     
                out.println("</head>");
                out.println("<body class='landing'>>");
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
                
                out.println("<br/>");
                    out.println("<div id='main' class='wrapper style1'>");
                    out.println("<div class='container'>");
                    out.println("<header class='major'>");
                    if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                        citas = (List)sesion.getAttribute("CitaProgramada");
                        int tCita = citas.size();
                        Cita c = new Cita();
                        for(int i=0 ; i<tCita ; i++) {
                            idCita = citas.get(i).getIdCita();
                        }   
                        verificador = cn.cancelarCita(idCita);
                        // Si la cita se cancela correctamente...
                        if(verificador != false) {
                            out.println("<h1>La cita seleccionada se canceló exitosamente</h1>");
                            out.println("<a class='button fit special' href='VerCitasdHoy'>Regresar a las citas de hoy</a>");
                        }
                        // Si la cita no se pudo cancelar...
                        else {
                            out.println("<h2 style='color:red;'>La cita no se pudo cancelar. Intente de nuevo, si persiste el problema contácte al desarrollador de la aplicación.</h2>");
                            out.println("<table>");
                            for(int i=0 ; i<tCita ; i++){
                                                out.println("<tr>");
                                                    out.println("<td style='font-weight: bold;'>Servicio:</td>");
                                                    out.println("<td>" + citas.get(0).getDescripcionServ() + "</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                    out.println("<td style='font-weight: bold;'>Especialista:</td>");
                                                    out.println("<td>" + citas.get(0).getNombre() + "</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                    out.println("<td style='font-weight: bold;'>Cliente:</td>");
                                                    out.println("<td>" + citas.get(0).getNomCliente() + "</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                    out.println("<td style='font-weight: bold;'>Fecha:</td>");
                                                    out.println("<td>" + citas.get(0).getFecha() + "</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                    out.println("<td style='font-weight: bold;'>Hora:</td>");
                                                    out.println("<td>" + c.convertAformatHoraSinSegundos(citas.get(0).getHoraCita(), citas.get(0).getMinCita())  + "</td>");
                                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("   <a class='button fit special' href='VerificarCancelacion?idCita=" + idCita + "'>Volver a intentar</a>");
                            out.println("   <a class='button special' href='VerCitasdHoy'>Cancelar</a>");
                            out.println("<br/>");
                        }
                    } else {
                        out.println("<h2 style='color: red;'>Usted no tiene permiso de acceder a esta sección de la aplicación</h2>");
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
            Logger.getLogger(VerificarCancelacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerificarCancelacion.class.getName()).log(Level.SEVERE, null, ex);
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