package $natural$com$$.hotbitmapgg.bilibili.utils;
import $natural$android$$.$natural$content$$.$natural$Context$$;
import $natural$android$$.$natural$os$$.$natural$Handler$$;
import $natural$android$$.$natural$os$$.$natural$Looper$$;
import $natural$android$$.$natural$widget$$.Toast;
import $natural$com$$.hotbitmapgg.bilibili.BilibiliApp;
/** 
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * Toast工具类
 */
public class ToastUtil {
  public static void showShort(  $natural$Context$$ context,  String $natural$text$$){
    Toast.makeText(context,$natural$text$$,Toast.LENGTH_SHORT).$natural$show$$();
  }
  public static void showShort(  $natural$Context$$ context,  int resId){
    Toast.makeText(context,resId,Toast.LENGTH_SHORT).$natural$show$$();
  }
  public static void showLong(  $natural$Context$$ context,  String $natural$text$$){
    Toast.makeText(context,$natural$text$$,Toast.LENGTH_LONG).$natural$show$$();
  }
  public static void showLong(  $natural$Context$$ context,  int resId){
    Toast.makeText(context,resId,Toast.LENGTH_LONG).$natural$show$$();
  }
  public static void LongToast(  final String $natural$text$$){
  }
  public static void LongToast(  final int stringId){
  }
  public static void ShortToast(  final String $natural$text$$){
  }
  public static void ShortToast(  final int stringId){
  }
}
