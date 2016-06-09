package com.xomorod.location.business;

import jp.satorufujiwara.binder.ViewType;

/**
 * Created by 890683 on 5/25/2016.
 */
public enum  BinderViewType implements ViewType {

    BASE_TYPE;

    @Override
    public int viewType() {
        return ordinal();
    }
}
