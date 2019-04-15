package heqi.online.com.main.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.utils.LogUtil;
import heqi.online.com.utils.UIUtils;
import heqi.online.com.view.LintDialog;
import jp.wasabeef.richeditor.RichEditor;

public class WriteArticleActivity extends BaseActivity {


    @BindView(R.id.iv_back_titlebar)
    ImageView ivBackTitlebar;
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenterTitlebar;
    @BindView(R.id.tv_right_titlebar)
    TextView tvRightTitlebar;
    @BindView(R.id.et_title_acWrite)
    EditText etTitleAcWrite;
    @BindView(R.id.editor_acWrite)
    RichEditor mEditor;
    @BindView(R.id.tv_preview_acWrite)
    TextView tvPreviewAcWrite;
    @BindView(R.id.action_undo)
    ImageButton actionUndo;
    @BindView(R.id.action_redo)
    ImageButton actionRedo;
    @BindView(R.id.action_bold)
    ImageButton actionBold;
    @BindView(R.id.action_italic)
    ImageButton actionItalic;
    @BindView(R.id.action_subscript)
    ImageButton actionSubscript;
    @BindView(R.id.action_superscript)
    ImageButton actionSuperscript;
    @BindView(R.id.action_strikethrough)
    ImageButton actionStrikethrough;
    @BindView(R.id.action_underline)
    ImageButton actionUnderline;
    @BindView(R.id.action_heading1)
    ImageButton actionHeading1;
    @BindView(R.id.action_heading2)
    ImageButton actionHeading2;
    @BindView(R.id.action_heading3)
    ImageButton actionHeading3;
    @BindView(R.id.action_heading4)
    ImageButton actionHeading4;
    @BindView(R.id.action_heading5)
    ImageButton actionHeading5;
    @BindView(R.id.action_heading6)
    ImageButton actionHeading6;
    @BindView(R.id.action_txt_color)
    ImageButton actionTxtColor;
    @BindView(R.id.action_bg_color)
    ImageButton actionBgColor;
    @BindView(R.id.action_indent)
    ImageButton actionIndent;
    @BindView(R.id.action_outdent)
    ImageButton actionOutdent;
    @BindView(R.id.action_align_left)
    ImageButton actionAlignLeft;
    @BindView(R.id.action_align_center)
    ImageButton actionAlignCenter;
    @BindView(R.id.action_align_right)
    ImageButton actionAlignRight;
    @BindView(R.id.action_insert_bullets)
    ImageButton actionInsertBullets;
    @BindView(R.id.action_insert_numbers)
    ImageButton actionInsertNumbers;
    @BindView(R.id.action_blockquote)
    ImageButton actionBlockquote;
    @BindView(R.id.action_insert_image)
    ImageButton actionInsertImage;
    @BindView(R.id.action_insert_link)
    ImageButton actionInsertLink;
    @BindView(R.id.action_insert_checkbox)
    ImageButton actionInsertCheckbox;

    @BindView(R.id.hs_acWrite)
    HorizontalScrollView hs_acWrite;

