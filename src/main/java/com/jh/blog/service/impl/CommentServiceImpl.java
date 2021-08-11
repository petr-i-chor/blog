package com.jh.blog.service.impl;

import com.jh.blog.dao.BlogDao;
import com.jh.blog.dao.CommentDao;
import com.jh.blog.pojo.Comment;
import com.jh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {

        //查询父评论
        //父节点的默认为null
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId);
        //查询出父节点
        for(Comment comment : comments){
            Long id = comment.getId();
            List<Comment> childComments = commentDao.findReplyComments(id);
//            查询出子评论
            combineChildren(childComments);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        getCommentt(comments);
        return comments;
    }

    private void getCommentt(List<Comment> list) {
        if (list == null) {
            return;
        }
        for (Comment comment : list) {
            System.out.println(comment.toString());
            List<Comment> replyComments = comment.getReplyComments();
            getCommentt(replyComments);
        }

    }



    @Transactional
    @Override
    //接收回复的表单
    public int saveComment(Comment comment) {
        //获得父id
        Long parentCommentId = comment.getParentComment().getId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
            comment.setParentCommentId(parentCommentId);
        } else {
            //没有父级评论
            comment.setParentComment(null);
            comment.setParentCommentId(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }


    private void combineChildren(List<Comment> childComments) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(childId);
            }
        }
    }



    private void recursively(Long childId) {
        List<Comment> replayComments = commentDao.findReplyComments(childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(replayId);
            }
        }
    }

}
