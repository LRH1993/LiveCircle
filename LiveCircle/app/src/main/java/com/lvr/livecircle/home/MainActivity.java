package com.lvr.livecircle.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.HomeViewPagerAdapter;
import com.lvr.livecircle.base.BaseActivity;
import com.lvr.livecircle.bean.FabScrollBean;
import com.lvr.livecircle.find.FindFragment;
import com.lvr.livecircle.meitu.MeiTuFragment;
import com.lvr.livecircle.music.MusicFragment;
import com.lvr.livecircle.nearby.NearByFragment;
import com.lvr.livecircle.news.fragment.NewsFragment;
import com.lvr.livecircle.recommend.RecommendFragment;
import com.lvr.livecircle.utils.StatusBarSetting;
import com.lvr.livecircle.video.VideoFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mAmNv;
    @BindView(R.id.drawer_layout)
    DrawerLayout mAmDl;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.vp_moudle)
    ViewPager mVpMoudle;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private View mView_nav;
    private ImageView mIv_photo;
    private String[] mMoudleName = {"推荐", "新闻", "音乐", "美图", "附近", "视频", "发现"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private NewsFragment mNewsFragment;
    private RecommendFragment mRecommendFragment;
    private MusicFragment mMusicFragment;
    private MeiTuFragment mMeiTuFragment;
    private NearByFragment mNearByFragment;
    private VideoFragment mVideoFragment;
    private FindFragment mFindFragment;
    private HomeViewPagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        StatusBarSetting.setColorForDrawerLayout(this, mAmDl, getResources().getColor(R.color.colorPrimary), StatusBarSetting.DEFAULT_STATUS_BAR_ALPHA);
        setToolBar();
        setNavigationView();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FabScrollBean("滑动到顶端",mTabs.getSelectedTabPosition()));

            }
        });
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==1||position==3){
                    mFab.setVisibility(View.VISIBLE);
                }else{
                    mFab.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        setViewPager();
    }

    /**
     * 填充ViewPager内容
     */
    private void setViewPager() {
        mAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), mFragmentList, Arrays.asList(mMoudleName));
        mVpMoudle.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mVpMoudle);
        mTabs.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.black));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        if (mNewsFragment.isAdded()) {
            manager.putFragment(outState, "NewsFragment", mNewsFragment);
        }
        if (mFindFragment.isAdded()) {
            manager.putFragment(outState, "FindFragment", mFindFragment);
        }
        if (mVideoFragment.isAdded()) {
            manager.putFragment(outState, "VideoFragment", mVideoFragment);
        }
        if (mNearByFragment.isAdded()) {
            manager.putFragment(outState, "NearByFragment", mNearByFragment);
        }
        if (mMeiTuFragment.isAdded()) {
            manager.putFragment(outState, "MeiTuFragment", mMeiTuFragment);
        }
        if (mMusicFragment.isAdded()) {
            manager.putFragment(outState, "MusicFragment", mMusicFragment);
        }
        if (mRecommendFragment.isAdded()) {
            manager.putFragment(outState, "RecommendFragment", mRecommendFragment);
        }
    }

    /**
     * 初始化fragment的记忆状态
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            mNewsFragment = (NewsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "NewsFragment");
            mRecommendFragment = (RecommendFragment) getSupportFragmentManager().getFragment(savedInstanceState, "RecommendFragment");
            mFindFragment = (FindFragment) getSupportFragmentManager().getFragment(savedInstanceState, "FindFragment");
            mVideoFragment = (VideoFragment) getSupportFragmentManager().getFragment(savedInstanceState, "VideoFragment");
            mNearByFragment = (NearByFragment) getSupportFragmentManager().getFragment(savedInstanceState, "NearByFragment");
            mMeiTuFragment = (MeiTuFragment) getSupportFragmentManager().getFragment(savedInstanceState, "MeiTuFragment");
            mMusicFragment = (MusicFragment) getSupportFragmentManager().getFragment(savedInstanceState, "MusicFragment");
        } else {
            mNewsFragment = new NewsFragment();
            mRecommendFragment = new RecommendFragment();
            mFindFragment = new FindFragment();
            mVideoFragment = new VideoFragment();
            mNearByFragment = new NearByFragment();
            mMeiTuFragment = new MeiTuFragment();
            mMusicFragment = new MusicFragment();
        }
        mFragmentList.add(mRecommendFragment);
        mFragmentList.add(mNewsFragment);
        mFragmentList.add(mMusicFragment);
        mFragmentList.add(mMeiTuFragment);
        mFragmentList.add(mNearByFragment);
        mFragmentList.add(mVideoFragment);
        mFragmentList.add(mFindFragment);
    }

    /**
     * 设置导航页信息
     */
    private void setNavigationView() {
        //NavigationView初始化
        mAmNv.setItemIconTintList(null);
        View headerView = mAmNv.getHeaderView(0);
        mIv_photo = (ImageView) headerView.findViewById(R.id.iv_user_photo);
    }

    /**
     * 设置状态栏
     */
    private void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        //菜单按钮可用
        actionBar.setHomeButtonEnabled(true);
        //回退按钮可用
        actionBar.setDisplayHomeAsUpEnabled(true);
        //将drawlayout与toolbar绑定在一起
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, mAmDl, mToolbar, R.string.app_name, R.string.app_name);
        abdt.syncState();//初始化状态
        //设置drawlayout的监听事件 打开/关闭
        mAmDl.setDrawerListener(abdt);
        //actionbar中的内容进行初始化
        mToolbar.setTitle("Live圈");//设置标题
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolabr, menu);
        return true;
    }
}
