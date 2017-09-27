package com.mmf.ancientcostume.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MMF on 2017-09-27.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;

    public GridSpacingItemDecoration( int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=spacing;
        outRect.right=spacing;
        outRect.bottom=spacing;
        //注释这两行是为了上下间距相同
//        if(parent.getChildAdapterPosition(view)==0){
        outRect.top=spacing;
    }
}