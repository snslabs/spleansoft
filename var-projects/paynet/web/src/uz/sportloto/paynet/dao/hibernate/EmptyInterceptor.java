package uz.sportloto.paynet.dao.hibernate;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

abstract public class EmptyInterceptor implements Interceptor {
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        return false;
    }

    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        return false;
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        return false;
    }

    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
    }

    public void preFlush(Iterator entities) throws CallbackException {
    }

    public void postFlush(Iterator entities) throws CallbackException {
    }

    public Boolean isUnsaved(Object entity) {
        return null;
    }

    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object instantiate(Class clazz, Serializable id) throws CallbackException {
        return null;
    }
}