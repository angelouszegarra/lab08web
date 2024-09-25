package RepuestoDAOImpl;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Repuesto.Repuesto;
import RepuestoDAO.RepuestoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestoDAOImpl implements RepuestoDAO {
    private Connection connection;

    // Constructor que recibe la conexión
    public RepuestoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Repuesto getRepuestoById(int id) {
        Repuesto repuesto = null;
        String sql = "SELECT * FROM repuestos WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                repuesto = new Repuesto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return repuesto;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        List<Repuesto> repuestos = new ArrayList<>();
        String sql = "SELECT * FROM repuestos";
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Repuesto repuesto = new Repuesto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                repuestos.add(repuesto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return repuestos;
    }

    @Override
    public void addRepuesto(Repuesto repuesto) throws SQLException {
        String sql = "INSERT INTO repuestos (nombre, precio) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, repuesto.getNombre());
            pstmt.setDouble(2, repuesto.getPrecio());
            pstmt.executeUpdate();
        }
    }
}
