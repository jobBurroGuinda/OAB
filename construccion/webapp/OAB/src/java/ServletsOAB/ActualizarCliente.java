/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cliente;
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
 * Online Appointment Book (OAB), orgullosamente hecho por manos batiztianas
 */
public class ActualizarCliente extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privEspecialista = u.getEspecialista();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private int idCliente = 0;
    private int tClientes = 0;
    
    private String nomCliente = "";
    private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private long telefono = 0;
    private String email = "";
    private String facebook = "";
    private int idContacto = 0;
    private boolean verificadorAct = false;
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
            List<Cliente> clientes = new ArrayList<>();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                if(privilegioOAB.equals(privAdmin)){
                    usernameOAB = (String)sesion.getAttribute("username");
                    verifIdentidadAdmin = cn.verificarIdentidadAdministrador(usernameOAB);
                }
                nomCliente = request.getParameter("nombres");
                apellidoPaterno = request.getParameter("apellidoPaterno");
                apellidoMaterno = request.getParameter("apellidoMaterno");
                telefono = Long.parseLong(request.getParameter("telefono"));
                email = request.getParameter("email");
                facebook = request.getParameter("facebook");
                        clientes = (List)sesion.getAttribute("DatosCliente");
                        tClientes = clientes.size();
                for(int i=0 ; i<tClientes ; i++) {
                    idCliente = clientes.get(i).getIdPersona();
                    idContacto = clientes.get(i).getIdContacto();
                }
                verificadorAct = cn.actualizarCliente(idCliente, nomCliente, apellidoPaterno, apellidoMaterno, idContacto, telefono, email, facebook);
            }
            
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Actualizar datos del cliente</title>");
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
                        if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
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
                if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                    // Si la actualización no se realizó correctamente...
                    if(verificadorAct != true) {
                                out.println("<h3>Modificar datos del cliente : '" + nomCliente + "'</h3>");
                                out.println("<h1 style='color:red;'>Los datos no se pudieron actualizar, intente de nuevo. Si persiste el problema contácte al desarrollador de la aplicación</h1>");
                                    out.println("<br/>");
                                out.println("<form method='POST' action='ActualizarCliente'>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='text' placeholder='Nombres *' name='nombres' id='nombres' value='"+ nomCliente+"' required/>");
                                    out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='text' placeholder='Apellido paterno *' name='apellidoPaterno' id='apellidoPaterno' value='" + apellidoPaterno + "' required/>");
                                    out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='text' placeholder='Apellido materno *' name='apellidoMaterno' id='apellidoMaterno' value='" + apellidoMaterno + "' required/>");
                                    out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='tel' placeholder='Teléfono *' pattern='[0-9]{10}' name='telefono' id='telefono' value='" + telefono + "' required/>");
                                    out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='email' placeholder='Correo electrónico *' name='email' id='email' value='" + email + "' required/>");
                                    out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                    out.println("   <input type='text' placeholder='Cuenta de facebook (opcional)' name='facebook' id='facebook' value='"+ facebook+"'/>");
                                    out.println("</div>");
                                    out.println("<br/>");
                                    out.println("   <input type='submit' class='button fit special' value='Actualizar'/>");
                                    out.println("   <input type='reset' class='button special' value='Borrar todo'/>");
                                out.println("</form>");
                    }
                    // De lo contrario..., si los datos del cliente se actualizaron correctamente
                    else {
                        clientes = null;
                        clientes = cn.buscarClientepID(idCliente);
                        sesion.setAttribute("DatosCliente", clientes);
                            if(clientes.size() > 0) {
                                out.println("<h2>Buscar cliente</h2>");
                                out.println("<form method='POST' action='BusqCliente'>");
                                out.println("<input type='text' id='nomCliente' name='nomCliente' placeholder='Introducir nombre del cliente' required>");
                                out.println("<input class='button special' value='Buscar' type='submit'>");
                                out.println("</form>");
                                out.println("<h1 style='color:green;'>Los datos del cliente se han actualizado exitosamente</h1>");
                                out.println("<a class='button fit special' href='EditarCliente?idCliente=" + idCliente + "'>Editar</a>");
                                out.println("<table>");
                            for(int i=0 ; i<tClientes ; i++){
                                if(clientes.get(i).getIdPersona() == idCliente) {
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Nombres:</td>");
                                                out.println("<td>" + clientes.get(0).getNombre() + "</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Apellido paterno:</td>");
                                                out.println("<td>" + clientes.get(0).getApellidoPaterno() + "</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Apellido materno:</td>");
                                                out.println("<td>" + clientes.get(0).getApellidoMaterno() + "</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Teléfono:</td>");
                                                out.println("<td>" + clientes.get(0).getTelefono() + "</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Correo electrónico:</td>");
                                                out.println("<td>" + clientes.get(0).getEmail() + "</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                                out.println("<td style='font-weight: bold;'>Facebook:</td>");
                                                out.println("<td>" + clientes.get(0).getFacebook() + "</td>");
                                            out.println("</tr>");
                                }
                            }
                            out.println("</table>");
                            if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                                out.println("<a class='button fit special' href='BajaCliente?idCliente=" + idCliente + "'>Dar de baja</a>");
                            }
                        } else {
                            out.println("<br/>");
                            out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                            out.println("<h1>Puede que el cliente seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
                            out.println("<h1>Intente buscarlo de nuevo, si aparece en la búsqueda contácte al desarrollador de la aplicación para notificar del fallo, de lo contrario, significa que el cliente definitivamente fue dado de baja</h1>");
                        }
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
            Logger.getLogger(ActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
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
