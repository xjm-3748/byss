package $natural$com$$.hotbitmapgg.bilibili.media;
import $natural$android$$.annotation.SuppressLint;
import $natural$android$$.$natural$content$$.$natural$Context$$;
import $natural$android$$.graphics.Rect;
import $natural$android$$.media.AudioManager;
import $natural$android$$.$natural$os$$.Build;
import $natural$android$$.$natural$os$$.$natural$Handler$$;
import $natural$android$$.$natural$os$$.Message;
import $natural$android$$.util.AttributeSet;
import $natural$android$$.$natural$view$$.Gravity;
import $natural$android$$.$natural$view$$.KeyEvent;
import $natural$android$$.$natural$view$$.LayoutInflater;
import $natural$android$$.$natural$view$$.MotionEvent;
import $natural$android$$.$natural$view$$.$natural$View$$;
import $natural$android$$.$natural$widget$$.FrameLayout;
import $natural$android$$.$natural$widget$$.ImageButton;
import $natural$android$$.$natural$widget$$.ImageView;
import $natural$android$$.$natural$widget$$.LinearLayout;
import $natural$android$$.$natural$widget$$.PopupWindow;
import $natural$android$$.$natural$widget$$.ProgressBar;
import $natural$android$$.$natural$widget$$.SeekBar;
import $natural$android$$.$natural$widget$$.SeekBar.OnSeekBarChangeListener;
import $natural$android$$.$natural$widget$$.$natural$TextView$$;
import $natural$com$$.hotbitmapgg.bilibili.media.callback.MediaPlayerListener;
import $natural$com$$.hotbitmapgg.bilibili.utils.LogUtil;
import $natural$com$$.hotbitmapgg.ohmybilibili.R;
import $natural$com$$.hotbitmapgg.bilibili.media.callback.DanmukuSwitchListener;
import $natural$com$$.hotbitmapgg.bilibili.media.callback.VideoBackListener;
import java.util.Locale;
/** 
 * Created by hcc on 16/8/31 19:50
 * 100332338@qq.com
 * <p/>
 * 播放器控制器
 */
