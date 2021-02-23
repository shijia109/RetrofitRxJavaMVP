package com.sj.mvprxjavaretrofit.contract.buy.contract;

/**
 * Created by heiyan on 2017/9/14.
 */

public interface LoginContract {
    interface Model {
        int a = 2;
        void login(String username, String password);
    }

    interface View {
    }

    interface Presenter {
    }
}
