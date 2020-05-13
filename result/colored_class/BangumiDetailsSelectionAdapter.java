package $natural$com$$.hotbitmapgg.bilibili.adapter;
import $natural$android$$.support.v7.$natural$widget$$.CardView;
import $natural$android$$.support.v7.$natural$widget$$.RecyclerView;
import $natural$android$$.$natural$view$$.LayoutInflater;
import $natural$android$$.$natural$view$$.$natural$View$$;
import $natural$android$$.$natural$view$$.ViewGroup;
import $natural$android$$.$natural$widget$$.$natural$TextView$$;
import $natural$com$$.hotbitmapgg.bilibili.adapter.helper.AbsRecyclerViewAdapter;
import $natural$com$$.hotbitmapgg.bilibili.entity.bangumi.BangumiDetailsInfo;
import $natural$com$$.hotbitmapgg.ohmybilibili.R;
import java.util.List;
/** 
 * Created by hcc on 2016/10/1 17:12
 * 100332338@qq.com
 * <p>
 * 番剧选集adapter
 */
public class BangumiDetailsSelectionAdapter extends AbsRecyclerViewAdapter {
  private int layoutPosition=0;
  private List<BangumiDetailsInfo.ResultBean.EpisodesBean> episodes;
  public BangumiDetailsSelectionAdapter(  RecyclerView recyclerView,  List<BangumiDetailsInfo.ResultBean.EpisodesBean> episodes){
    super(recyclerView);
    this.episodes=episodes;
  }
  @Override public ClickableViewHolder onCreateViewHolder(  ViewGroup parent,  int viewType){
    bindContext(parent.getContext());
    return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_bangumi_selection,parent,false));
  }
  @Override public void onBindViewHolder(  ClickableViewHolder holder,  int position){
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
      BangumiDetailsInfo.ResultBean.EpisodesBean episodesBean=episodes.get(position);
      itemViewHolder.mIndex.setText("第 " + episodesBean.getIndex() + " 话");
      itemViewHolder.mTitle.setText(episodesBean.getIndex_title());
      if (position == layoutPosition) {
        itemViewHolder.mCardView.setForeground(getContext().getResources().$natural$getDrawable$$(R.$natural$drawable$$.bg_selection));
        itemViewHolder.mTitle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        itemViewHolder.mIndex.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
      }
 else {
        itemViewHolder.mCardView.setForeground(getContext().getResources().$natural$getDrawable$$(R.$natural$drawable$$.bg_normal));
        itemViewHolder.mTitle.setTextColor(getContext().getResources().getColor(R.color.black_alpha_45));
        itemViewHolder.mIndex.setTextColor(getContext().getResources().getColor(R.color.font_normal));
      }
    }
    super.onBindViewHolder(holder,position);
  }
  public void notifyItemForeground(  int clickPosition){
    layoutPosition=clickPosition;
    notifyDataSetChanged();
  }
  @Override public int getItemCount(){
    return episodes.size();
  }
public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
    CardView mCardView;
    $natural$TextView$$ mIndex;
    $natural$TextView$$ mTitle;
    public ItemViewHolder(    $natural$View$$ itemView){
      super(itemView);
      mCardView=$(R.id.card_view);
      mIndex=$(R.id.tv_index);
      mTitle=$(R.id.tv_title);
    }
  }
}
