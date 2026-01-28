package root.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.ErrorMessage;
import root.constant.QuerryMessage;
import root.dao.UserDao;
import root.exception.DatabaseException;
import root.exception.UserNotFoundException;
import root.model.entity.OtpCode;
import root.model.entity.User;
import root.util.HibernateUtil;
import root.util.PasswordUtil;

import java.time.LocalDateTime;

public class UserDaoImpl implements UserDao {
    // Đăng nhập
    public boolean login(User user){
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        try {
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
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        Transaction transaction = null;
        try {
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
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        try {
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
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        try {
            Long count = session
                    .createQuery(QuerryMessage.USER_CHECK_EMAIL, Long.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
        }
    }

    public boolean resetPass(String email, String newPass){
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        Transaction transaction = null;
        try {
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
    // Luu OTP
    public void saveOtp(OtpCode otpCode){
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            LocalDateTime now = LocalDateTime.now();
            //Xóa otp hêt hạn
            session.createQuery(QuerryMessage.DELETE_EXPIRED_OTP)
                    .setParameter("now",now)
                    .executeUpdate();

            // Xóa email cũ của email này
            session.createQuery(QuerryMessage.DELETE_OTP_BY_EMAIL)
                    .setParameter("email", otpCode.getEmail())
                    .executeUpdate();


            session.persist(otpCode);
            transaction.commit();

        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    // Tìm OTP hop lệ
    @Override
    public OtpCode findValidOtp(String email,String otp){
        Session session = HibernateUtil.buildingSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            LocalDateTime now = LocalDateTime.now();

            OtpCode otpCode = session.createQuery(QuerryMessage.OTP_VALID, OtpCode.class)
                    .setParameter("email",email)
                    .setParameter("otp",otp)
                    .setParameter("now",now)
                    .uniqueResult();

            if(otpCode != null){
                session.remove(otpCode);
            }

            transaction.commit();
            return otpCode;
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseException(ErrorMessage.DATABASE_CONNECTION_ERROR,e);
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
