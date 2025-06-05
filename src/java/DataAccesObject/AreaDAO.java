package DataAccesObject;

import BusinessEntity.AreaBE;
import java.sql.*;
import java.util.ArrayList;

public class AreaDAO extends ConexionMySQL implements IBaseDAO<AreaBE> {

    @Override
    public boolean Create(AreaBE input) {
        String query = "INSERT INTO Area (nombre, responsable) VALUES (?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getNombre());
            pst.setString(2, input.getResponsable());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public AreaBE Read(int idArea) {
        String query = "SELECT * FROM Area WHERE idArea = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idArea);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                AreaBE area = new AreaBE();
                area.setIdArea(rs.getInt("idArea"));
                area.setNombre(rs.getString("nombre"));
                area.setResponsable(rs.getString("responsable"));
                return area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<AreaBE> ReadAll() {
        ArrayList<AreaBE> areas = new ArrayList<>();
        String query = "SELECT * FROM Area";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                AreaBE area = new AreaBE();
                area.setIdArea(rs.getInt("idArea"));
                area.setNombre(rs.getString("nombre"));
                area.setResponsable(rs.getString("responsable"));
                areas.add(area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areas;
    }

    @Override
    public boolean Update(AreaBE input) {
        String query = "UPDATE Area SET nombre = ?, responsable = ? WHERE idArea = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getNombre());
            pst.setString(2, input.getResponsable());
            pst.setInt(3, input.getIdArea());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idArea) {
        String query = "DELETE FROM Area WHERE idArea = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idArea);
            return pst.executeUpdate() > 0;  // Si la eliminación es exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


