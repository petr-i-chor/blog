package com.jh.blog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;
    private String name;

    //    Blog对Type多对一
    private List<Blog> blogs = new ArrayList<>();
}
