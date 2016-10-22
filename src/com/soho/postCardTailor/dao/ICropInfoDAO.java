package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.postCard.CropInfo;

/**
 * Created by HackerOrange on 2016/10/7.
 */
public interface ICropInfoDAO {
    /**
     * 创建新的裁切信息
     *
     * @param cropInfo 新的裁切信息
     * @return 是否创建成功
     */
    boolean doCreate(CropInfo cropInfo);

    /**
     * 更新裁切信息
     *
     * @param cropInfo 更新后的裁切信息
     * @return 是否更新成功
     */
    boolean doUpdate(CropInfo cropInfo);

    /**
     * 根据明信片ID查找裁切信息
     *
     * @param id 明信片ID
     * @return 裁切信息
     */
    CropInfo findByPostCardId(Integer id);
}
