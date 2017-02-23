/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.dsl;

/**
 *
 * @author DAWUZI
 */
import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static Session getSession()
    {
        return sessionFactory.openSession(); 
    }

    static {
        try {
            File f = new File("BiocaptureConfig.xml");
            System.out.println("file exists : "+f.exists()+", path : "+f.getAbsolutePath());
            Configuration configuration = new Configuration().configure(f);
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException(ex);
        }
    }
}