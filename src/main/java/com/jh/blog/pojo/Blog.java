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
public class Blog {
//    主键id
    private Long id;
//    博客标题
    private String title;
//    博客内容
    private String content;
//    博客描述
    private String description;
//    博客图片
    private String firstPicture;
//    标记
    private String flag;
//    浏览次数
    private Integer views;
//    赞赏是否开启
    private boolean appreciation;
//    版权是否开启
    private boolean shareStatement;
//    评论是否开启
    private boolean commentabled;
//     是否发布
    private boolean published;
//     是否推荐
    private boolean recommend;
//     发布时间
    private Date createTime;
//     更新时间
    private Date updateTime;


    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;
    private String tagIds;

    //    Blog多对一
    private Type type;
    private User user;
    //    Blog多对多
    private List<Tag> tags = new ArrayList<>();
    //    Blog一对多
    private List<Comment> comments = new ArrayList<>();



    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
