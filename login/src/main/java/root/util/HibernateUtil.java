package root.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import root.model.entity.OtpCode;
import root.model.entity.User;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory buildingSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");

                // 🔥 BẮT BUỘC add entity
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(OtpCode.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
