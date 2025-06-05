package BusinessLogic;

import BusinessEntity.TrabajadorBE;
import DataAccesObject.TrabajadorDAO;
import java.util.ArrayList;

public class TrabajadorBL implements IBaseBL<TrabajadorBE> {

    @Override
    public boolean Create(TrabajadorBE input) {
        TrabajadorDAO DAO = new TrabajadorDAO();
        return DAO.Create(input);
    }

    @Override
    public TrabajadorBE Read(String input) {
        TrabajadorDAO DAO = new TrabajadorDAO();
        try {
            int idTrabajador = Integer.parseInt(input);
            return DAO.Read(idTrabajador);
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID del trabajador debe ser un número entero.");
            return null;
        }
    }

    @Override
    public ArrayList<TrabajadorBE> ReadAll() {
        TrabajadorDAO DAO = new TrabajadorDAO();
        return DAO.ReadAll();
    }

    @Override
    public boolean Update(TrabajadorBE input) {
        TrabajadorDAO DAO = new TrabajadorDAO();
        return DAO.Update(input);
    }

    @Override
    public boolean Delete(String input) {
        TrabajadorDAO DAO = new TrabajadorDAO();
        try {
            int idTrabajador = Integer.parseInt(input);
            return DAO.Delete(idTrabajador);
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID del trabajador debe ser un número entero.");
            return false;
        }
    }

}
