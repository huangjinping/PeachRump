package com.peach.rump.ui.direct.view;

import android.view.View;
import android.widget.Button;

import com.peach.rump.R;
import com.peach.rump.ui.observation.view.CircleBar;
import com.peachrump.comm.base.BaseFragment;

/**
 * author Created by harrishuang on 2017/6/20.
 * email : huangjinping@hdfex.com
 */

public class DirectFragment extends BaseFragment {


    private CircleBar circle_Bar;
    private Button btn_open;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pic;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        circle_Bar = (CircleBar) rootView.findViewById(R.id.circle_Bar);
        btn_open=(Button)rootView.findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle_Bar.setSweepAngle(67);
                circle_Bar.setText("100");
                circle_Bar.setMiniText("10000");
            }
        });
    }
}
