package DataAccesObject;

import BusinessEntity.CargoBE;
import java.sql.*;
import java.util.ArrayList;

public class CargoDAO extends ConexionMySQL implements IBaseDAO<CargoBE> {

    @Override
    public boolean Create(CargoBE input) {
        String query = "INSERT INTO Cargo (nombre, sueldo) VALUES (?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getNombre());
            pst.setDouble(2, input.getSueldo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CargoBE Read(int idCargo) {
        String query = "SELECT * FROM Cargo WHERE idCargo = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idCargo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                CargoBE cargo = new CargoBE();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNombre(rs.getString("nombre"));
                cargo.setSueldo(rs.getDouble("sueldo"));
                return cargo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CargoBE> ReadAll() {
        ArrayList<CargoBE> cargos = new ArrayList<>();
        String query = "SELECT * FROM Cargo";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CargoBE cargo = new CargoBE();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNombre(rs.getString("nombre"));
                cargo.setSueldo(rs.getDouble("sueldo"));
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    @Override
    public boolean Update(CargoBE input) {
        String query = "UPDATE Cargo SET nombre = ?, sueldo = ? WHERE idCargo = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getNombre());
            pst.setDouble(2, input.getSueldo());
            pst.setInt(3, input.getIdCargo());

            return pst.executeUpdate() > 0;  // Si la actualización es exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idCargo) {
        String query = "DELETE FROM Cargo WHERE idCargo = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idCargo);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
