package root.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.UserDao;
import root.model.entity.User;
import root.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
    // Đăng nhập
    public boolean login(User user){
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()) {
            return session
                    .createQuery(QuerryMessage.USER_LOGIN, User.class)
                    .setParameter("un", user.getUsername())
                    .setParameter("pw", user.getPassword())
                    .uniqueResult() != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // Đăng ký
    public boolean register(User user){
        Transaction transaction = null;
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();
            return true;
        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra tồn tại
    public boolean exists(User user){
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()) {
            Long count = session
                    .createQuery(QuerryMessage.USER_ISEXISTS, Long.class)
                    .setParameter("un", user.getUsername())
                    .setParameter("email", user.getEmail())
                    .uniqueResult();

            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tạo lại mật khẩu
    public boolean isEmailExists(String email) {
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()){
            Integer count = session
                    .createQuery(QuerryMessage.USER_CHECK_EMAIL, Integer.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resetPass(String email, String newPass){
        Transaction transaction = null;
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery(QuerryMessage.USER_PASSWORD_UPDATE);
            query.setParameter("pw", newPass);
            query.setParameter("email", email);

            int rowsAffected = query.executeUpdate();

            transaction.commit();

            return rowsAffected > 0;
        }catch (Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return false;
        }

    }
}
