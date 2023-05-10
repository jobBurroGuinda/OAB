/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.ConexionDB;
import LogicaOAB.Recepcionista;
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
public class DetallesRecepcionista extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    
    private String privilegioOAB = null;
    
    private int idRecepcionista = 0;
    private int tRecepcionistas = 0;
    
    
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
            List<Recepcionista> recepcionistas = new ArrayList<>();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            if(request.getParameter("idRecepcionista") != null) {
                  idRecepcionista = Integer.parseInt(request.getParameter("idRecepcionista"));
            }
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Detalles sobre el especialista</title>");
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
                        if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
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
                            out.println("<li>");
                            out.println("   <a href='#'>Usuarios</a>");
                            out.println("   <ul>");
                                if(privilegioOAB.equals(privAdmin)){
                                    out.println("           <li>");
                                    out.println("               <a href='#'>Administradores</a>");
                                    out.println("               <ul>");
                                    out.println("                   <li><a href='NvoAdmin'>Nuevo</a></li>");
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
                if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                    out.println("<h2>Buscar recepcionista</h2>");
                    out.println("<form method='POST' action='BusqRecepcionista'>");
                    out.println("<input type='text' id='nomRecepcionista' name='nomRecepcionista' placeholder='Introducir nombre' required>");
                    out.println("<input class='button special' value='Buscar' type='submit'>");
                    out.println("</form>");
                    out.println("<a class='button fit special' href='EditarRecepcionista?idRecepcionista=" + idRecepcionista + "'>Editar</a>");
                    out.println("<table>");
                    if(privilegioOAB.equals(privGerente)) {
                        recepcionistas = cn.buscarRecepcionista_ID(idRecepcionista);
                    } else if(privilegioOAB.equals(privAdmin)){
                        recepcionistas = cn.buscarRecepcionistaA_ID(idRecepcionista);
                    }
                    sesion.setAttribute("DatosRecepcionista", recepcionistas);
                    tRecepcionistas = recepcionistas.size();
                    Cita c = new Cita();
                    if(tRecepcionistas > 0) {
                        for(int i=0 ; i<tRecepcionistas ; i++){
                            if(recepcionistas.get(i).getIdPersona() == idRecepcionista) {
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Nombre de usuario:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getNomUsuario() + "</td>");
                                        out.println("</tr>");
                                        if(privilegioOAB.equals(privAdmin)) {
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>El usuario cuenta con permisos de:</td>");
                                                String privR = recepcionistas.get(0).getPrivilegio();
                                                String privilegioRec = "";
                                                if(null != privR) switch (privR) {
                                                case "E":
                                                    privilegioRec = "Especialista";
                                                    break;
                                                case "R":
                                                    privilegioRec = "Recepcionista";
                                                    break;
                                                case "G":
                                                    privilegioRec = "Gerente";
                                                    break;
                                                case "A":
                                                    privilegioRec = "Administrador";
                                                    break;
                                                default:
                                                    break;
                                            }
                                                out.println("<td>" + privilegioRec + "</td>");
                                            out.println("</tr>");
                                        }
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Nombres:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getNombre() + "</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Apellido paterno:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getApellidoPaterno() + "</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Apellido materno:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getApellidoMaterno() + "</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Teléfono:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getTelefono() + "</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Correo electrónico:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getEmail() + "</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td style='font-weight: bold;'>Facebook:</td>");
                                            out.println("<td>" + recepcionistas.get(0).getFacebook() + "</td>");
                                        out.println("</tr>");
                            }
                        }
                    out.println("</table>");
                            out.println("<a class='button fit special' href='BajaRecepcionista?idRecepcionista=" + idRecepcionista + "'>Dar de baja</a>");
                    } else {
                        out.println("<br/>");
                        out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                        out.println("<h1>Puede que el cliente seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
                        out.println("<h1>Intente buscarlo de nuevo, si aparece en la búsqueda contácte al desarrollador de la aplicación para notificar del fallo, de lo contrario, significa que el cliente definitivamente fue dado de baja</h1>");
                    }////////////////
                    
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
            Logger.getLogger(DetallesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetallesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
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
