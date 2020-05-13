package $natural$com$$.hotbitmapgg.bilibili.utils;
import $natural$android$$.$natural$content$$.$natural$Context$$;
import $natural$android$$.util.DisplayMetrics;
/** 
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 屏幕像素转换工具类
 */
public class DisplayUtil {
  public static int px2dp(  $natural$Context$$ context,  float pxValue){
    final float scale=context.getResources().getDisplayMetrics().density;
    return (int)(pxValue / scale + 0.5f);
  }
  public static int dp2px(  $natural$Context$$ context,  float dipValue){
    final float scale=context.getResources().getDisplayMetrics().density;
    return (int)(dipValue * scale + 0.5f);
  }
  public static int px2sp(  $natural$Context$$ context,  float pxValue){
    final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
    return (int)(pxValue / fontScale + 0.5f);
  }
  public static int sp2px(  $natural$Context$$ context,  float spValue){
    final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
    return (int)(spValue * fontScale + 0.5f);
  }
  public static int getScreenWidth(  $natural$Context$$ context){
    DisplayMetrics dm=context.getResources().getDisplayMetrics();
    return dm.widthPixels;
  }
  public static int getScreenHeight(  $natural$Context$$ context){
    DisplayMetrics dm=context.getResources().getDisplayMetrics();
    return dm.heightPixels;
  }
  public static float getDisplayDensity(  $natural$Context$$ context){
    if (context == null) {
      return -1;
    }
    return context.getResources().getDisplayMetrics().density;
  }
}
