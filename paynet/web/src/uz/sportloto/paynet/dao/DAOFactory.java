package uz.sportloto.paynet.dao;

import uz.sportloto.paynet.dao.hibernate.ProvidersHibernateDAOImpl;
import uz.sportloto.paynet.dao.hibernate.PaymentHibernateDAOImpl;
import uz.sportloto.paynet.dao.hibernate.HibernateSecurityDAOImpl;

public class DAOFactory {
    public static final int HIBERNATE = 1;
    public static final int JDBC = 3;

    private static int mode = HIBERNATE;
    public static ProvidersDAO getProvidersDAO(){
        switch(mode){
            case HIBERNATE: return new ProvidersHibernateDAOImpl(); 
        }
        throw new IllegalArgumentException("DAO type "+mode+" not implemented yet!");
    }
    public static PaymentDAO getPaymentDAO(){
        switch(mode){
            case HIBERNATE: return new PaymentHibernateDAOImpl();
        }
        throw new IllegalArgumentException("DAO type "+mode+" not implemented yet!");
    }

    public static SecurityDAO getSecurityDAO() {
        switch(mode){
            case HIBERNATE: return new HibernateSecurityDAOImpl();
        }
        throw new IllegalArgumentException("DAO type "+mode+" not implemented yet!");
    }
}
