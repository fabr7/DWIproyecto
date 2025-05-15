package DataAccesObject;

import BusinessEntity.CertificadoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

public class CertificadoDAO extends ConexionMySQL implements IBaseDAO<CertificadoBE> {

    @Override
    public boolean Create(CertificadoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CertificadoBE Read(String input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CertificadoBE> ReadAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(CertificadoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM Certificado WHERE Id_Certificado = ?";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(id));
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar certificado: " + e.getMessage());
            return false;
        }

    }

}
