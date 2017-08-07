package zjl.com.dagger_mvp_rxjava_demo2.bean;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("ALL")
public class LastestNewsBean implements Parcelable {

    private int id;
    private int type;
    private String title;
    private String image;
    private String ga_prefix;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(type);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(ga_prefix);
    }

    public LastestNewsBean() {
        super();
    }

    protected LastestNewsBean(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.title = in.readString();
        this.image = in.readString();
        this.ga_prefix = in.readString();
    }

    public static final Creator<LastestNewsBean> CREATOR = new Creator<LastestNewsBean>() {
        public LastestNewsBean createFromParcel(Parcel source) {
            return new LastestNewsBean(source);
        }

        public LastestNewsBean[] newArray(int size) {
            return new LastestNewsBean[size];
        }
    };
}
