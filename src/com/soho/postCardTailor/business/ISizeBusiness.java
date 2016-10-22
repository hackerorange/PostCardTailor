package com.soho.postCardTailor.business;

import com.soho.postCardTailor.pojo.graphics.Size;

import java.util.List;

/**
 * 尺寸逻辑层
 */
public interface ISizeBusiness {
    /**
     * 获取所有尺寸
     *
     * @return 所有尺寸
     */
    List<Size> getAll();

    /**
     * 插入新的尺寸
     *
     * @param size 要插入的尺寸
     * @return 是否插入成功
     */
    boolean insert(Size size);
}
