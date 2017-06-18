package com.mmf.ancientcostume.fragment.test;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.baidu.BaiduFragment;
import com.mmf.ancientcostume.fragment.home.HomeFragment;
import com.mmf.ancientcostume.fragment.home.RecordActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuayan
 */
public class PagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    //灰色以及相对应的RGB值
    private int mGrayColor;
    private int mGrayRed;
    private int mGrayGreen;
    private int mGrayBlue;
    //灰色以及相对应的RGB值
    private int mGreenColor;
    private int mGreenRed;
    private int mGreenGreen;
    private int mGreenBlue;
    private List<Fragment> textViews;//viewpager中适配的 item
    private ImageView[] mBorderimageViews;  //外部的边框
    private ImageView[] mContentImageViews; //内部的内容
    private ImageView[] mWhiteImageViews;  //发现上面的白色部分
    private TextView[] mTitleViews;

    private int tempPosition = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        initColor();
        mViewPager = (ViewPager) findViewById(R.id.pager_view);
        textViews = new ArrayList<>();
        textViews.add(new RecordActivity());
        textViews.add(new BaiduFragment());
        textViews.add(new HomeFragment());
        textViews.add(new RecordActivity());

        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        mBorderimageViews = new ImageView[]{imageView1, imageView2, imageView3, imageView4};

        ImageView topImageView1 = (ImageView) findViewById(R.id.image1_top);
        ImageView topImageView2 = (ImageView) findViewById(R.id.image2_top);
        ImageView topImageView3 = (ImageView) findViewById(R.id.image3_top);
        ImageView topImageView4 = (ImageView) findViewById(R.id.image4_top);
        mContentImageViews = new ImageView[]{topImageView1, topImageView2, topImageView3, topImageView4};


        ImageView whiteImageView1 = (ImageView) findViewById(R.id.image1_white);
        ImageView whiteImageView2 = (ImageView) findViewById(R.id.image2_white);
        ImageView whiteImageView3 = (ImageView) findViewById(R.id.image3_white);
        ImageView whiteImageView4 = (ImageView) findViewById(R.id.image3_white);
        mWhiteImageViews = new ImageView[]{whiteImageView1, whiteImageView2, whiteImageView3, whiteImageView4};


        TextView titileView1 = (TextView) findViewById(R.id.text1);
        TextView titileView2 = (TextView) findViewById(R.id.text2);
        TextView titileView3 = (TextView) findViewById(R.id.text3);
        TextView titileView4 = (TextView) findViewById(R.id.text4);
        mTitleViews = new TextView[]{titileView1, titileView2, titileView3, titileView4};

        LinearLayout lyt_2 = (LinearLayout) findViewById(R.id.lyt_2);

