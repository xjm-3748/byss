package $natural$com$$.hotbitmapgg.bilibili.module.search;
import $natural$android$$.$natural$app$$.Activity;
import $natural$android$$.$natural$content$$.Intent;
import $natural$android$$.graphics.$natural$drawable$$.AnimationDrawable;
import $natural$android$$.$natural$os$$.Bundle;
import $natural$android$$.support.v4.$natural$app$$.Fragment;
import $natural$android$$.support.v4.$natural$app$$.FragmentManager;
import $natural$android$$.support.v4.$natural$app$$.FragmentStatePagerAdapter;
import $natural$android$$.support.v4.$natural$view$$.ViewPager;
import $natural$android$$.$natural$text$$.TextPaint;
import $natural$android$$.$natural$text$$.TextUtils;
import $natural$android$$.$natural$view$$.$natural$View$$;
import $natural$android$$.$natural$view$$.inputmethod.EditorInfo;
import $natural$android$$.$natural$widget$$.EditText;
import $natural$android$$.$natural$widget$$.ImageView;
import $natural$android$$.$natural$widget$$.LinearLayout;
import $natural$android$$.$natural$widget$$.$natural$TextView$$;
import $natural$com$$.flyco.tablayout.SlidingTabLayout;
import $natural$com$$.hotbitmapgg.bilibili.base.RxBaseActivity;
import $natural$com$$.hotbitmapgg.bilibili.entity.search.SearchArchiveInfo;
import $natural$com$$.hotbitmapgg.bilibili.network.RetrofitHelper;
import $natural$com$$.hotbitmapgg.bilibili.utils.ConstantUtil;
import $natural$com$$.hotbitmapgg.bilibili.utils.KeyBoardUtil;
import $natural$com$$.hotbitmapgg.bilibili.utils.StatusBarUtil;
import $natural$com$$.hotbitmapgg.bilibili.$natural$widget$$.NoScrollViewPager;
import $natural$com$$.hotbitmapgg.ohmybilibili.R;
import $natural$com$$.jakewharton.rxbinding.$natural$view$$.RxView;
import $natural$com$$.jakewharton.rxbinding.$natural$widget$$.RxTextView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.$natural$android$$.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/** 
 * Created by hcc on 16/8/29 19:58
 * 100332338@qq.com
 * <p/>
 * 全站搜索界面
 */
