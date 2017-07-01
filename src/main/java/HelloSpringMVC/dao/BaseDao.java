package HelloSpringMVC.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {

     void save(T entity);

     void update(T entity);

     void delete(Serializable id);

     T findObjectById(Serializable id);

     List<T> findObjects();
}
