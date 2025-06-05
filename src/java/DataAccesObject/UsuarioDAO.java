package DataAccesObject;

import BusinessEntity.UsuarioBE;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO extends ConexionMySQL implements IBaseDAO<UsuarioBE> {

    @Override
    public boolean Create(UsuarioBE input) {
        String query = "INSERT INTO Usuario (username, password, rol, idTrabajador) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getUsername());
            pst.setString(2, input.getPassword());
            pst.setString(3, input.getRol());
            pst.setInt(4, input.getIdTrabajador());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UsuarioBE Read(int idUsuario) {
        String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                UsuarioBE usuario = new UsuarioBE();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                usuario.setIdTrabajador(rs.getInt("idTrabajador"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<UsuarioBE> ReadAll() {
        ArrayList<UsuarioBE> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UsuarioBE usuario = new UsuarioBE();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                usuario.setIdTrabajador(rs.getInt("idTrabajador"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean Update(UsuarioBE input) {
        String query = "UPDATE Usuario SET username = ?, password = ?, rol = ?, idTrabajador = ? WHERE idUsuario = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getUsername());
            pst.setString(2, input.getPassword());
            pst.setString(3, input.getRol());
            pst.setInt(4, input.getIdTrabajador());
            pst.setInt(5, input.getIdUsuario());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idUsuario) {
        String query = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idUsuario);
            return pst.executeUpdate() > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
