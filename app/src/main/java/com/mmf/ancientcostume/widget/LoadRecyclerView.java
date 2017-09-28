package com.mmf.ancientcostume.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

/**
 * Created by MMF on 2017-07-19.
 * 需要加载时使用这个控件
 */

public class LoadRecyclerView extends RecyclerView  {

    private LoadingDataListener loadingDataListener;
    public LoadRecyclerView(Context context) {
        this(context, null);
        addOnScrollListener(new AutoLoadScrollListener());
    }

    public LoadRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        addOnScrollListener(new AutoLoadScrollListener());
    }

    public LoadRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(new AutoLoadScrollListener());
    }
    private class AutoLoadScrollListener extends OnScrollListener {
        boolean isSlidingToLast = false;
        private final int LINEAR_LAYOUT = 1;
        private final int GRID_LAYOUT = 2;
        private final int STAGGERED_GRID_LAYOUT = 3;
        private int layoutManagerType = LINEAR_LAYOUT;

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
                    loadingDataListener.onLoadMore();//当滑动到底部的时候触动这个方法
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) {
                isSlidingToLast = false;
            } else {
                isSlidingToLast = true;
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
    }

    public interface LoadingDataListener {
        void onLoadMore();
    }

    public void setLoadingDataListener(LoadingDataListener loadingDataListener) {
        this.loadingDataListener = loadingDataListener;
    }
}