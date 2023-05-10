/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.ConexionDB;
import LogicaOAB.Gerente;
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
public class EditarGerente extends HttpServlet {
    
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    
    private String privilegioOAB = null;
    

    private int idGerente = 0;
    private int tGerentes = 0;
    
    private String nomGerente = "";
    private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private long telefono = 0;
    private String email = "";
    private String facebook = "";
    private String nomUsuario;
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
            List<Gerente> gerentes = new ArrayList<>();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            if(privilegioOAB.equals(privAdmin)) {
                        if(request.getParameter("idGerente") != null) {
                            idGerente = Integer.parseInt(request.getParameter("idGerente"));
                        }
                            gerentes = cn.buscarGerenteA_ID(idGerente);
                        sesion.setAttribute("DatosGerente", gerentes);
                        tGerentes = gerentes.size();
            }
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Editar datos del gerente</title>");
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
                                    usernameOAB = (String)sesion.getAttribute("username");
                                    verifIdentidadAdmin = cn.verificarIdentidadAdministrador(usernameOAB);
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
                if(privilegioOAB.equals(privAdmin)){
                    if(tGerentes > 0) {
                        for(int i=0 ; i<tGerentes ; i++) {
                            nomUsuario = gerentes.get(i).getNomUsuario();
                            nomGerente = gerentes.get(i).getNombre();
                            apellidoPaterno = gerentes.get(i).getApellidoPaterno();
                            apellidoMaterno = gerentes.get(i).getApellidoMaterno();
                            telefono = gerentes.get(i).getTelefono();
                            email = gerentes.get(i).getEmail();
                            facebook = gerentes.get(i).getFacebook();
                            out.println("<h3>Modificar datos del gerente: '" + nomGerente + "'</h3>");
                                out.println("<br/>");
                            out.println("<form method='POST' action='ActualizarGerente'>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombre de usuario *' name='nomUsuario' value='" + nomUsuario + "' id='nomUsuario' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' pattern=\".{8,}\" placeholder='Nueva contraseña *' title='La contrtaseña debe contener mínimo 8 caracteres' name='passw' id='passw' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' pattern=\".{8,}\" placeholder='Repetir contraseña *' title='Mínimo 8 caracteres' name='passw2' id='passw2' required/>");
                                out.println("</div>");
                                    out.println("<div class='row uniform 50%'>");
                                        out.println("<select style=\"width='50%';\" placeholder='privilegio' name='privilegio' id='privilegio' required>");
                                        switch (gerentes.get(i).getIdPrivilegio()) {
                                            case 2:
                                                out.println("<option selected='selected' value='2'>El usuario cuenta con permisos de 'especialista'</option>");
                                                out.println("<option value='1'>Asignarle permisos de 'recepcionista'</option>");
                                                out.println("<option value='4'>Asignarle permisos de 'gerente'</option>");
                                                out.println("<option value='3'>Asignarle permisos de 'administrador'</option>");
                                                break;
                                            case 1:
                                                out.println("<option selected='selected' value='1'>El usuario cuenta con permisos de 'recepcionista'</option>");
                                                out.println("<option value='2'>Asignarle permisos de 'especialista'</option>");
                                                out.println("<option value='4'>Asignarle permisos de 'gerente'</option>");
                                                out.println("<option value='3'>Asignarle permisos de 'administrador'</option>");
                                                break;
                                            case 4:
                                                out.println("<option selected='selected' value='4'>El usuario cuenta con permisos de 'gerente'</option>");
                                                out.println("<option value='2'>Asignarle permisos de 'especialista'</option>");
                                                out.println("<option value='1'>Asignarle permisos de 'recepcionista'</option>");
                                                out.println("<option value='3'>Asignarle permisos de 'administrador'</option>");
                                                break;
                                            case 3:
                                                out.println("<option selected='selected' value='3'>El usuario cuenta con permisos de 'administrador'</option>");
                                                out.println("<option value='1'>Asignarle permisos de 'recepcionista'</option>");
                                                out.println("<option value='2'>Asignarle permisos de 'especialista'</option>");
                                                out.println("<option value='4'>Asignarle permisos de 'gerente'</option>");
                                                break;
                                            default:
                                                break;
                                        }
                                        out.println("</select>");
                                    out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombres *' name='nombres' id='nombres' value='"+ nomGerente+"' required/>");
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
                    }
                    // Si no existe ningún cliente registrado con ese ID sería porque otro usuario lo dio de baja en un instante específico
                    // Momentos después de que este usuario haya consultado sus datos, en tal caso aparecerá este mensaje:
                    else {
                        out.println("<h2>Buscar gerente</h2>");
                        out.println("<form method='POST' action='BusqGerente'>");
                        out.println("<input type='text' id='nomGerente' name='nomGerente' autocomplete='on' placeholder='Introducir nombre' required>");
                        out.println("<input class='button special' value='Buscar' type='submit'>");
                        out.println("</form>");
                        out.println("<br/>");
                        out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                        out.println("<h1>Puede que el cliente seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
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
            Logger.getLogger(EditarGerente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditarGerente.class.getName()).log(Level.SEVERE, null, ex);
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
