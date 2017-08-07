package zjl.com.dagger_mvp_rxjava_demo2.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

@SuppressWarnings("ALL")
public class NewsListBean implements Parcelable {

    private String date;
    private List<NewsBean> stories;
    private List<LastestNewsBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsBean> getStories() {
        return stories;
    }

    public void setStories(List<NewsBean> stories) {
        this.stories = stories;
    }

    public List<LastestNewsBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<LastestNewsBean> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }

    public NewsListBean() {
        super();
    }

    protected NewsListBean(Parcel in) {
        this.date = in.readString();
        this.stories = in.createTypedArrayList(NewsBean.CREATOR);
        this.top_stories = in.createTypedArrayList(LastestNewsBean.CREATOR);
    }

    public static final Creator<NewsListBean> CREATOR = new Creator<NewsListBean>() {
        public NewsListBean createFromParcel(Parcel source) {
            return new NewsListBean(source);
        }

        public NewsListBean[] newArray(int size) {
            return new NewsListBean[size];
        }
    };
}
