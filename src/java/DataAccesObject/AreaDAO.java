package DataAccesObject;

import BusinessEntity.AreaBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaDAO extends ConexionMySQL implements IBaseDAO<AreaBE> {

    @Override
    public boolean Create(AreaBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AreaBE Read(String input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AreaBE> ReadAll() {
        ArrayList<AreaBE> areas = new ArrayList<>();
        String sql = "SELECT * FROM Area";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                AreaBE area = new AreaBE();
                area.setIdArea(res.getInt("Id_Area"));
                area.setNombre(res.getString("Nombre"));
                area.setResponsable(res.getString("Responsable"));
                areas.add(area);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar áreas: " + e.getMessage());
        }

        return areas;
    }

    @Override
    public boolean Update(AreaBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM Area WHERE Id_Area = ?";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(id));
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar área: " + e.getMessage());
            return false;
        }
    }

}
