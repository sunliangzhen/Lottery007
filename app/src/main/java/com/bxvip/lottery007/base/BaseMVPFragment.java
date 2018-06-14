package com.bxvip.lottery007.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwh.jackknife.ioc.SupportActivity;
import com.lwh.jackknife.ioc.SupportFragment;
import com.lwh.jackknife.ioc.ViewInjector;
import com.lwh.jackknife.ioc.bind.BindLayout;
import com.lwh.jackknife.ioc.exception.LackInterfaceException;
import com.lwh.jackknife.ioc.inject.FragmentHandler;
import com.lwh.jackknife.mvp.BasePresenter;
import com.lwh.jackknife.mvp.IBaseView;
import com.lwh.jackknife.util.ToastUtils;

public abstract class BaseMVPFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends Fragment implements SupportFragment {

    protected P mPresenter;
    protected final String TAG = this.getClass().getSimpleName();

    protected abstract P createPresenter();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(this.TAG, "onCreateView()");
        FragmentHandler handler = new FragmentHandler();
        return handler.inflateLayout(new BindLayout(this));
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(this.TAG, "onViewCreated()");
        ViewInjector.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(this.TAG, "onAttach()");
        this.mPresenter = this.createPresenter();
        this.mPresenter.attachView((V) this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(this.TAG, "onDetach()");
        this.mPresenter.detachView();
    }

    protected void toast(String tips) {
        ToastUtils.showShort(getActivity(), tips);
    }

    public SupportActivity getFragmentActivity() {
        if (this.getActivity() instanceof SupportActivity) {
            return (SupportActivity)this.getActivity();
        } else {
            throw new LackInterfaceException("The activity lacks the SupportActivity interface.");
        }
    }

    @Override
    public <VIEW extends View> VIEW findViewById(int id) {
        return getFragmentActivity().findViewById(id);
    }
}