    boolean isChanged;
    boolean isBgChanged;
    private LintDialog lintDialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_write_article;
    }

    @Override
    protected void initViewAndData() {

        tvCenterTitlebar.setText("编辑文章");
        tvRightTitlebar.setText("发布");

        mEditor.setEditorFontSize(22);
        //初始化字体颜色
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);

        //初始化内边距
        mEditor.setPadding(10, 10, 10, 10);
        //设置编辑框背景，可以是网络图片
        // mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        // mEditor.setBackgroundColor(Color.BLUE);
        // mEditor.setBackgroundResource(R.drawable.bg);
        //设置默认显示语句
        mEditor.setPlaceholder("请输入正文");
        //设置编辑器是否可用
        mEditor.setInputEnabled(true);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                tvPreviewAcWrite.setText(text);
            }
        });

        mEditor.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候 会调用此方法
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                WriteArticleActivity.this.getWindow().getDecorView()
                        .getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = WriteArticleActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;

                if (UIUtils.checkDeviceHasNavigationBar()) {
                    heightDifference -= UIUtils.getVirtualBarHeigh();
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, heightDifference);

                hs_acWrite.setLayoutParams(layoutParams);
            }
        });

        //当监听改变的时候
        etTitleAcWrite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hs_acWrite.setVisibility(View.GONE);
                } else {
                    hs_acWrite.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void destroyResources() {

    }


    @OnClick({R.id.iv_back_titlebar, R.id.tv_right_titlebar, R.id.action_undo, R.id.action_redo, R.id.action_bold, R.id.action_italic, R.id.action_subscript, R.id
            .action_superscript, R.id.action_strikethrough, R.id.action_underline, R.id.action_heading1, R.id.action_heading2, R.id.action_heading3, R.id.action_heading4, R.id
            .action_heading5, R.id.action_heading6, R.id.action_txt_color, R.id.action_bg_color, R.id.action_indent, R.id.action_outdent, R.id.action_align_left, R.id
            .action_align_center, R.id.action_align_right, R.id.action_insert_bullets, R.id.action_insert_numbers, R.id.action_blockquote, R.id.action_insert_image, R.id
            .action_insert_link, R.id.action_insert_checkbox})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_back_titlebar:
                //BAI:2019/4/15 0015  是否需要提示
                break;
            case R.id.tv_right_titlebar:
                //发布按钮
                String etText = tvPreviewAcWrite.getText().toString();
                LogUtil.i("html", "html 文本是" + etText);
                break;
            case R.id.action_undo:
                //撤回按钮
                mEditor.undo();
                break;
            case R.id.action_redo:
                //取消撤回按钮
                mEditor.redo();
                break;
            case R.id.action_bold:
                mEditor.setBold();
                break;
            case R.id.action_italic:
                mEditor.setItalic();
                break;
            case R.id.action_subscript:
                mEditor.setSubscript();
                break;
            case R.id.action_superscript:
                mEditor.setSuperscript();
                break;
            case R.id.action_strikethrough:
                mEditor.setStrikeThrough();
                break;
            case R.id.action_underline:
                mEditor.setUnderline();
                break;
            case R.id.action_heading1:
                mEditor.setHeading(1);
                break;
            case R.id.action_heading2:
                mEditor.setHeading(2);
                break;
            case R.id.action_heading3:
                mEditor.setHeading(3);
                break;
            case R.id.action_heading4:
                mEditor.setHeading(4);
                break;
            case R.id.action_heading5:
                mEditor.setHeading(5);
                break;
            case R.id.action_heading6:
                mEditor.setHeading(6);
                break;
            case R.id.action_txt_color:

                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
                break;
            case R.id.action_bg_color:
                mEditor.setTextBackgroundColor(isBgChanged ? Color.WHITE : Color.YELLOW);
                isBgChanged = !isBgChanged;
                break;
            case R.id.action_indent:
                mEditor.setIndent();
                break;
            case R.id.action_outdent:
                mEditor.setOutdent();
                break;
            case R.id.action_align_left:
                mEditor.setAlignLeft();
                break;
            case R.id.action_align_center:
                mEditor.setAlignCenter();
                break;
            case R.id.action_align_right:
                mEditor.setAlignRight();
                break;
            case R.id.action_insert_bullets:
                mEditor.setBullets();
                break;
            case R.id.action_insert_numbers:
                mEditor.setNumbers();
                break;
            case R.id.action_blockquote:
                mEditor.setBlockquote();
                break;
            case R.id.action_insert_image:
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                        "dachshund");
                break;
            case R.id.action_insert_link:
                if (lintDialog == null) {
                    lintDialog = new LintDialog(WriteArticleActivity.this);
                    lintDialog.setOnConfirmClick(new LintDialog.OnConfirmClick() {
                        @Override
                        public void onConfirm() {
                            //添加链接的功能
                            mEditor.insertLink(lintDialog.getEtLintAddress(), lintDialog.getEtLintContent());
                            lintDialog.dismiss();
                        }
                    });
                }
                lintDialog.show();

                break;
            case R.id.action_insert_checkbox:
                mEditor.insertTodo();
                break;
        }
    }
}
