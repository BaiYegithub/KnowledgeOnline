package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.base.MyApplication;
import heqi.online.com.login.LoginActivity;
import heqi.online.com.main.activity.ChangeActivity;
import heqi.online.com.main.activity.CourseListActivity;
import heqi.online.com.main.activity.GroupActivity;
import heqi.online.com.main.activity.HeadImgSetActivity;
import heqi.online.com.main.activity.MyCollectActivity;
import heqi.online.com.main.inter.IUpdate;
import heqi.online.com.main.presenter.UpdatePresenter;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.GlideUtil;
import heqi.online.com.utils.SharedPreferenceUtils;
import heqi.online.com.view.BottomDialog;
import heqi.online.com.view.CircleImageView;

/**
 * Created by Administrator on 2019/4/6.
 */

public class MyselfFragment extends BaseFragment implements IUpdate {
    //头像
    @BindView(R.id.iv_head_fragMine)
    CircleImageView ivHeadFragMine;
    //用户名
    @BindView(R.id.tv_name_fragMine)
    TextView tvNameFragMine;
    //我的收藏
    @BindView(R.id.rlv_collect_fragMine)
    RelativeLayout rlvCollectFragMine;
    //我的手机号
    @BindView(R.id.tv_myPhoneNum_fragMine)
    TextView tvMyPhoneNumFragMine;
    //我的邮箱
    @BindView(R.id.tv_email_fragMine)
    TextView tvEmailFragMine;
    //我的购物车
    @BindView(R.id.tv_shopCart_fragMine)
    TextView tvShopCartFragMine;
    //设置
    @BindView(R.id.tv_set_fragMine)
    TextView tvSetFragMine;
    //推荐课程
    @BindView(R.id.tv_course_fragMine)
    TextView tvCourse;

    @BindView(R.id.tv_group_fragMine)
    TextView tvGroup;
    private BottomDialog bottomDialog;
    private UpdatePresenter updatePresenter;

    private String sex;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_myself;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        updatePresenter = new UpdatePresenter(this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String s = (String) SharedPreferenceUtils.get(ConstantUtil.UserPicture, "");
        GlideUtil.setImageTiling(ivHeadFragMine, (String) SharedPreferenceUtils.get(ConstantUtil.UserPicture, ""), R.drawable.headimg_default);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @OnClick({R.id.iv_head_fragMine, R.id.rlv_collect_fragMine,
            R.id.tv_myPhoneNum_fragMine, R.id.tv_email_fragMine,
            R.id.tv_shopCart_fragMine, R.id.tv_set_fragMine,
            R.id.tv_gender_fragMine, R.id.tv_course_fragMine,
            R.id.tv_group_fragMine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_fragMine:
                openActivity(HeadImgSetActivity.class);
                break;
            case R.id.rlv_collect_fragMine:
                openActivity(MyCollectActivity.class);
                break;
            //昵称
            case R.id.tv_myPhoneNum_fragMine:
                ChangeActivity.navToChangeActivity(getActivity(), ConstantUtil.NickName, (String) SharedPreferenceUtils.get(ConstantUtil.NickName, ""));
                break;
            //年龄
            case R.id.tv_email_fragMine:
                ChangeActivity.navToChangeActivity(getActivity(), ConstantUtil.Age, (int) SharedPreferenceUtils.get(ConstantUtil.Age, 0) + "");
                break;
            case R.id.tv_shopCart_fragMine:

                break;
            //退出登录
            case R.id.tv_set_fragMine:
                SharedPreferenceUtils.clear();
                MyApplication.getInstance().removeAllActivity();
                openActivity(LoginActivity.class);
                break;
            //性别
            case R.id.tv_gender_fragMine:
                if (bottomDialog == null) {
                    bottomDialog = new BottomDialog(getActivity());
                }
                bottomDialog.show();
                bottomDialog.SetOnTextClickListener(new BottomDialog.OnTextClickListener() {
                    @Override
                    public void onOneTextClickListener() {
                        sex = "男";
                        updatePresenter.update((String) SharedPreferenceUtils.get(ConstantUtil.NickName, ""), sex, (int) SharedPreferenceUtils.get(ConstantUtil.Age, 0));
                    }

                    @Override
                    public void onTwoTextClickListener() {
                        sex = "女";
                        updatePresenter.update((String) SharedPreferenceUtils.get(ConstantUtil.NickName, ""), sex, (int) SharedPreferenceUtils.get(ConstantUtil.Age, 0));
                    }
                });
                break;
            case R.id.tv_course_fragMine:
                openActivity(CourseListActivity.class);
                break;
            case R.id.tv_group_fragMine:
                openActivity(GroupActivity.class);
                break;
        }
    }

    @Override
    public void UpdateSuccess() {
        showToast("修改成功");
        SharedPreferenceUtils.put(ConstantUtil.Sex, sex);
        bottomDialog.dismiss();
    }
}
