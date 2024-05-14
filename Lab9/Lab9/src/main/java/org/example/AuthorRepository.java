package org.example;
package org.example.repository;

import org.example.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorRepository {
    private EntityManagerFactory entityManagerFactory;

    public AuthorRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void createAuthor(Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Author findById(int authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Author.class, authorId);
        } finally {
            entityManager.close();
        }
    }

    public List<Author> findByName(String namePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createNamedQuery("Author.findByName", Author.class);
            query.setParameter("namePattern", "%" + namePattern + "%");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
