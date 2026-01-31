package repository.interfaces;

import exception.DatabaseOperationException;
import java.util.List;


public interface ICrudRepository<T> {
    void create(T entity) throws DatabaseOperationException;
    T getById(int id) throws DatabaseOperationException;
    List<T> getAll() throws DatabaseOperationException;
    void update(int id, T entity) throws DatabaseOperationException;
    boolean delete(int id) throws DatabaseOperationException;
}