public class TotalStationSearchActivity extends RxBaseActivity {
  @BindView(R.id.sliding_tabs) SlidingTabLayout mSlidingTabLayout;
  @BindView(R.id.view_pager) NoScrollViewPager mViewPager;
  @BindView(R.id.iv_search_loading) ImageView mLoadingView;
  @BindView(R.id.search_img) ImageView mSearchBtn;
  @BindView(R.id.search_edit) EditText mSearchEdit;
  @BindView(R.id.search_text_clear) ImageView mSearchTextClear;
  @BindView(R.id.search_layout) LinearLayout mSearchLayout;
  private String $natural$content$$;
  private AnimationDrawable mAnimationDrawable;
  private List<String> titles=new ArrayList<>();
  private List<Fragment> fragments=new ArrayList<>();
  private List<SearchArchiveInfo.DataBean.NavBean> navs=new ArrayList<>();
  @Override public int getLayoutId(){
    return R.layout.activity_search;
  }
  @Override public void initToolBar(){
    StatusBarUtil.from(this).setLightStatusBar(true).process();
  }
  @Override public void initViews(  Bundle savedInstanceState){
    Intent intent=getIntent();
    if (intent != null) {
      $natural$content$$=intent.getStringExtra(ConstantUtil.EXTRA_CONTENT);
    }
    mLoadingView.setImageResource(R.$natural$drawable$$.anim_search_loading);
    mAnimationDrawable=(AnimationDrawable)mLoadingView.$natural$getDrawable$$();
    showSearchAnim();
    mSearchEdit.clearFocus();
    mSearchEdit.setText($natural$content$$);
    getSearchData();
    search();
    setUpEditText();
  }
  private void setUpEditText(){
  }
  private void search(){
  }
  private void clearData(){
    navs.clear();
    titles.clear();
    fragments.clear();
  }
  private void getSearchData(){
  }
  @Override public void finishTask(){
    hideSearchAnim();
    titles.add("综合");
    titles.add(navs.get(0).getName() + "(" + checkNumResults(navs.get(0).getTotal())+ ")");
    titles.add(navs.get(1).getName() + "(" + checkNumResults(navs.get(1).getTotal())+ ")");
    titles.add(navs.get(2).getName() + "(" + checkNumResults(navs.get(2).getTotal())+ ")");
    ArchiveResultsFragment archiveResultsFragment=ArchiveResultsFragment.newInstance($natural$content$$);
    BangumiResultsFragment bangumiResultsFragment=BangumiResultsFragment.newInstance($natural$content$$);
    UpperResultsFragment upperResultsFragment=UpperResultsFragment.newInstance($natural$content$$);
    MovieResultsFragment movieResultsFragment=MovieResultsFragment.newInstance($natural$content$$);
    fragments.add(archiveResultsFragment);
    fragments.add(bangumiResultsFragment);
    fragments.add(upperResultsFragment);
    fragments.add(movieResultsFragment);
    SearchTabAdapter mAdapter=new SearchTabAdapter(getSupportFragmentManager(),titles,fragments);
    mViewPager.setAdapter(mAdapter);
    mViewPager.setOffscreenPageLimit(titles.size());
    mSlidingTabLayout.setViewPager(mViewPager);
    measureTabLayoutTextWidth(0);
    mSlidingTabLayout.setCurrentTab(0);
    mAdapter.notifyDataSetChanged();
    mSlidingTabLayout.notifyDataSetChanged();
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
      @Override public void onPageScrolled(      int position,      float positionOffset,      int positionOffsetPixels){
      }
      @Override public void onPageSelected(      int position){
        measureTabLayoutTextWidth(position);
      }
      @Override public void onPageScrollStateChanged(      int state){
      }
    }
);
  }
  public String checkNumResults(  int numResult){
    return numResult > 100 ? "99+" : String.valueOf(numResult);
  }
  private void measureTabLayoutTextWidth(  int position){
    String title=titles.get(position);
    $natural$TextView$$ titleView=mSlidingTabLayout.getTitleView(position);
    TextPaint paint=titleView.getPaint();
    float textWidth=paint.measureText(title);
    mSlidingTabLayout.setIndicatorWidth(textWidth / 3);
  }
  private void showSearchAnim(){
    mLoadingView.setVisibility($natural$View$$.VISIBLE);
    mSearchLayout.setVisibility($natural$View$$.GONE);
    mAnimationDrawable.start();
  }
  private void hideSearchAnim(){
    mLoadingView.setVisibility($natural$View$$.GONE);
    mSearchLayout.setVisibility($natural$View$$.VISIBLE);
    mAnimationDrawable.stop();
  }
  public void setEmptyLayout(){
    mLoadingView.setVisibility($natural$View$$.VISIBLE);
    mSearchLayout.setVisibility($natural$View$$.GONE);
    mLoadingView.setImageResource(R.$natural$drawable$$.search_failed);
  }
  @OnClick(R.id.search_back) void OnBack(){
    onBackPressed();
  }
  @Override public void onBackPressed(){
    if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
      mAnimationDrawable.stop();
      mAnimationDrawable=null;
    }
    super.onBackPressed();
  }
  public static void launch(  Activity activity,  String str){
    Intent mIntent=new Intent(activity,TotalStationSearchActivity.class);
    mIntent.putExtra(ConstantUtil.EXTRA_CONTENT,str);
    activity.startActivity(mIntent);
  }
  @Override protected void onDestroy(){
    super.onDestroy();
    if (mAnimationDrawable != null) {
      mAnimationDrawable.stop();
      mAnimationDrawable=null;
    }
  }
private static class SearchTabAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;
    SearchTabAdapter(    FragmentManager fm,    List<String> titles,    List<Fragment> fragments){
      super(fm);
      this.titles=titles;
      this.fragments=fragments;
    }
    @Override public Fragment getItem(    int position){
      return fragments.get(position);
    }
    @Override public CharSequence getPageTitle(    int position){
      return titles.get(position);
    }
    @Override public int getCount(){
      return fragments.size();
    }
  }
}
