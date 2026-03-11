/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.registration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author datto
 */
public class RegistrationBLO {
    // chi nen khoi tao 1 lan do load rat lau
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MVC2_DemoPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Registration checkLogin(String username, String password) {
        EntityManager em = emf.createEntityManager();
        Registration result = null;
        
        String jpql = "SELECT r "
                + "FROM Registration r "
                + "WHERE r.username = :u "
                + "AND r.password = :p";
        try {
//          Cach 1: 
            Query query = em.createQuery(jpql);
//          Cach 2: TypedQuery<Registration> query = (TypedQuery<Registration>) em.createQuery(jpql);
//          Cach 3: TypedQuery<Registration> query = em.createQuery(jpql, Registration.class);
//              
//          query.setParameter("u", username);
//          query.setParameter("p", password);
//               
//          result = (Registration) query.getSingleResult();
            
            result = (Registration) em.createQuery(jpql)
                    .setParameter("u", username)
                    .setParameter("p", password)
                    .getSingleResult();
               
        } catch (NoResultException e) {
            result = null;
        } finally {
            em.close();
        }
        
        return result;
    }
    
    public List<Registration> searchLastName(String searchValue) {
        EntityManager em = emf.createEntityManager();
        List<Registration> result = null;
        String jpql = "Registration.findLIKELastname";
        
        try {
            Query query = em.createNamedQuery(jpql);
            query.setParameter("lastname", "%" + searchValue + "%");
            result = (List<Registration>) query.getResultList();
        } catch (PersistenceException e) {
            result = null;
        } finally {
            em.close();
        }
        
        return result;
    }
    
    // fine, remove, merge, persist
    // find(entity/table?, pk)
    // remove(object)
    // merge(object) -> instance/object dc tao ra tu entity/table -> update -> persist pro
    // persist(object) -> instance/object dc tao ra tu entity/table
    
    public boolean deleteAccount(String username) {
        EntityManager em = emf.createEntityManager();
        boolean result = false;
        
        try {
            // CUD -> Transaction
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                em.getTransaction().begin();
                em.remove(tmp);
                em.getTransaction().commit();
                
                result = true;
            }
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return result;
    }
    
    public boolean updateAccount(String username, String password, boolean role) {
        EntityManager em = emf.createEntityManager();
        boolean result = false;
        
        try {
            // CUD -> Transaction
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                em.getTransaction().begin();
                tmp.setPassword(password); // -> dirty checking
                tmp.setIsAdmin(role);
//                em.merge(tmp);
                em.getTransaction().commit();
                
                result = true;
            }
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return result;
    }
    
    public boolean createAccount (Registration r) {
        EntityManager em = emf.createEntityManager();
        boolean result = false;
        
        try {
            Registration tmp = em.find(Registration.class, r.getUsername());
            if (tmp == null) {
                this.persist(r);
                result = true;
            }
        } finally {
            em.close();
        }
        
        return result;
    }
}
