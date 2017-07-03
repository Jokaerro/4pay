package pro.games_box.a4pay.presentation.screen.base;

/**
 * Created by Tesla on 03.07.2017.
 */

public class BasePresenter<View> {
    protected View mView;

    public void setView(View view) {
        mView = view;
    }

    public void destroy() {
        mView = null;
    }
}
