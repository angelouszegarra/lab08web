package RepuestoController;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DBConfig.DBConfig;
import Repuesto.Repuesto;
import RepuestoDAO.RepuestoDAO;
import RepuestoDAOImpl.RepuestoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/repuestos")
public class RepuestoController extends HttpServlet {
    private RepuestoDAO repuestoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection connection = DBConfig.getConnection(); // Asegúrate de que DBConfig esté configurado correctamente
            repuestoDAO = new RepuestoDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("No se pudo conectar a la base de datos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Repuesto> repuestos = repuestoDAO.getAllRepuestos();
        request.setAttribute("repuestos", repuestos);
        request.getRequestDispatcher("repuestos.jsp").forward(request, response); // Redirigir a la vista JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Repuesto nuevoRepuesto = new Repuesto();
        nuevoRepuesto.setNombre(nombre);
        nuevoRepuesto.setPrecio(precio);

        try {
            repuestoDAO.addRepuesto(nuevoRepuesto);
            response.sendRedirect("repuestos"); // Redirigir después de agregar
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirigir a una página de error si ocurre un problema
        }
    }
}