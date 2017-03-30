package net.derohimat.baseapp.presenter;

import net.derohimat.baseapp.view.BaseView;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}