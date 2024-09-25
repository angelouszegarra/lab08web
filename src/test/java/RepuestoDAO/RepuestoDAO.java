package RepuestoDAO;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Repuesto.Repuesto;
import java.sql.SQLException;
import java.util.List;

public interface RepuestoDAO {
    Repuesto getRepuestoById(int id);
    List<Repuesto> getAllRepuestos();
    void addRepuesto(Repuesto repuesto) throws SQLException;
}
