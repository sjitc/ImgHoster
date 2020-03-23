package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;



    //Executes JPQL query to fetch all the comments for given Image Id
    //Returns the list of all the images fetched from the database

    public List<Comment> getComments(int imageId) {

        EntityManager em = emf.createEntityManager();


        try {
            TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i where i.image.id=:imageId", Comment.class);
            List<Comment> resultList = query.getResultList();
            return resultList;
        } catch (NoResultException nre) {
            return null;
        }


    }
    public void createComment(Comment newComment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

    }


}

