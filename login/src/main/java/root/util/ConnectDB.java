package root.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConnectDB {
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public Session session = null;

    public Session open() {
        if (session == null) {
            session =  sessionFactory.openSession();
        }
        return session;
    }

    public void closing() {
        if (session != null) {
            session.close();
        }
    }
}
