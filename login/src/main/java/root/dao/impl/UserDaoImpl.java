package root.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.ErrorMessage;
import root.constant.QuerryMessage;
import root.dao.UserDao;
import root.exception.DatabaseException;
import root.exception.UserNotFoundException;
import root.model.entity.User;
import root.util.HibernateUtil;
import root.util.PasswordUtil;

public class UserDaoImpl implements UserDao {
    // Đăng nhập
    public boolean login(User user){
        try (Session session = HibernateUtil.buildingSessionFactory().openSession()) {
            User dbUser = session.createQuery(QuerryMessage.USER_LOGIN, User.class)
                    .setParameter("un", user.getUsername())
                    .uniqueResult();

            if(dbUser == null){
                return false;
            }
            // So sách mật khẩu mã khóa
            return PasswordUtil.checkPassword(user.getPassword(),dbUser.getPassword());
        }catch (Exception e){
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
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
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
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
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
        }
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
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
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

            if(rowsAffected == 0){
                throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND_ERROR);
            }
            return true;
        }catch (UserNotFoundException e){
            throw e;
        }catch (Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,ex);
        }
    }
}
