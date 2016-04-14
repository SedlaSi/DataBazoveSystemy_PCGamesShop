package data;

/**
 * Created by root on 31.3.16.
 */
public class TemplateDAO<T> {

    public void create(T t){
        
    }

    public T update(T t){
        return t;
    }

    public void remove(T t){

    }

    public T getById(int id){
        return new T();
    }

}
