package com.mmf.ancientcostume.model;

import java.util.List;

/**
 * Created by MMF on 2017-08-03.
 */

public class GoodsDetailAndImg {
    private List<GoodsImg> goodsImgList;
    private GoodsDetail goodsDetail;

    public List<GoodsImg> getGoodsImgList() {
        return goodsImgList;
    }

    public void setGoodsImgList(List<GoodsImg> goodsImgList) {
        this.goodsImgList = goodsImgList;
    }

    public GoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(GoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