//        ItemPagerAdapter adapter = new ItemPagerAdapter(textViews);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        setSelection(0);
        lyt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setSelection(1);
                mViewPager.setCurrentItem(2);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("******position--" + position + "   ***positionOffset***" + positionOffset + "   **positionOffsetPixels**" + positionOffsetPixels);
                if (positionOffset > 0) {
                    if (positionOffset < 0.5) {
                        //  滑动到一半前，上一页的边框保持绿色不变，下一页的边框由灰色变为绿色
                        mBorderimageViews[position].setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
                        mBorderimageViews[position + 1].setColorFilter(getGrayToGreen(positionOffset), PorterDuff.Mode.SRC_IN);
                        //   上一页的内容保持由实心变为透明，下一页的内容保持透明
                        mContentImageViews[position].setAlpha((1 - 2 * positionOffset));
                        mContentImageViews[position + 1].setAlpha(0f);
                        //文字颜色变化
                        mTitleViews[position].setTextColor(mGreenColor);
                        mTitleViews[position + 1].setTextColor(getGrayToGreen(positionOffset));

                    } else {
                        //滑动到一半后，上一页的边框由lvse变为灰色，，下一页边框保持绿色不变
                        mBorderimageViews[position].setColorFilter(getGreenToGray(positionOffset), PorterDuff.Mode.SRC_IN);
                        mBorderimageViews[position + 1].setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
                        //上一页的内容保持透明，下一页的内容由透明变为实心绿色
                        mContentImageViews[position].setAlpha(0f);
                        mContentImageViews[position + 1].setAlpha(2 * positionOffset - 1);
                        mTitleViews[position].setTextColor(getGreenToGray(positionOffset));
                        mTitleViews[position + 1].setTextColor(mGreenColor);
                        if (positionOffset > 0.8) {
                            mWhiteImageViews[position + 1].setVisibility(View.VISIBLE);
                            mWhiteImageViews[position + 1].setAlpha(10 * positionOffset - 8);
                        } else {
                            mWhiteImageViews[position + 1].setVisibility(View.GONE);
                        }
                    }
                } else {
                    setSelection(position);
                }

            }

            @Override
            public void onPageSelected(int position) {
                setSelection(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * 设置索引  当前导航页边框绿色，内容实心绿，其他页边框灰色，内容透明
     *
     * @param position
     */
    private void setSelection(int position) {
        System.out.println("setSelection(int position) " + position);
        for (int i = 0; i < mBorderimageViews.length; i++) {

//            if(position != tempPosition){
//                tempPosition = position;
//                setSelection(position);
//            }else{
            if (i == position) {
                mBorderimageViews[i].setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
                mContentImageViews[i].setAlpha(1f);
                mWhiteImageViews[i].setVisibility(View.VISIBLE);
                mTitleViews[i].setTextColor(mGreenColor);
            } else {
                mBorderimageViews[i].setColorFilter(mGrayColor, PorterDuff.Mode.SRC_IN);
                mContentImageViews[i].setAlpha(0f);
                mWhiteImageViews[i].setVisibility(View.GONE);
                mTitleViews[i].setTextColor(mGrayColor);
            }
        }
    }


    private void initColor() {
        mGrayColor = getResources().getColor(R.color.gray);
        mGrayRed = Color.red(mGrayColor);
        mGrayGreen = Color.green(mGrayColor);
        mGrayBlue = Color.blue(mGrayColor);
        mGreenColor = getResources().getColor(R.color.colorAccent);
        mGreenRed = Color.red(mGreenColor);
        mGreenGreen = Color.green(mGreenColor);
        mGreenBlue = Color.blue(mGreenColor);
    }

    /**
     * 偏移量在 0——0.5区间 ，左边一项颜色不变，右边一项颜色从灰色变为绿色，根据两点式算出变化函数
     *
     * @param positionOffset
     * @return
     */
    private int getGrayToGreen(float positionOffset) {
        int red = (int) (positionOffset * (mGreenRed - mGrayRed) * 2 + mGrayRed);
        int green = (int) (positionOffset * (mGreenGreen - mGrayGreen) * 2 + mGrayGreen);
        int blue = (int) ((positionOffset) * (mGreenBlue - mGrayBlue) * 2 + mGrayBlue);
        Log.d("why ", "#### " + red + "  " + green + "  " + blue);
        return Color.argb(255, red, green, blue);
    }

    /**
     * 偏移量在 0.5--1 区间，颜色从绿色变成灰色，根据两点式算出变化函数
     *
     * @param positionOffset
     * @return
     */
    private int getGreenToGray(float positionOffset) {
        int red = (int) (positionOffset * (mGrayRed - mGreenRed) * 2 + 2 * mGreenRed - mGrayRed);
        int green = (int) (positionOffset * (mGrayGreen - mGreenGreen) * 2 + 2 * mGreenGreen - mGrayGreen);
        int blue = (int) (positionOffset * (mGrayBlue - mGreenBlue) * 2 + 2 * mGreenBlue - mGrayBlue);
        Log.d("why ", "#### " + red + "  " + green + "  " + blue);
        return Color.argb(255, red, green, blue);
    }


    /**
     * viewpager适配器
     */
    class ItemPagerAdapter extends PagerAdapter {
        List<TextView> list;

        public ItemPagerAdapter(List<TextView> views) {
            list = views;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            switch (position) {
                case 0:
                    list.get(position).setText("晴川历历汉阳树");
                    break;
                case 1:
                    list.get(position).setText("芳草萋萋鹦鹉洲");
                    break;
                case 2:
                    list.get(position).setText("长烟落日孤城闭");
                    break;
            }
            list.get(position).setGravity(Gravity.CENTER);

            container.addView(list.get(position), 0);
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"分类", "主页", "热门推荐", "热门收藏", "本月热榜", "今日热榜", "专栏", "随机"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            return textViews.get(position);
        }

    }
}
