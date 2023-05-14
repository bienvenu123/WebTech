package com.example.bibafrica.dao;

import com.example.bibafrica.model.Signup;
import org.hibernate.Session;

public class userAuthentication {
    public boolean loginUser(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Signup so WHERE so.email = :email AND so.password = :password", Signup.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultStream()
                    .findFirst()
                    .isPresent();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
