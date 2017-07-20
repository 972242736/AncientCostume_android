package com.mmf.ancientcostume.widget;

/**
 * Created by MMF on 2017-07-20.
 * 图片预览界面操作接口
 */

public interface IImagePreview {
    /**
     * 删除选择接图片
     * @param position
     */
    void delete(int position,String path);

    /**
     * 选择图片
     * @param position
     */
    void select(int position,String path);
}
