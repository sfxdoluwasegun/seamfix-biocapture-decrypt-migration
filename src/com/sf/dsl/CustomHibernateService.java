/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.dsl;

/**
 *
 * @author DAWUZI
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomHibernateService {

    private static final Logger log = Logger.getLogger(CustomHibernateService.class);

    public static Object getObjectByHql(String query) {
        return getObjectByHql(query, null);
    }

    public static Object getObjectByHql(String query, String name, Object value) {
        Map paramsMap = new HashMap();
        paramsMap.put(name, value);
        return getObjectByHql(query, paramsMap);
    }

    public static Object getObjectByHql(String query, Map<String, Object> paramsMap) {
        return getObjectByHql(query, paramsMap, null, null);
    }

    public static Object getObjectByHql(String query, Map<String, Object> paramsMap, Integer firstResult, Integer maxResults) {
        return getObjectOrListQuery(query, paramsMap, firstResult, maxResults, true);
    }

    public static List<?> getListByHql(String query) {
        return getListByHql(query, null);
    }

    public static List<?> getListByHql(String query, String name, Object value) {
        Map paramsMap = new HashMap();
        paramsMap.put(name, value);
        return getListByHql(query, paramsMap);
    }

    public static List<?> getListByHql(String query, Map<String, Object> paramsMap) {
        return getListByHql(query, paramsMap, null, null);
    }

    public static List<?> getListByHql(String query, Map<String, Object> paramsMap, Integer firstResult, Integer maxResults) {
        return (List) getObjectOrListQuery(query, paramsMap, firstResult, maxResults, false);
    }

    private static Object getObjectOrListQuery(String query, Map<String, Object> paramsMap, Integer firstResult, Integer maxResults, boolean object) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();
            Query q = session.createQuery(query);
            if (paramsMap != null) {
                for (String key : paramsMap.keySet()) {
                    q.setParameter(key, paramsMap.get(key));
                }
            }

            if (firstResult != null) {
                q.setFirstResult(firstResult.intValue());
            }

            if (maxResults != null) {
                q.setMaxResults(maxResults.intValue());
            }

            tx = session.beginTransaction();
            Object avariable;

            if (object) {
                avariable = q.uniqueResult();
            } else {
                avariable = q.list();
            }

            tx.commit();

            return avariable;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException he) {
                    log.error(he.getMessage(), he);
                }
            }
        }
    }

//    public static void updateRecord(Object existingRecord) {
//        getSession().update(existingRecord);
//    }

    public static void updateRecord(Object record) {

        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(record);
            tx.commit();
        } catch (Throwable th) {
            throw new RuntimeException(th.getMessage(), th);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
    }

    public static Session getSession() {
        return HibernateUtils.getSession();
    }
}
