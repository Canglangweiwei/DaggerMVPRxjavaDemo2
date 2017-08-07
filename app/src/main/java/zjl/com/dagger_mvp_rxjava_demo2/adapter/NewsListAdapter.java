package zjl.com.dagger_mvp_rxjava_demo2.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zjl.com.dagger_mvp_rxjava_demo2.R;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsBean;
import zjl.com.dagger_mvp_rxjava_demo2.ui.NewsDetailActivity;

/**
 * <p>
 * 功能描述：新闻列表
 * </p>
 * Created by weiwei on 2017/07/04
 */
@SuppressWarnings("ALL")
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private Context mContext;
    private List<NewsBean> mNewsBeanList;

    public NewsListAdapter(Context context, List<NewsBean> newsBeanList) {
        this.mContext = context;
        this.mNewsBeanList = newsBeanList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsBean newsBean = mNewsBeanList.get(position);
        if (newsBean == null) {
            return;
        }
        if (holder instanceof NewsViewHolder) {
            holder.bindItem(holder, newsBean);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsBeanList == null ? 0 : mNewsBeanList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_news)
        CardView mCardview;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView tv_title;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 数据处理
         */
        void bindItem(NewsViewHolder holder, NewsBean newsBean) {
            if (!newsBean.getTitle().isEmpty()) {
                holder.tv_title.setText(newsBean.getTitle());
            } else {
                holder.tv_title.setText(mContext.getString(R.string.no_brief));
            }
            List<String> images = newsBean.getImages();
            if (images != null && images.size() > 0) {
                Glide.with(mContext).load(images.get(0))
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.mIvNews);
                holder.mCardview.setOnClickListener(getListener(newsBean));
            }
        }

        /**
         * 定义监听事件
         *
         * @param newsBean 新闻
         * @return 监听事件
         */
        private View.OnClickListener getListener(final NewsBean newsBean) {

            return new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("new", newsBean.getId());
                    mContext.startActivity(intent);
                }
            };
        }
    }
}


















