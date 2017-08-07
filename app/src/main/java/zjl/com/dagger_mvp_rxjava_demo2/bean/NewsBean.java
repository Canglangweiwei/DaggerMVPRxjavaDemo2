package zjl.com.dagger_mvp_rxjava_demo2.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

@SuppressWarnings("ALL")
public class NewsBean implements Parcelable {

    private int id;
    private int type;
    private String date;
    private String title;
    private String ga_prefix;
    private List<String> images;
    private boolean isRead = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(id);
        dest.writeString(ga_prefix);
        dest.writeString(title);
        dest.writeStringList(images);
        dest.writeByte(isRead ? (byte) 1 : (byte) 0);
    }

    public NewsBean() {
        super();
    }

    protected NewsBean(Parcel in) {
        this.type = in.readInt();
        this.id = in.readInt();
        this.ga_prefix = in.readString();
        this.title = in.readString();
        this.images = in.createStringArrayList();
        this.isRead = in.readByte() != 0;
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
