package com.jh.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    //是否为管理员评论
    private boolean adminComment;
    //头像
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;  //父评论id
    private String parentNickname;


    /**
     * 自关联
     *
     */
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;



    //    Blog对comment一对多
    private Blog blog;

}
