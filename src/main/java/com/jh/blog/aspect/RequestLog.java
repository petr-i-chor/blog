package com.jh.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jh
 * @create 2021-07-17-20:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestLog {
    private String URL;
    private String ip;
    private String classMethod;
    private Object[] args;
}
