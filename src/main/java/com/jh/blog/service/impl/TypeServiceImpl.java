package com.jh.blog.service.impl;

import com.jh.blog.dao.TypeDao;
import com.jh.blog.pojo.Type;
import com.jh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 增删改需要加事务注解@Transactional
     *
     * @param type
     * @return
     */

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }


    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }


    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }


    //    所有标签
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    //    博客主页展示标签(标签使用次数从高到低排序)
    @Override
    public List<Type> getBlogType() {
        List<Type> blogType = typeDao.getBlogType();

        blogType.sort(new Comparator<Type>() {
            @Override
            public int compare(Type t1, Type t2) {
                int s1 = t1.getBlogs().size();
                int s2 = t2.getBlogs().size();
                if (s1 > s2) {
                    return -1;
                } else if (s1 < s2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return blogType;

    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
