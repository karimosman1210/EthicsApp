package com.elmostafa.ethicstask.base_classes;

public abstract  class BasePresenter<V extends BaseView> {
    public V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewDetached() {
        return view == null;
    }

    public boolean isViewAttached() {
        return view != null;
    }
}
