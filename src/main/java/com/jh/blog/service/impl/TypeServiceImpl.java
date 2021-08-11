package com.jh.blog.service.impl;

import com.jh.blog.dao.TypeDao;
import com.jh.blog.pojo.Type;
import com.jh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 增删改需要加事务注解@Transactional
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

//    博客有用到的标签
    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
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
