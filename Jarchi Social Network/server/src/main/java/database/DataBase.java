package database;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataBase<T> {
    T Load(int id) throws IOException;
    void update(T t) throws IOException;
    void delete(int id);
    int getLastId() throws FileNotFoundException;
}
