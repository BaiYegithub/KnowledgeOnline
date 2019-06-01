package heqi.online.com.main.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.inter.IUploadImg;
import heqi.online.com.main.presenter.UploadImgPresenter;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.GlideUtil;
import heqi.online.com.utils.SharedPreferenceUtils;
import heqi.online.com.utils.UIUtils;
/*更改头像的activity*/

public class HeadImgSetActivity extends BaseActivity implements IUploadImg {

    //中间文字
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenter;
    //头像占位图
    @BindView(R.id.iv_preview_acHeadImgSet)
    ImageView ivPreviewAcHeadImgSet;
    //从相册选一张
    @BindView(R.id.tv_album_acHeadImgSet)
    TextView tvAlbumAcHeadImgSet;
    //拍一张照片
    @BindView(R.id.tv_take_photo_acHeadImgSet)
    TextView tvTakePhotoAcHeadImgSet;

    private File photoFile;
    private Uri photoUri;

    private int TAKE_PHOTO = 5;  //startActivityForResult 的请求码和返回码
    private int PICK_PHOTO = 10;
    private Uri pickPhotoUri;
    private UploadImgPresenter uploadImgPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_head_img_set;
    }

    @Override
    protected void initViewAndData() {
        tvCenter.setText("设置个人头像");
        GlideUtil.setImageTiling(ivPreviewAcHeadImgSet, (String) SharedPreferenceUtils.get(ConstantUtil.UserPicture, ""), R.drawable.headimg_default);
    }

    @Override
    protected void initHttp() {
        uploadImgPresenter = new UploadImgPresenter(this, this);
    }

    @Override
    protected void destroyResources() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.iv_back_titlebar,R.id.tv_album_acHeadImgSet, R.id.tv_take_photo_acHeadImgSet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_titlebar:
                finish();
                break;
            case R.id.tv_album_acHeadImgSet:
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                if (checkPermissions(permissions)) { //判断读取权限
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, PICK_PHOTO);
                } else {
                    requestPermission(permissions, 1);
                }
                break;
            case R.id.tv_take_photo_acHeadImgSet:
                String[] permissions2 = {Manifest.permission.CAMERA};
                if (checkPermissions(permissions2)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    photoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/test/" + System.currentTimeMillis() + ".jpg");
                    photoFile.getParentFile().mkdirs();

                    photoUri = FileProvider.getUriForFile(HeadImgSetActivity.this, "heqi.online.com.fileprovider", photoFile);
                    //动态添加权限
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                } else {
                    requestPermission(permissions2, 1);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_PHOTO) {
                //相册返回数据
                pickPhotoUri = data.getData();
                uploadImgPresenter.uploadImg(UIUtils.getRealFilePath(HeadImgSetActivity.this, pickPhotoUri), UIUtils.getUid());
            } else if (requestCode == TAKE_PHOTO) {
                //照片返回数据
                uploadImgPresenter.uploadImg(photoFile.getAbsolutePath(), UIUtils.getUid());
            }
        }
    }


    @Override
    public void uploadSuccess(String url) {
        //设置成功之后的回调
        if (pickPhotoUri != null) {  //设置本地uri 给图片 加载速度快 ，加载相册图片
            GlideUtil.setImageUriTiling(ivPreviewAcHeadImgSet, pickPhotoUri, R.drawable.headimg_default);
        }
        if (photoUri != null) { //相机照照片
            GlideUtil.setImageUriTiling(ivPreviewAcHeadImgSet, photoUri, R.drawable.headimg_default);
        }

        SharedPreferenceUtils.put(ConstantUtil.UserPicture, url);
        GlideUtil.setImageTiling(ivPreviewAcHeadImgSet, url, R.drawable.headimg_default);
    }
}
