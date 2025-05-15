package DataAccesObject;

import BusinessEntity.CargoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

public class CargoDAO extends ConexionMySQL implements IBaseDAO<CargoBE> {

    @Override
    public boolean Create(CargoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CargoBE Read(String input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CargoBE> ReadAll() {
        ArrayList<CargoBE> cargos = new ArrayList<>();
        String sql = "SELECT * FROM Cargo ORDER BY Nombre";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                CargoBE cargo = new CargoBE();
                cargo.setIdCargo(res.getInt("Id_Cargo"));
                cargo.setNombre(res.getString("Nombre"));
                cargo.setSueldo(res.getDouble("Sueldo"));
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cargos: " + e.getMessage());
        }

        return cargos;
    }

    @Override
    public boolean Update(CargoBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM Cargo WHERE Id_Cargo = ?";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(id));
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cargo: " + e.getMessage());
            return false;
        }

    }
}
