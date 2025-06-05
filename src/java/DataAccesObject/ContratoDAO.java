package DataAccesObject;

import BusinessEntity.ContratoBE;
import java.sql.*;
import java.util.ArrayList;

public class ContratoDAO extends ConexionMySQL implements IBaseDAO<ContratoBE> {

    @Override
    public boolean Create(ContratoBE input) {
        String query = "INSERT INTO Contrato (tipo, fechaInicio, fechaFin, idTrabajador) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getTipo());
            pst.setDate(2, Date.valueOf(input.getFechaInicio()));
            pst.setDate(3, input.getFechaFin() != null ? Date.valueOf(input.getFechaFin()) : null);
            pst.setInt(4, input.getIdTrabajador());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ContratoBE Read(int idContrato) {
        String query = "SELECT * FROM Contrato WHERE idContrato = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idContrato);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ContratoBE contrato = new ContratoBE();
                contrato.setIdContrato(rs.getInt("idContrato"));
                contrato.setTipo(rs.getString("tipo"));
                contrato.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                contrato.setFechaFin(rs.getDate("fechaFin") != null ? rs.getDate("fechaFin").toLocalDate() : null);
                contrato.setIdTrabajador(rs.getInt("idTrabajador"));
                return contrato;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ContratoBE> ReadAll() {
        ArrayList<ContratoBE> contratos = new ArrayList<>();
        String query = "SELECT * FROM Contrato";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ContratoBE contrato = new ContratoBE();
                contrato.setIdContrato(rs.getInt("idContrato"));
                contrato.setTipo(rs.getString("tipo"));
                contrato.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                contrato.setFechaFin(rs.getDate("fechaFin") != null ? rs.getDate("fechaFin").toLocalDate() : null); // Convertir a LocalDate
                contrato.setIdTrabajador(rs.getInt("idTrabajador"));
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    @Override
    public boolean Update(ContratoBE input) {
        String query = "UPDATE Contrato SET tipo = ?, fechaInicio = ?, fechaFin = ?, idTrabajador = ? WHERE idContrato = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, input.getTipo());
            pst.setDate(2, Date.valueOf(input.getFechaInicio()));
            pst.setDate(3, input.getFechaFin() != null ? Date.valueOf(input.getFechaFin()) : null);
            pst.setInt(4, input.getIdTrabajador());
            pst.setInt(5, input.getIdContrato());

            return pst.executeUpdate() > 0;  // Si la actualización es exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idContrato) {
        String query = "DELETE FROM Contrato WHERE idContrato = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idContrato);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
