package com.soho.postCardTailor.business.impl;

import com.soho.postCardTailor.business.ISizeBusiness;
import com.soho.postCardTailor.dao.ISizeDAO;
import com.soho.postCardTailor.pojo.graphics.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 尺寸业务逻辑
 */
@Service("sizeBusiness")
public class SizeBusinessImpl implements ISizeBusiness {
    @Autowired
    private ISizeDAO sizeDAO;

    @Override
    public List<Size> getAll() {
        return sizeDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
    public boolean insert(Size size) {
        //当尺寸不存在的时候，执行插入操作
        if (sizeDAO.findBySize(size) == null) {
            sizeDAO.doCreate(size);
        }
        return true;
    }
}
