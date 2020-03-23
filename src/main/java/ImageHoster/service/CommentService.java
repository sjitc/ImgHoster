package ImageHoster.service;
import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //The method calls the createComment() method in the Repository and passes the image to be persisted in the database
    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }

    public List<Comment> getComments(int imageId)
    {
        List<Comment> comments=commentRepository.getComments(imageId);
        return comments;
    }

}
