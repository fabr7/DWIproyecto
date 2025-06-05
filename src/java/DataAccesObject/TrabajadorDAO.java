package DataAccesObject;

import BusinessEntity.TrabajadorBE;
import java.sql.*;
import java.util.ArrayList;

public class TrabajadorDAO extends ConexionMySQL implements IBaseDAO<TrabajadorBE> {

    @Override
    public boolean Create(TrabajadorBE trabajador) {
        String query = "INSERT INTO Trabajador (dni, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, telefono, email, idArea, idCargo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, trabajador.getDni());
            pst.setString(2, trabajador.getNombre());
            pst.setString(3, trabajador.getApellidoPaterno());
            pst.setString(4, trabajador.getApellidoMaterno());
            pst.setDate(5, Date.valueOf(trabajador.getFechaNacimiento()));
            pst.setString(6, trabajador.getTelefono());
            pst.setString(7, trabajador.getEmail());
            pst.setInt(8, trabajador.getIdArea());
            pst.setInt(9, trabajador.getIdCargo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TrabajadorBE Read(int idTrabajador) {
        String query = "SELECT * FROM Trabajador WHERE idTrabajador = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idTrabajador);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                TrabajadorBE trabajador = new TrabajadorBE();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setDni(rs.getString("dni"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setApellidoPaterno(rs.getString("apellidoPaterno"));
                trabajador.setApellidoMaterno(rs.getString("apellidoMaterno"));
                trabajador.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate()); // Convertir a LocalDate
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setEmail(rs.getString("email"));
                trabajador.setIdArea(rs.getInt("idArea"));
                trabajador.setIdCargo(rs.getInt("idCargo"));
                return trabajador;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TrabajadorBE> ReadAll() {
        ArrayList<TrabajadorBE> trabajadores = new ArrayList<>();
        String query = "SELECT * FROM Trabajador";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                TrabajadorBE trabajador = new TrabajadorBE();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setDni(rs.getString("dni"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setApellidoPaterno(rs.getString("apellidoPaterno"));
                trabajador.setApellidoMaterno(rs.getString("apellidoMaterno"));
                trabajador.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate()); // Convertir a LocalDate
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setEmail(rs.getString("email"));
                trabajador.setIdArea(rs.getInt("idArea"));
                trabajador.setIdCargo(rs.getInt("idCargo"));
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    @Override
    public boolean Update(TrabajadorBE trabajador) {
        String query = "UPDATE Trabajador SET dni = ?, nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, fechaNacimiento = ?, telefono = ?, email = ?, idArea = ?, idCargo = ? "
                + "WHERE idTrabajador = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, trabajador.getDni());
            pst.setString(2, trabajador.getNombre());
            pst.setString(3, trabajador.getApellidoPaterno());
            pst.setString(4, trabajador.getApellidoMaterno());
            pst.setDate(5, Date.valueOf(trabajador.getFechaNacimiento()));
            pst.setString(6, trabajador.getTelefono());
            pst.setString(7, trabajador.getEmail());
            pst.setInt(8, trabajador.getIdArea());
            pst.setInt(9, trabajador.getIdCargo());
            pst.setInt(10, trabajador.getIdTrabajador());

            return pst.executeUpdate() > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idTrabajador) {
        String query = "DELETE FROM Trabajador WHERE idTrabajador = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idTrabajador);
            return pst.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}