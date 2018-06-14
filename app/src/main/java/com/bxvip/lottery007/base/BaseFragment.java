package com.bxvip.lottery007.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwh.jackknife.ioc.SupportActivity;
import com.lwh.jackknife.ioc.SupportFragment;
import com.lwh.jackknife.ioc.ViewInjector;
import com.lwh.jackknife.ioc.bind.BindLayout;
import com.lwh.jackknife.ioc.exception.LackInterfaceException;
import com.lwh.jackknife.ioc.inject.FragmentHandler;
import com.lwh.jackknife.util.ToastUtils;

/**
 * 所有Fragment的基类。
 */
public abstract class BaseFragment extends Fragment implements SupportFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHandler handler = new FragmentHandler();
        return handler.inflateLayout(new BindLayout(this));
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewInjector.inject(this);
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
