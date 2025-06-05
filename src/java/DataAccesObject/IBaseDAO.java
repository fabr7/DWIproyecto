package DataAccesObject;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    public boolean Create(T input);
    public T Read(int input);
    public ArrayList<T> ReadAll();
    public boolean Update(T input);
    public boolean Delete(int input);
    
    
}
