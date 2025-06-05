package BusinessLogic;

import BusinessEntity.UsuarioBE;
import DataAccesObject.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioBL implements IBaseBL<UsuarioBE> {

    @Override
    public boolean Create(UsuarioBE input) {
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.Create(input);
    }

    @Override
    public UsuarioBE Read(String input) {
        UsuarioDAO DAO = new UsuarioDAO();
        try {
            int idUsuario = Integer.parseInt(input);
            return DAO.Read(idUsuario);
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID del usuario debe ser un número entero.");
            return null;
        }
    }

    @Override
    public ArrayList<UsuarioBE> ReadAll() {
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.ReadAll();
    }

    @Override
    public boolean Update(UsuarioBE input) {
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.Update(input);
    }

    @Override
    public boolean Delete(String input) {
        UsuarioDAO DAO = new UsuarioDAO();  
        try {
            int idUsuario = Integer.parseInt(input);  
            return DAO.Delete(idUsuario);  
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID del usuario debe ser un número entero.");
            return false;
        }
    }

}
