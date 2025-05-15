package DataAccesObject;

import BusinessEntity.ContratoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

public class ContratoDAO extends ConexionMySQL implements IBaseDAO<ContratoBE> {

    @Override
    public boolean Create(ContratoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ContratoBE Read(String input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ContratoBE> ReadAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(ContratoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM Contrato WHERE Id_Contrato = ?";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(id));
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar contrato: " + e.getMessage());
            return false;
        }
    }

}
