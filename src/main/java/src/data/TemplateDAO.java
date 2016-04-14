package src.data;

import src.util.Resources;

import javax.persistence.EntityManager;

/**
 * Created by root on 31.3.16.
 */
public class TemplateDAO<T> {

    public TemplateDAO(Resources res){
        this.res = res;
        em = res.getEntityManager();
    }

    private Resources res;

    protected EntityManager em;

    public void create(T t){
        
    }

    public T update(T t){
        return t;
    }

    public void remove(T t){

    }

    public T getById(int id){
        return null;
    }

}
