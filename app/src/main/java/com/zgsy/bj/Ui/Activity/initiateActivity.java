package com.zgsy.bj.Ui.Activity;

import android.app.Activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;

import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import android.support.v4.view.ViewPager.OnPageChangeListener;


import com.zgsy.bj.R;

import static java.lang.Integer.MAX_VALUE;


public class initiateActivity extends Activity implements OnPageChangeListener {
    private ViewPager mviewPager;
    private PagerAdapter pageradapter;
    private static  boolean record=false;
    private int[] imgIdArray;
    public  ArrayList<ImageView> mImageViews=new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public  int i=0;
    private onjudge mAuthTask = null;
    final Lock lock=new ReentrantLock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuthTask = new onjudge();
        mAuthTask.execute();
        //PushAgent.getInstance(context).onAppStart();


    }

    private void initView(){
        imgIdArray = new int[]{R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.forth};

        new Thread() {
            public void run(){
                ImageView imageView1 = new ImageView(initiateActivity.this);
                ImageView imageView2 = new ImageView(initiateActivity.this);
                ImageView imageView3 = new ImageView(initiateActivity.this);
                ImageView imageView4 = new ImageView(initiateActivity.this);
                ArrayList<ImageView> mmImageViews = new ArrayList<>();
                imageView1.setImageResource(imgIdArray[0]);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView1);
                imageView2.setImageResource(imgIdArray[1]);
                imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView2);
                imageView3.setImageResource(imgIdArray[2]);
                imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView3);
                imageView4.setImageResource(imgIdArray[3]);
                imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView4);
                mImageViews.addAll(mmImageViews);
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //System.out.println(position);
        if (position % 4 == 3) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void PagerAdapter(){
        pageradapter=new PagerAdapter() {


            @Override
            public int getCount() {
                return MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(mImageViews.get(position % mImageViews.size()));

            }

            /**
             * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
             */
            @Override
            public Object instantiateItem(View container, int position) {


                ((ViewPager) container).addView(mImageViews.get(position % mImageViews.size()), 0);
                return mImageViews.get(position % mImageViews.size());
            }

        };
    }
    public Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {

            switch(msg.what)
            {
                case 0:
                    record=true;
                    //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
                    mviewPager.setCurrentItem((mImageViews.size()));
                    mviewPager.setOnPageChangeListener(initiateActivity.this);
                    mviewPager.setAdapter(pageradapter);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };




    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    //后台做一个简单的判断，需要读取文件操作，比较耗时
    public class onjudge extends AsyncTask<Void, Void, Integer>{



        @Override
        protected Integer doInBackground(Void... params) {




            lock.lock();
            SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(initiateActivity.this);
            i=share.getInt("first",0);




                if(i==0)    {
                    lock.unlock();

                    SharedPreferences sharedPreferences =PreferenceManager.getDefaultSharedPreferences(initiateActivity.this);; //私有数据
                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                    editor.putInt("first", 1);
                    editor.apply();//提交修改，这里用apply是先跳过写这个步骤然后直接进行下一步，如果使用commit就更加耗时
                    initView();//初始化图片加载

                }




            return i;
        }

        @Override
        protected void onPostExecute(final Integer success) {
            mAuthTask = null;

            if(success!=0){
                    Intent intent = new Intent();
                    intent.setClass(initiateActivity.this, start.class);
                    startActivity(intent);
                    finish();
            }
            else{
                setContentView(R.layout.activity_initiate);
                mviewPager = (ViewPager) findViewById(R.id.initviewPager);
                //适配器
                PagerAdapter();
            }
        }



    }
}
