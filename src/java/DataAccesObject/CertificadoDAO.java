package DataAccesObject;

import BusinessEntity.CertificadoBE;
import java.sql.*;
import java.util.ArrayList;

public class CertificadoDAO extends ConexionMySQL implements IBaseDAO<CertificadoBE> {

    @Override
    public boolean Create(CertificadoBE input) {
        String query = "INSERT INTO Certificado (idTrabajador, fechaEmision, motivo, codigo) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, input.getIdTrabajador());
            pst.setDate(2, Date.valueOf(input.getFechaEmision()));
            pst.setString(3, input.getMotivo());
            pst.setString(4, input.getCodigo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CertificadoBE Read(int idCertificado) {
        String query = "SELECT * FROM Certificado WHERE idCertificado = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idCertificado);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                CertificadoBE certificado = new CertificadoBE();
                certificado.setIdCertificado(rs.getInt("idCertificado"));
                certificado.setIdTrabajador(rs.getInt("idTrabajador"));
                certificado.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());  // Convertir a LocalDate
                certificado.setMotivo(rs.getString("motivo"));
                certificado.setCodigo(rs.getString("codigo"));
                return certificado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CertificadoBE> ReadAll() {
        ArrayList<CertificadoBE> certificados = new ArrayList<>();
        String query = "SELECT * FROM Certificado";
        try (Connection conn = getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CertificadoBE certificado = new CertificadoBE();
                certificado.setIdCertificado(rs.getInt("idCertificado"));
                certificado.setIdTrabajador(rs.getInt("idTrabajador"));
                certificado.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());
                certificado.setMotivo(rs.getString("motivo"));
                certificado.setCodigo(rs.getString("codigo"));
                certificados.add(certificado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certificados;
    }

    @Override
    public boolean Update(CertificadoBE input) {
        String query = "UPDATE Certificado SET idTrabajador = ?, fechaEmision = ?, motivo = ?, codigo = ? WHERE idCertificado = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, input.getIdTrabajador());
            pst.setDate(2, Date.valueOf(input.getFechaEmision()));
            pst.setString(3, input.getMotivo());
            pst.setString(4, input.getCodigo());
            pst.setInt(5, input.getIdCertificado());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Delete(int idCertificado) {
        String query = "DELETE FROM Certificado WHERE idCertificado = ?";
        try (Connection conn = getConexion(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idCertificado);
            return pst.executeUpdate() > 0;  // Si la eliminación es exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
