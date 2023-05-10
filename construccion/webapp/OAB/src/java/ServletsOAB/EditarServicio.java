/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.ConexionDB;
import LogicaOAB.Servicio;
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
public class EditarServicio extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private int idServicio = 0;
    private int tServicios = 0;
    
    private String servicio = "";
    private int horaDuracion = 0;
    private int minutosDuracion = 0;
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
            ConexionDB cn = new ConexionDB();
            List<Servicio> servicios = new ArrayList<>();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                        if(request.getParameter("idServicio") != null) {
                            idServicio = Integer.parseInt(request.getParameter("idServicio"));
                        }
                        servicios = cn.desplegarServiciosCompleto(idServicio);
                        sesion.setAttribute("DatosServicio", servicios);
                        tServicios = servicios.size();
            }
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Editar servicio</title>");
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
                if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                    if(tServicios > 0) {
                        for(int i=0 ; i<tServicios ; i++) {
                            servicio = servicios.get(i).getDescripcionServ();
                            horaDuracion = servicios.get(i).getHoraDuracion();
                            minutosDuracion = (int)servicios.get(i).getMinDuracion();
                                out.println("<h3>Editar servicio</h3>");
                                    out.println("<br/>");
                                out.println("<form method='POST' action='ActualizarServicio'>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='text' value='" + servicio + "' placeholder='Introduzca una descripción breve del servicio *' name='servicio' id='servicio' required/>");
                                    out.println("</div>");
                                    out.println("<br/>");
                                    out.println("<h4 style='font-weight:bold;'>Duración</h4>");
                                    out.println("<h5>Horas</h5><br>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='number' value='" + horaDuracion + "' placeholder='Si el servicio dura menos de una hora, puede escribir en este campo el cero *' name='horas' id='horas' required/>");
                                    out.println("</div>");
                                    out.println("<br/>");
                                    out.println("<h5>Minutos</h5>");
                                    out.println("<select style=\"width='50%';\" name='minutos' id='minutos' required>");
                                        switch (minutosDuracion) {
                                            case 0:
                                                out.println("<option selected='selected' value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 5:
                                                out.println("<option selected='selected' value='5'>05</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 10:
                                                out.println("<option selected='selected' value='10'>10</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 15:
                                                out.println("<option selected='selected' value='15'>15</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 20:
                                                out.println("<option selected='selected' value='20'>20</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 25:
                                                out.println("<option selected='selected' value='25'>25</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 30:
                                                out.println("<option selected='selected' value='30'>30</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 35:
                                                out.println("<option selected='selected' value='35'>35</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 40:
                                                out.println("<option selected='selected' value='40'>40</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 45:
                                                out.println("<option selected='selected' value='45'>45</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 50:
                                                out.println("<option selected='selected' value='50'>50</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                            case 55:
                                                out.println("<option selected='selected' value='55'>55</option>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                break;
                                            default:
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='5'>05</option>");
                                                out.println("<option value='10'>10</option>");
                                                out.println("<option value='15'>15</option>");
                                                out.println("<option value='20'>20</option>");
                                                out.println("<option value='25'>25</option>");
                                                out.println("<option value='30'>30</option>");
                                                out.println("<option value='35'>35</option>");
                                                out.println("<option value='40'>40</option>");
                                                out.println("<option value='45'>45</option>");
                                                out.println("<option value='50'>50</option>");
                                                out.println("<option value='55'>55</option>");
                                                break;
                                        }
                                    out.println("</select>");
                                    out.println("<br/>");
                                    out.println("   <input type='submit' class='button fit special' value='Actualizar'/>");
                                    out.println("   <input type='reset' class='button special' value='Borrar todo'/>");
                                out.println("</form>");
                        }
                    }
                    // Si no existe ningún cliente registrado con ese ID sería porque otro usuario lo dio de baja en un instante específico
                    // Momentos después de que este usuario haya consultado sus datos, en tal caso aparecerá este mensaje:
                    else {
                        out.println("<h2>Buscar servicio</h2>");
                        out.println("<form method='POST' action='VerServicios'>");
                        out.println("<input type='text' id='nomServicio' name='nomServicio' autocomplete='on' placeholder='Introducir descripcion del servicio' required>");
                        out.println("<input class='button special' value='Buscar' type='submit'>");
                        out.println("</form>");
                        out.println("<br/>");
                        out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                        out.println("<h1>Puede que el servicio seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
                        out.println("<h1>Intente buscarlo de nuevo, si aparece en la búsqueda contácte al desarrollador de la aplicación para notificar el fallo, de lo contrario, significa que el cliente definitivamente fue dado de baja</h1>");
                    }
                } else {
                    out.println("<h2 style='color: red;'>Usted no tiene permiso de acceder a esta sessión de la aplicación</h2>");
                }
                out.println("</header>");
                out.println("</div>");
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
            Logger.getLogger(EditarServicio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditarServicio.class.getName()).log(Level.SEVERE, null, ex);
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
