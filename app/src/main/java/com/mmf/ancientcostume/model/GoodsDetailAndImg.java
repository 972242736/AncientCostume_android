package com.mmf.ancientcostume.model;

import java.util.List;

/**
 * Created by MMF on 2017-08-03.
 */

public class GoodsDetailAndImg {
    private List<GoodsImg> goodsTopImgList;
    private List<GoodsImg> goodsDetailImgList;
    private GoodsDetail goodsDetail;

    public List<GoodsImg> getGoodsTopImgList() {
        return goodsTopImgList;
    }

    public void setGoodsTopImgList(List<GoodsImg> goodsTopImgList) {
        this.goodsTopImgList = goodsTopImgList;
    }

    public List<GoodsImg> getGoodsDetailImgList() {
        return goodsDetailImgList;
    }

    public void setGoodsDetailImgList(List<GoodsImg> goodsDetailImgList) {
        this.goodsDetailImgList = goodsDetailImgList;
    }

    public GoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(GoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
