/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.ConexionDB;
import LogicaOAB.Especialista;
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
public class ActualizarEspecialista extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privEspecialista = u.getEspecialista();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private String password = "";
    private int idEspecialista = 0;
    private int idContacto = 0;
    private int idUsuario = 0;
    private int idHorarioTrab = 0; 
    private int tEspecialistas = 0;
    private int tServicios = 0;
    private int idPrivilegio = 0;
    private String nomEspecialista = "";
    private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private String nomUsuario = "";
    private Long telefono;
    private String email = "";
    private String facebook = "";
    
    private String nameServicio = "";
    private int idServicio = 0;
    
    // Hora comida
    private int horaComidaInt = 0;
    private double minComida = 0.0;
    // Hora entrada
    private int horaEntradaInt = 0;
    private double minEntrada = 0.0;
    // Hora salida
    private int horaSalidaInt = 0;
    private double minSalida = 0.0;
    
    private String horaComida = "";
    private String horaEntrada = "";
    private String horaSalida = "";
    
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession sesion = request.getSession(false);
        
        if(sesion != null){boolean checador = false;
            ConexionDB cn = new ConexionDB();
            List<Integer> serviciosSeleccionados = new ArrayList<>();
            List<Servicio> servicios = new ArrayList<>();
            List<Especialista>especialistas = new ArrayList<>();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                nomUsuario = request.getParameter("nomUsuario");
                password = request.getParameter("passw");
                if(privilegioOAB.equals(privAdmin)){
                    idPrivilegio = Integer.parseInt(request.getParameter("privilegio"));
                    usernameOAB = (String)sesion.getAttribute("username");
                    verifIdentidadAdmin = cn.verificarIdentidadAdministrador(usernameOAB);
                }
                nomEspecialista = request.getParameter("nombres");
                apellidoPaterno = request.getParameter("apellidoPaterno");
                apellidoMaterno = request.getParameter("apellidoMaterno");
                telefono = Long.parseLong(request.getParameter("telefono"));
                email = request.getParameter("email");
                facebook = request.getParameter("facebook");
                horaComidaInt = Integer.parseInt(request.getParameter("horaComida"));
                minComida = Double.parseDouble(request.getParameter("minComida"));
                horaEntradaInt = Integer.parseInt(request.getParameter("horaEntrada"));
                minEntrada = Double.parseDouble(request.getParameter("minEntrada"));
                horaSalidaInt = Integer.parseInt(request.getParameter("horaSalida"));
                minSalida = Double.parseDouble(request.getParameter("minSalida"));
                especialistas = (List)sesion.getAttribute("DatosEspecialista");
                tEspecialistas = especialistas.size();
                for(int i=0 ; i<tEspecialistas ; i++) {
                    idEspecialista = especialistas.get(i).getIdPersona();
                    idContacto = especialistas.get(i).getIdContacto();
                    idUsuario = especialistas.get(i).getIdUsuario();
                    idHorarioTrab = especialistas.get(i).getIdHorarioTrabajo();
                }
                Cita c = new Cita();
                horaComida = c.convertirAformatoHora(horaComidaInt, minComida);
                horaEntrada = c.convertirAformatoHora(horaEntradaInt, minEntrada);
                horaSalida = c.convertirAformatoHora(horaSalidaInt, minSalida);
                servicios = cn.desplegarServicios();
                        for(int i=0 ; i<servicios.size() ; i++) {
                            nameServicio = "servicio" + servicios.get(i).getIdServicio();
                            if(request.getParameter(nameServicio) != null) {
                                idServicio = Integer.parseInt(request.getParameter(nameServicio));
                                serviciosSeleccionados.add(idServicio);
                            }
                        }
                    if(privilegioOAB.equals(privAdmin)){
                        verificadorAct = cn.actualizarEspecialistaA(idUsuario, nomUsuario, password, idPrivilegio, idEspecialista, nomEspecialista,
                                                        apellidoPaterno, apellidoMaterno, idContacto, telefono, email, facebook, 
                                                            horaComida, idHorarioTrab, horaEntrada, horaSalida, serviciosSeleccionados);
                    } else {
                        verificadorAct = cn.actualizarEspecialista(idUsuario, nomUsuario, password, idEspecialista, nomEspecialista,
                                                        apellidoPaterno, apellidoMaterno, idContacto, telefono, email, facebook, 
                                                            horaComida, idHorarioTrab, horaEntrada, horaSalida, serviciosSeleccionados);
                    }
                
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
                        if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
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
                
                if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                        Cita c = new Cita();
                    if(verificadorAct != true) {
                    // Si la actualización no se realizó correctamente...
                        out.println("<h3>Actualización del especialista</h3>");
                                out.println("<h1 style='color:red;'>Los datos no se pudieron actualizar, intente de nuevo. Si persiste el problema contácte al desarrollador de la aplicación</h1>");
                                out.println("<br/>");
                            out.println("<form method='POST' action='ActualizarEspecialista'>");
                        for(int i=0 ; i<tEspecialistas ; i++) {
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombre de usuario *' name='nomUsuario' value='" + nomUsuario + "' id='nomUsuario' disabled required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' pattern=\".{8,}\" placeholder='Nueva contraseña *' title='La contrtaseña debe contener mínimo 8 caracteres' name='passw' id='passw' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' pattern=\".{8,}\" placeholder='Repetir contraseña *' title='Mínimo 8 caracteres' name='passw2' id='passw2' required/>");
                                out.println("</div>");
                                if(privilegioOAB.equals(privAdmin)) {
                                    out.println("<div class='row uniform 50%'>");
                                        out.println("<select style=\"width='50%';\" placeholder='privilegio' name='privilegio' id='privilegio' required>");
                                        switch (idPrivilegio) {
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
                                }
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombres *' name='nombres' id='nombres' value='"+ nomEspecialista+"' required/>");
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
                                    out.println("<h1 style='font-weight:bold;'>--Hora de comida--</h1>");
                                    out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaComida' id='horaComida' required>");
                                    switch (horaComidaInt) {
                                        case 11:
                                            out.println("<option value='11' selected='selected'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 12:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12' selected='selected'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 13:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13' selected='selected'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 14:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14' selected='selected'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15' selected='selected'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 16:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16' selected='selected'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 17:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17' selected='selected'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 18:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18' selected='selected'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 19:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19' selected='selected'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20' selected='selected'>8 pm</option>");
                                            break;
                                        default:
                                            break;
                                    }
                                    out.println("</select>");
                                    out.println("<select style=\"width='50%';\" name='minComida' id='minComida' required>");
                                    int minutosComida = (int)minComida;
                                    switch (minutosComida) {
                                        case 0:
                                            out.println("<option value='0' selected='selected'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 5:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option selected='selected' value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 10:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option selected='selected' value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value=' selected='selected' value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option selected='selected' value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 25:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option selected='selected' value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 30:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option selected='selected' value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 35:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option selected='selected' value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 40:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option selected='selected' value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 45:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option selected='selected' value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 50:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option selected='selected' value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 55:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option selected='selected' value='" + c.min55() + "'>55</option>");
                                            break;
                                        default:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                    }
                                    out.println("</select>");
                                    out.println("<br/>");
                                    // Hora de entrada
                                    out.println("<h1 style='font-weight:bold;'>--Horario de trabajo--</h1>");
                                    out.println("<h1>Hora de entrada:</h1>");
                                    out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaEntrada' id='horaEntrada' required>");
                                    switch (horaEntradaInt) {
                                        case 11:
                                            out.println("<option value='11' selected='selected'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 12:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12' selected='selected'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 13:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13' selected='selected'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 14:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14' selected='selected'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15' selected='selected'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 16:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16' selected='selected'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 17:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17' selected='selected'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 18:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18' selected='selected'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 19:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19' selected='selected'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20' selected='selected'>8 pm</option>");
                                            break;
                                        default:
                                            break;
                                    }
                                    out.println("</select>");
                                    int minutosEntrada = (int)minEntrada;
                                    out.println("<select style=\"width='50%';\" name='minEntrada' id='minEntrada' required>");
                                    switch (minutosEntrada) {
                                        case 0:
                                            out.println("<option value='0' selected='selected'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 5:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option selected='selected' value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 10:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option selected='selected' value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value=' selected='selected' value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option selected='selected' value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 25:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option selected='selected' value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 30:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option selected='selected' value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 35:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option selected='selected' value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 40:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option selected='selected' value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 45:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option selected='selected' value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 50:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option selected='selected' value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 55:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option selected='selected' value='" + c.min55() + "'>55</option>");
                                            break;
                                        default:
                                            break;
                                    }
                                    out.println("</select>");
                                    out.println("<br/>");
                                    // Hora de salida
                                    out.println("<h1>Hora de salida:</h1>");
                                    out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaSalida' id='horaSalida' required>");
                                    switch (horaSalidaInt) {
                                        case 11:
                                            out.println("<option value='11' selected='selected'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 12:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12' selected='selected'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 13:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13' selected='selected'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 14:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14' selected='selected'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15' selected='selected'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 16:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16' selected='selected'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 17:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17' selected='selected'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 18:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18' selected='selected'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 19:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19' selected='selected'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20' selected='selected'>8 pm</option>");
                                            break;
                                        default:
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                    }
                                    out.println("</select>");
                                    int minutosSalida = (int)minSalida;
                                    out.println("<select style=\"width='50%';\" name='minSalida' id='minSalida' required>");
                                    switch (minutosSalida) {
                                        case 0:
                                            out.println("<option value='0' selected='selected'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 5:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option selected='selected' value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 10:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option selected='selected' value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 15:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value=' selected='selected' value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 20:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option selected='selected' value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 25:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option selected='selected' value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 30:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option selected='selected' value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 35:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option selected='selected' value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 40:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option selected='selected' value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 45:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option selected='selected' value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 50:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option selected='selected' value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                            break;
                                        case 55:
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option selected='selected' value='" + c.min55() + "'>55</option>");
                                            break;
                                        default:
                                            break;
                                    }
                                out.println("</select>");
                                out.println("<br/>");
                        }
                            List<Servicio> todosServicios = new ArrayList<>();
                            String selected = "";
                            todosServicios = cn.desplegarServicios();
                            for(int i=0 ; i<todosServicios.size() ; i++) {
                                    nameServicio = "servicio" + todosServicios.get(i).getIdServicio();
                                    idServicio = todosServicios.get(i).getIdServicio();
                                    String servicio = todosServicios.get(i).getDescripcionServ();
                                if(request.getParameter(nameServicio) != null) {
                                    idServicio = Integer.parseInt(request.getParameter(nameServicio));
                                    serviciosSeleccionados.add(idServicio);
                                }
                                for (int j=0 ; j<serviciosSeleccionados.size() ; j++) {
                                    // Si el ID del servicio desplegado se encuentra registrado entre los ID's de servicio del especialista
                                    // Que se seleccione automáticamente la casilla o chekbox
                                    if(idServicio == serviciosSeleccionados.get(j)) {
                                        selected = " checked";
                                    }
                                }
                                out.println("<input type=\"checkbox\"" + selected + " id=\"" + nameServicio + "\" value=\"" + idServicio + "\" name=\"" + nameServicio + " \"><label for=\"" + nameServicio + "\">" + servicio + "</label>");
                            }
                             out.println("   <input type='submit' class='button fit special' value='Actualizar'/>");
                                out.println("   <input type='reset' class='button special' value='Borrar todo'/>");
                             out.println("</form>");
                    }
                    // De lo contrario..., si los datos del especialista se actualizaron correctamente
                    else {
                             servicios = cn.verNomServiciospEsp(idEspecialista);
                                    tServicios = servicios.size();
                                    if(privilegioOAB.equals(privGerente)) {
                                        especialistas = cn.buscarEspecialista_ID(idEspecialista);
                                    } else if(privilegioOAB.equals(privAdmin)){
                                        especialistas = cn.buscarEspecialistaA_ID(idEspecialista);
                                    }
                            if(especialistas.size() > 0) {
                                    out.println("<h2>Buscar especialista</h2>");
                                    out.println("<form method='POST' action='BusqEspecialista'>");
                                    out.println("<input type='text' id='nomEspecialista' name='nomEspecialista' placeholder='Introducir nombre' required>");
                                    out.println("<input class='button special' value='Buscar' type='submit'>");
                                    out.println("</form>");
                                    out.println("<h4 style='color:green;'>¡Los datos se han actualizado exitosamente!</h4>");
                                    out.println("<a class='button fit special' href='EditarEspecialista?idEspecialista=" + idEspecialista + "'>Editar</a>");
                                    out.println("<table>");
                                    sesion.setAttribute("DatosEspecialista", especialistas);
                                    sesion.setAttribute("ServiciospEsp", servicios);
                                    tEspecialistas = especialistas.size();
                                    if(especialistas.size() > 0) {
                                        for(int i=0 ; i<tEspecialistas ; i++){
                                            horaEntradaInt = especialistas.get(i).getHoraEntrada();
                                            minEntrada = especialistas.get(i).getMinEntrada();
                                            horaSalidaInt = especialistas.get(i).getHoraSalida();
                                            minSalida = especialistas.get(i).getMinSalida();
                                            horaComidaInt = especialistas.get(i).getHoraComida();
                                            minComida = especialistas.get(i).getMinComida();
                                            horaEntrada = c.convertAformatHoraSinSegundos(especialistas.get(i).getHoraEntrada(), especialistas.get(i).getMinEntrada());
                                            horaSalida = c.convertAformatHoraSinSegundos(especialistas.get(i).getHoraSalida(), especialistas.get(i).getMinSalida());
                                            horaComida = c.convertAformatHoraSinSegundos(especialistas.get(i).getHoraComida(), especialistas.get(i).getMinComida());
                                            if(especialistas.get(i).getIdPersona() == idEspecialista) {
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Nombre de usuario:</td>");
                                                            out.println("<td>" + especialistas.get(0).getNomUsuario() + "</td>");
                                                        out.println("</tr>");
                                                        if(privilegioOAB.equals(privAdmin)) {
                                                            out.println("<tr>");
                                                                out.println("<td style='font-weight: bold;'>El usuario cuenta con permisos de:</td>");
                                                                String privE = especialistas.get(0).getPrivilegio();
                                                                String privilegioEsp = "";
                                                                if(null != privE) switch (privE) {
                                                                case "E":
                                                                    privilegioEsp = "Especialista";
                                                                    break;
                                                                case "R":
                                                                    privilegioEsp = "Recepcionista";
                                                                    break;
                                                                case "G":
                                                                    privilegioEsp = "Gerente";
                                                                    break;
                                                                case "A":
                                                                    privilegioEsp = "Administrador";
                                                                    break;
                                                                default:
                                                                    break;
                                                            }
                                                                out.println("<td>" + privilegioEsp + "</td>");
                                                            out.println("</tr>");
                                                        }
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Nombres:</td>");
                                                            out.println("<td>" + especialistas.get(0).getNombre() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Apellido paterno:</td>");
                                                            out.println("<td>" + especialistas.get(0).getApellidoPaterno() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Apellido materno:</td>");
                                                            out.println("<td>" + especialistas.get(0).getApellidoMaterno() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Teléfono:</td>");
                                                            out.println("<td>" + especialistas.get(0).getTelefono() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Correo electrónico:</td>");
                                                            out.println("<td>" + especialistas.get(0).getEmail() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Facebook:</td>");
                                                            out.println("<td>" + especialistas.get(0).getFacebook() + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Hora de comida:</td>");
                                                            out.println("<td>" + horaComida + "</td>");
                                                        out.println("</tr>");
                                                        out.println("<tr>");
                                                            out.println("<td style='font-weight: bold;'>Horario de trabajo:</td>");
                                                            out.println("<td>De: " + horaEntrada + " - a: " + horaSalida + "</td>");
                                                        out.println("</tr>");
                                            }
                                        }
                                                        out.println("<tr>");
                                                        out.println("<td style='font-weight: bold;'>Servicios que realiza:</td>");
                                                            out.println("<td>");
                                                            for(int j=0 ; j<tServicios ; j++) {
                                                                out.println(servicios.get(j).getDescripcionServ() + ", ");
                                                            }
                                                            out.println("</td>");
                                                        out.println("</tr>");
                                    out.println("</table>");
                                    if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                                        out.println("<a class='button fit special' href='BajaEspecialista?idEspecialista=" + idEspecialista + "'>Dar de baja</a>");
                                    }
                                } else {
                                    out.println("<br/>");
                                    out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                                    out.println("<h1>Puede que el especialista seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
                                    out.println("<h1>Intente buscarlo de nuevo, si aparece en la búsqueda contácte al desarrollador de la aplicación para notificar del fallo, de lo contrario, significa que el cliente definitivamente fue dado de baja</h1>");
                                }
                        } else {
                            out.println("<br/>");
                            out.println("<h1 style='color: red;'>¡Ha ocurrido un error!</h1>");
                            out.println("<h1>Puede que el especialista seleccionado haya sido dado de baja por otro usuario, verifique que el ID del ciente se encuentre en el link.</h1>");
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
            
        } else{
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
            Logger.getLogger(ActualizarEspecialista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActualizarEspecialista.class.getName()).log(Level.SEVERE, null, ex);
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
    }     // </editor-fold>
      
}