public class MediaController extends FrameLayout {
  private static final int sDefaultTimeout=3000;
  private static final int FADE_OUT=1;
  private static final int SHOW_PROGRESS=2;
  private MediaPlayerListener mPlayer;
  private $natural$Context$$ mContext;
  private PopupWindow mWindow;
  private int mAnimStyle;
  private $natural$View$$ mAnchor;
  private $natural$View$$ mRoot;
  private ProgressBar mProgress;
  private $natural$TextView$$ mEndTime, mCurrentTime;
  private $natural$TextView$$ mTitleView;
  private OutlineTextView mInfoView;
  private String mTitle;
  private long mDuration;
  private boolean mShowing;
  private boolean mDragging;
  private boolean mInstantSeeking=true;
  private boolean mFromXml=false;
  private ImageButton mPauseButton;
  private AudioManager mAM;
  private OnShownListener mShownListener;
  private OnHiddenListener mHiddenListener;
  private boolean mDanmakuShow=false;
  private DanmukuSwitchListener mDanmukuSwitchListener;
  private ImageView mBack;
  private VideoBackListener mVideoBackListener;
  private ImageView mTvPlay;
  public void setDanmakuSwitchListener(  DanmukuSwitchListener danmukuSwitchListener){
    this.mDanmukuSwitchListener=danmukuSwitchListener;
  }
  public void setVideoBackEvent(  VideoBackListener videoBackListener){
    this.mVideoBackListener=videoBackListener;
  }
  @SuppressLint("HandlerLeak") private $natural$Handler$$ mHandler;
  private OnClickListener mPauseListener;
  private Runnable lastRunnable;
  private OnSeekBarChangeListener mSeekListener;
  public MediaController(  $natural$Context$$ context,  AttributeSet attrs){
    super(context,attrs);
    mRoot=this;
    mFromXml=true;
    initController(context);
  }
  public MediaController(  $natural$Context$$ context){
    super(context);
    if (!mFromXml && initController(context)) {
      initFloatingWindow();
    }
  }
  private static String generateTime(  long position){
    int totalSeconds=(int)((position / 1000.0) + 0.5);
    int seconds=totalSeconds % 60;
    int minutes=(totalSeconds / 60) % 60;
    int hours=totalSeconds / 3600;
    if (hours > 0) {
      return String.format(Locale.US,"%02d:%02d:%02d",hours,minutes,seconds);
    }
 else {
      return String.format(Locale.US,"%02d:%02d",minutes,seconds);
    }
  }
  private boolean initController(  $natural$Context$$ context){
    mContext=context;
    mAM=(AudioManager)mContext.getSystemService($natural$Context$$.AUDIO_SERVICE);
    return true;
  }
  @Override public void onFinishInflate(){
    super.onFinishInflate();
    if (mRoot != null) {
      initControllerView(mRoot);
    }
  }
  private void initFloatingWindow(){
    mWindow=new PopupWindow(mContext);
    mWindow.setFocusable(false);
    mWindow.setBackgroundDrawable(null);
    mWindow.setOutsideTouchable(true);
    mAnimStyle=$natural$android$$.R.style.Animation;
  }
  /** 
 * 设置VideoView
 */
  public void setAnchorView(  $natural$View$$ $natural$view$$){
    mAnchor=$natural$view$$;
    if (!mFromXml) {
      removeAllViews();
      mRoot=makeControllerView();
      mWindow.setContentView(mRoot);
      mWindow.setWidth(LayoutParams.MATCH_PARENT);
      mWindow.setHeight(LayoutParams.WRAP_CONTENT);
    }
    initControllerView(mRoot);
  }
  /** 
 * 创建视图包含小部件,控制回放
 */
  protected $natural$View$$ makeControllerView(){
    return ((LayoutInflater)mContext.getSystemService($natural$Context$$.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_media_controller,this);
  }
  private void initControllerView(  $natural$View$$ v){
  }
  public void setMediaPlayer(  MediaPlayerListener player){
    mPlayer=player;
    updatePausePlay();
  }
  /** 
 * 拖动seekBar时回调
 */
  public void setInstantSeeking(  boolean seekWhenDragging){
    mInstantSeeking=seekWhenDragging;
  }
  public void $natural$show$$(){
    $natural$show$$(sDefaultTimeout);
  }
  /** 
 * 设置播放的文件名称
 */
  public void setTitle(  String name){
    mTitle=name;
    if (mTitleView != null) {
      mTitleView.setText(mTitle);
    }
  }
  /** 
 * 设置MediaController持有的View
 */
  public void setInfoView(  OutlineTextView v){
    mInfoView=v;
  }
  private void disableUnsupportedButtons(){
    if (mPauseButton != null && mTvPlay != null && !mPlayer.canPause()) {
      mPauseButton.setEnabled(false);
      mTvPlay.setEnabled(false);
    }
  }
  /** 
 * 改变控制器的动画风格的资源
 */
  public void setAnimationStyle(  int animationStyle){
    mAnimStyle=animationStyle;
  }
  /** 
 * 在屏幕上显示控制器
 */
  @SuppressLint("InlinedApi") public void $natural$show$$(  int timeout){
    if (!mShowing && mAnchor != null && mAnchor.getWindowToken() != null) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        mAnchor.setSystemUiVisibility($natural$View$$.SYSTEM_UI_FLAG_VISIBLE);
      }
      if (mPauseButton != null && mTvPlay != null) {
        mPauseButton.requestFocus();
        mTvPlay.requestFocus();
      }
      disableUnsupportedButtons();
      if (mFromXml) {
        setVisibility($natural$View$$.VISIBLE);
      }
 else {
        int[] location=new int[2];
        mAnchor.getLocationOnScreen(location);
        Rect anchorRect=new Rect(location[0],location[1],location[0] + mAnchor.getWidth(),location[1] + mAnchor.getHeight());
        mWindow.setAnimationStyle(mAnimStyle);
        mWindow.showAtLocation(mAnchor,Gravity.BOTTOM,anchorRect.left,0);
      }
      mShowing=true;
      if (mShownListener != null) {
        mShownListener.onShown();
      }
    }
    updatePausePlay();
    mHandler.sendEmptyMessage(SHOW_PROGRESS);
    if (timeout != 0) {
      mHandler.removeMessages(FADE_OUT);
      mHandler.sendMessageDelayed(mHandler.obtainMessage(FADE_OUT),timeout);
    }
  }
  public boolean isShowing(){
    return mShowing;
  }
  @SuppressLint("InlinedApi") public void hide(){
    if (mAnchor == null) {
      return;
    }
    if (mShowing) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        mAnchor.setSystemUiVisibility($natural$View$$.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
      }
      try {
        mHandler.removeMessages(SHOW_PROGRESS);
        if (mFromXml) {
          setVisibility($natural$View$$.GONE);
        }
 else {
          mWindow.dismiss();
        }
      }
 catch (      IllegalArgumentException ex) {
        LogUtil.all("MediaController already removed");
      }
      mShowing=false;
      if (mHiddenListener != null) {
        mHiddenListener.onHidden();
      }
    }
  }
  public void setOnShownListener(  OnShownListener l){
    mShownListener=l;
  }
  public void setOnHiddenListener(  OnHiddenListener l){
    mHiddenListener=l;
  }
  private long setProgress(){
    if (mPlayer == null || mDragging) {
      return 0;
    }
    int position=mPlayer.getCurrentPosition();
    int duration=mPlayer.getDuration();
    if (mProgress != null) {
      if (duration > 0) {
        long pos=1000L * position / duration;
        mProgress.setProgress((int)pos);
      }
      int percent=mPlayer.getBufferPercentage();
      mProgress.setSecondaryProgress(percent * 10);
    }
    mDuration=duration;
    if (mEndTime != null) {
      mEndTime.setText(generateTime(mDuration));
    }
    if (mCurrentTime != null) {
      mCurrentTime.setText(generateTime(position));
    }
    return position;
  }
  @Override public boolean onTouchEvent(  MotionEvent event){
    $natural$show$$(sDefaultTimeout);
    return true;
  }
  @Override public boolean onTrackballEvent(  MotionEvent ev){
    $natural$show$$(sDefaultTimeout);
    return false;
  }
  @Override public boolean dispatchKeyEvent(  KeyEvent event){
    int keyCode=event.getKeyCode();
    if (event.getRepeatCount() == 0 && (keyCode == KeyEvent.KEYCODE_HEADSETHOOK || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE || keyCode == KeyEvent.KEYCODE_SPACE)) {
      doPauseResume();
      $natural$show$$(sDefaultTimeout);
      if (mPauseButton != null && mTvPlay != null) {
        mPauseButton.requestFocus();
        mTvPlay.requestFocus();
      }
      return true;
    }
 else     if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP) {
      if (mPlayer.isPlaying()) {
        mPlayer.pause();
        updatePausePlay();
      }
      return true;
    }
 else     if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
      hide();
      return true;
    }
 else {
      $natural$show$$(sDefaultTimeout);
    }
    return super.dispatchKeyEvent(event);
  }
  private void updatePausePlay(){
    if (mRoot == null || mPauseButton == null || mTvPlay == null) {
      return;
    }
    if (mPlayer.isPlaying()) {
      mPauseButton.setImageResource(R.$natural$drawable$$.bili_player_play_can_pause);
      mTvPlay.setImageResource(R.$natural$drawable$$.ic_tv_stop);
    }
 else {
      mPauseButton.setImageResource(R.$natural$drawable$$.bili_player_play_can_play);
      mTvPlay.setImageResource(R.$natural$drawable$$.ic_tv_play);
    }
  }
  private void doPauseResume(){
    if (mPlayer.isPlaying()) {
      mPlayer.pause();
    }
 else {
      mPlayer.start();
    }
    updatePausePlay();
  }
  @Override public void setEnabled(  boolean enabled){
    if (mPauseButton != null) {
      mPauseButton.setEnabled(enabled);
    }
    if (mTvPlay != null) {
      mTvPlay.setEnabled(enabled);
    }
    if (mProgress != null) {
      mProgress.setEnabled(enabled);
    }
    disableUnsupportedButtons();
    super.setEnabled(enabled);
  }
public interface OnShownListener {
    void onShown();
  }
public interface OnHiddenListener {
    void onHidden();
  }
}
