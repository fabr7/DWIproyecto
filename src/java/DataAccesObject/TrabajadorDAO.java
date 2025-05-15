package DataAccesObject;

import BusinessEntity.TrabajadorBE;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TrabajadorDAO extends ConexionMySQL implements IBaseDAO<TrabajadorBE> {

    @Override
    public boolean Create(TrabajadorBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TrabajadorBE Read(String input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TrabajadorBE> ReadAll() {
        ArrayList<TrabajadorBE> lista = new ArrayList<>();
        String sql = "SELECT t.* FROM Trabajador t";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                TrabajadorBE trabajador = new TrabajadorBE();
                trabajador.setIdTrabajador(res.getInt("Id_Trabajador"));
                trabajador.setDni(res.getString("Dni"));
                trabajador.setNombre(res.getString("Nombre"));
                trabajador.setApellidoPaterno(res.getString("Apellido_Paterno"));
                trabajador.setApellidoMaterno(res.getString("Apellido_Materno"));
                trabajador.setFechaNacimiento(res.getDate("Fecha_Nacimiento"));
                trabajador.setTelefono(res.getString("Telefono"));
                trabajador.setEmail(res.getString("Email"));
                trabajador.setIdArea(res.getInt("Id_Area"));
                trabajador.setIdCargo(res.getInt("Id_Cargo"));

                lista.add(trabajador);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar trabajadores: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean Update(TrabajadorBE input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            System.err.println("DNI no puede ser nulo o vacío");
            return false;
        }

        String sql = "DELETE FROM Trabajador WHERE Dni = ?";

        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, dni);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar trabajador con DNI " + dni + ": " + e.getMessage());
            return false;
        }
    }

}
