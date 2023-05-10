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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class VerCitasdHoy extends HttpServlet {
    
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privRecepcionista = u.getRecepcionista();
    private final String privEspecialista = u.getEspecialista();
    
    private String privilegioOAB = null;
    
    private String username = "";
    private boolean verifIdentidadAdmin;
    private String usernameOAB;

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
                out.println("<title>Ver citas de hoy</title>");
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
                        if(privilegioOAB.equals(privEspecialista)){
                            out.println("<li><a href='VerCitaspFecha'>Ver citas por fecha</a></li>");
                        }
                        out.println("</ul>");
                        out.println("</li>");
                        out.println("<li><a href='Logout' class=\"button\">Cerrar sesión</a></li>");
                        out.println("</ul>");
                        out.println("</nav>");
                        out.println("</header>");
                // Fin del encabezado
                
                out.println("<section id='banner'>");
                out.println("<div class='content'>");
                out.println("<header>");
                List<Cita> citas = new ArrayList<>();
                ConexionDB cn = new ConexionDB();
                Cita c = new Cita();
                Calendar fa = new GregorianCalendar();
                            int mesInt = fa.get(Calendar.MONTH) + 1;
                            String mes = "";
                            int diaInt = fa.get(Calendar.DAY_OF_MONTH);
                            String dia = "";
                            if(mesInt == 0  ||  mesInt == 1 || mesInt == 2 || mesInt == 3 || mesInt == 4 || mesInt == 5 || mesInt == 6 || 
                                                                                                    mesInt == 7 || mesInt == 8 || mesInt == 9) {
                                mes = "0" + mesInt;
                            } else {
                                mes = mesInt + "";
                            }
                            
                            if(diaInt == 0  ||  diaInt == 1 || diaInt == 2 || diaInt == 3 || diaInt == 4 || diaInt == 5 || diaInt == 6 || 
                                                                                                    diaInt == 7 || diaInt == 8 || diaInt == 9) {
                                dia = "0" + diaInt;
                            } else{
                                dia = diaInt + "";
                            }
                            
                            String fechaActual = fa.get(Calendar.YEAR) + "-" + mes + "-" + dia;
                    if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin))
                        {
                            citas = cn.verCitasDhoy();
                            int tCitas = citas.size();
                            if(tCitas > 0 ) {
                                out.println("<center><h1 style='font-weight: bold;'>Citas de hoy:</h1></center>");
                                out.println("<table>");
                                out.println("<thead>");
                                out.println("<th>Especialista</th>");
                                out.println("<th>Servicio</th>");
                                out.println("<th>Cliente</th>");
                                out.println("<th>Hora</th>");
                                out.println("</thead>");
                                for(int i=0 ; i<tCitas ; i++) {
                                    out.println("<tr>");
                                        // Nombre del especialista
                                        out.println("<td>" + citas.get(i).getNombre() + "</td>");
                                        out.println("<td>" + citas.get(i).getDescripcionServ() + "</td>");
                                        out.println("<td>" + citas.get(i).getNomCliente() + "</td>");
                                        out.println("<td>" + c.convertAformatHoraSinSegundos(citas.get(i).getHoraCita(), citas.get(i).getMinCita()) + "</td>");
                                        out.println("   <td><a class='button special small' href='PosponerCita?idCita=" + citas.get(i).getIdCita() + "'>Posponer</a></td>");
                                        out.println("   <td><a class='button special small' href='CancelarCita?idCita=" + citas.get(i).getIdCita() + "'>Cancelar</a></td>");
                                    out.println("</tr>");
                                }
                                out.println("</table>");
                                out.println("<h3><a class='button fit' href='VerCitasdHoy'>Actualizar</a></h3>");
                            } else{
                                out.println("<h2>No se han registrado citas para el día de hoy</h2>");
                                out.println("<h3><a class='button fit' href='VerCitasdHoy'>Actualizar</a></h3>");
                            }       
                        }
                    else if(privilegioOAB.equals(privEspecialista))
                        {
                            username = (String)sesion.getAttribute("username");
                            sesion.setAttribute("username", username);
                            citas = cn.verCitasDhoyPespecialista(username);
                            int tCitas = citas.size();
                            if(tCitas >= 1) {
                                out.println("<center><h1 style='font-weight: bold;'>Sus citas de hoy:</h1></center>");
                                out.println("<table>");
                                out.println("<thead>");
                                out.println("<th>Servicio:</th>");
                                out.println("<th>Cliente:</th>");
                                out.println("<th>Hora:</th>");
                                out.println("</thead>");
                                for(int i=0 ; i<tCitas ; i++) {
                                            out.println("<tr>");
                                                out.println("<td>" + citas.get(i).getDescripcionServ() + "</td>");
                                                out.println("<td>" + citas.get(i).getNomCliente() + "</td>");
                                                out.println("<td>" + c.convertAformatHoraSinSegundos(citas.get(i).getHoraCita(), citas.get(i).getMinCita()) + "</td>");
                                            out.println("</tr>");
                                }
                                out.println("</table>");
                                out.println("<h3><a class='button fit' href='VerCitasdHoy'>Actualizar</a></h3>");
                            } else {
                                out.println("<h2>Aún no tiene citas programadas para el día de hoy</h2>");
                                out.println("<h3><a class='button fit' href='VerCitasdHoy'>Actualizar</a></h3>");
                            }
                            
                        }
                    else {
                        out.println("<h2 style='color: red;'>Usted no inició sesión debidamente</h2>");
                    }
                
                out.println("</header>");
                out.println("</div>");
                out.println("</section>");
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
            Logger.getLogger(VerCitasdHoy.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerCitasdHoy.class.getName()).log(Level.SEVERE, null, ex);
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
