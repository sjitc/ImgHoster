package ImageHoster.controller;

import ImageHoster.model.*;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller

public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(Comment newComment,@PathVariable("imageTitle") String title, Model model,@RequestParam("comment") String comment, @PathVariable int imageId,HttpSession session) {

        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");
        newComment.setImage(image);
        newComment.setUser(user);
        newComment.setCreatedDate(new Date());
        newComment.setText(comment);
        model.addAttribute("comments", newComment);
        commentService.createComment(newComment);

        return "redirect:/images/"+imageId+"/"+title;
    }


}
