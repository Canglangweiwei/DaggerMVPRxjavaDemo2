package zjl.com.dagger_mvp_rxjava_demo2.utils;

import android.text.TextUtils;

public class Validator {

    public Validator() {
        super();
    }

    public boolean validUsername(String username) {
        return !TextUtils.isEmpty(username);
    }

    public boolean validPassword(String password) {
        return !TextUtils.isEmpty(password);
    }
}
