package com.mmf.ancientcostume.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by MMF on 2017-09-26.
 */

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private LoadingDataListener listener;
    boolean isSlidingToLast = false;
    private final int LINEAR_LAYOUT = 1;
    private final int GRID_LAYOUT = 2;
    private final int STAGGERED_GRID_LAYOUT = 3;
    private int layoutManagerType = LINEAR_LAYOUT;

    public RecyclerViewScrollListener(LoadingDataListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LINEAR_LAYOUT;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = GRID_LAYOUT;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = STAGGERED_GRID_LAYOUT;
            } else {
                throw new RuntimeException("不支持的layoutManager");
            }
            int lastVisibleItemPosition = 0;
            switch (layoutManagerType) {
                case LINEAR_LAYOUT:
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case GRID_LAYOUT:
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case STAGGERED_GRID_LAYOUT:
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItemPosition = findMax(lastPositions);
                    break;
                default:
                    break;
            }

            int totalItemCount = layoutManager.getItemCount();
            if (lastVisibleItemPosition == (totalItemCount - 1) && !isSlidingToLast) {
                listener.onLoadMore();//当滑动到底部的时候触动这个方法
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dx > 0) {

            isSlidingToLast = true;

        } else {

            isSlidingToLast = false;

        }

    }
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface LoadingDataListener {
        void onLoadMore();
    }
}
