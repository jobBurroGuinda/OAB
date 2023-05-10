/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.Cliente;
import LogicaOAB.ConexionDB;
import LogicaOAB.Especialista;
import LogicaOAB.Persona;
import LogicaOAB.Servicio;
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
public class SelectFechaCita extends HttpServlet {

    private int idServicio = 0;
    private int idCliente = 0;
    private int idEspecialista = 0;
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private String nomCliente = "";
    private String nomEspecialista = "";
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
            idCliente = Integer.parseInt(request.getParameter("idCliente"));
            idServicio = Integer.parseInt(request.getParameter("idServicio"));
            //idEspecialista = Integer.parseInt(request.getParameter("idEspecialista"));
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            List<Servicio> nomServs = new ArrayList<>();
            List<Especialista> soloEspecialistas = new ArrayList<>();
            int tServicios = 0;
            int tClientes = 0;
            int tEspecialista = 0; 
            List<Cliente> clientes = new ArrayList<>();
            if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                ConexionDB cn = new ConexionDB();
                cn.conectar();
                clientes = (List) sesion.getAttribute("DatosCliente");
                tClientes = clientes.size();
                sesion.setAttribute("DatosCliente", clientes);
                nomServs = (List) sesion.getAttribute("NomServicios");
                tServicios = nomServs.size();
                sesion.setAttribute("NomServicios", nomServs);
                soloEspecialistas = cn.verNomEspecialistaS(idServicio);
                tEspecialista = soloEspecialistas.size();
                sesion.setAttribute("NomEspecialista", soloEspecialistas);
                /*
                soloEspecialistas = (List) sesion.getAttribute("NomEspecialista");
                sesion.setAttribute("NomEspecialista", soloEspecialistas);
                tEspecialista = soloEspecialistas.size();
                */
            }
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Selecciona un especialista</title>");   
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
                
                    out.println("<br/>");
                    out.println("<div id='main' class='wrapper style1'>");
                    out.println("<div class='container'>");
                    out.println("<header class='major'>");
                    if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                            out.println("<form method='POST' action='AgendarCita'>");
                                out.println("<select name='idCliente' id='idCliente'>");
                                Persona p = new Persona();
                                    for(int i=0 ; i<tClientes ; i++) {
                                        if(clientes.get(i).getIdPersona() == idCliente){
                                            nomCliente = p.obtenerNomCompleto(clientes.get(i).getNombre(), clientes.get(i).getApellidoPaterno(), clientes.get(i).getApellidoMaterno());
                                            out.println("<option value='" + idCliente + "'>" + "Cliente: " + nomCliente + "</option>");
                                        }
                                    }
                                out.println("</select>");
                                out.println("<select name='idServicio' id='idServicio'>");
                                // Lista de servicios
                                    for(int i=0 ; i<tServicios ; i++) {
                                        if(nomServs.get(i).getIdServicio() == idServicio){
                                            out.println("<option value='" + idServicio + "'>"+ "Servicio: "+ nomServs.get(i).getDescripcionServ() + "</option>");
                                        }
                                    }
                                out.println("</select>");
                                out.println("<select name='idEspecialista' id='idEspecialista'>");
                                    for(int i=0 ; i<tEspecialista ; i++) {
                                            nomEspecialista = soloEspecialistas.get(i).getNombre();
                                            out.println("<option value='" + soloEspecialistas.get(i).getIdPersona() + "'>Especialista: "+ nomEspecialista + "</option>");
                                    }
                                out.println("</select>");
                                out.println("<br/>");
                            out.println("<h1>Selecciona la fecha de la cita:</h1>");
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
                            out.println("<input type='date' value='"+fechaActual+"' min='"+fechaActual+"' name='fechaCita' id='fechaCita'>");
                            out.println("<br/>");
                            out.println("<h1>Hora:</h1>");
                            out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaCita' id='horaCita'>");
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
                            out.println("</select>");
                            Cita c = new Cita();
                            out.println("<select style=\"width='50%';\" name='minCita' id='minCita'>");
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
                            out.println("</select>");
                            out.println("<br/>");
                                out.println("<input type='submit' class='button fit special' value='Verificar disponibilidad'>");
                                out.println("<a class='button special' href='VerCitasdHoy'>Cancelar</a>");
                            out.println("</form>");
                    }
                    out.println("</header>");
                    out.println("</div>");
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
            
        } else {
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
            Logger.getLogger(SelectFechaCita.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SelectFechaCita.class.getName()).log(Level.SEVERE, null, ex);
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
