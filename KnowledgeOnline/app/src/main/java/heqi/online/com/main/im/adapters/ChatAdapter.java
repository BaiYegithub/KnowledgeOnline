package heqi.online.com.main.im.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import heqi.online.com.R;
import heqi.online.com.main.im.model.Message;


/**
 * 聊天界面adapter
 */
public class ChatAdapter extends ArrayAdapter<Message> {

    private final String TAG = "ChatAdapter";

    private int resourceId;
    private View view;
    private ViewHolder viewHolder;
    private String GroupId;

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return view != null ? view.getId() : position;
    }

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ChatAdapter(Context context, int resource, List<Message> objects, String groupid) {
        super(context, resource, objects);
        resourceId = resource;
        GroupId = groupid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.leftMessage = (RelativeLayout) view.findViewById(R.id.leftMessage);
            viewHolder.rightMessage = (RelativeLayout) view.findViewById(R.id.rightMessage);
            viewHolder.leftPanel = (RelativeLayout) view.findViewById(R.id.leftPanel);
            viewHolder.rightPanel = (RelativeLayout) view.findViewById(R.id.rightPanel);
            viewHolder.sending = (ProgressBar) view.findViewById(R.id.sending);
            viewHolder.error = (ImageView) view.findViewById(R.id.sendError);
            viewHolder.sender = (TextView) view.findViewById(R.id.sender);
            viewHolder.rightDesc = (TextView) view.findViewById(R.id.rightDesc);
            viewHolder.systemMessage = (TextView) view.findViewById(R.id.systemMessage);

            viewHolder.sdvleft = view.findViewById(R.id.leftAvatar);
            viewHolder.sdvright = view.findViewById(R.id.rightAvatar);
            view.setTag(viewHolder);
        }
        if (position < getCount()) {
            final Message data = getItem(position);
            data.showMessage(viewHolder, getContext());


           /* viewHolder.sdvleft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //请求用户详细信息
                    TIMUserProfile profile = data.getMessage().getSenderProfile();
                    TIMGroupMemberInfo groupMemberInfo = data.getMessage().getSenderGroupMemberProfile();

                    if (profile != null && groupMemberInfo != null) {
                        if (groupMemberInfo != null && groupMemberInfo.getRole() == TIMGroupMemberRoleType.Normal) {
                            //是班级学生的

                            *//*Map<String, Object> studentmap = new HashMap<>();
                            studentmap.put("studentId", profile.getIdentifier());
                            //  studentmap.put("studentId", "1805141152530010010000000");
                            String aesparams = MapToStraes.getAesparams(studentmap, getContext());
                            new DetailPresenter(new BaseView() {
                                @Override
                                public void getdata(Basebean basebean) {
                                    if (basebean.getCode().equals("000000")) {
                                        String data1 = basebean.getData();
                                        try {
                                            String aesoverdata = AesUtil.decryptAESnotneedkey(getContext(), data1);
                                            //  android.util.Log.i("xxx",aesoverdata);
                                            DetailBean detailBean = GsonFormat.getobject(aesoverdata, DetailBean.class);
                                            if (detailBean != null && !TextUtils.isEmpty(detailBean.getFullName())) {
                                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                                intent.putExtra("detailBean", (Serializable) detailBean);
                                                getContext().startActivity(intent);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).relevancegetstudentmessage(aesparams, GetSharedpreferenceuId.getsharedpreferenceuId(getContext()));*//*
                     *//*   } else {
                            //是老师的
                            Map<String, Object> teachermap = new HashMap<>();
                            teachermap.put("teacherId", profile.getIdentifier());
                            //  studentmap.put("studentId", "1805141152530010010000000");
                            String aesparams = MapToStraes.getAesparams(teachermap, getContext());
                            new DetailPresenter(new BaseView() {
                                @Override
                                public void getdata(Basebean basebean) {
                                    if (basebean.getCode().equals("000000")) {
                                        String data1 = basebean.getData();
                                        try {
                                            String aesoverdata = AesUtil.decryptAESnotneedkey(getContext(), data1);
                                            //  android.util.Log.i("xxx",aesoverdata);
                                            DetailBean detailBean = GsonFormat.getobject(aesoverdata, DetailBean.class);
                                            if (detailBean != null && !TextUtils.isEmpty(detailBean.getFullName())) {
                                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                                intent.putExtra("detailBean", (Serializable) detailBean);
                                                intent.putExtra("isteacher", true);
                                                getContext().startActivity(intent);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).relGetTeacherMessage(aesparams, GetSharedpreferenceuId.getsharedpreferenceuId(getContext()));*//*
                        }

                    }
                }
            });*/

            //String peer = data.getMessage().getConversation().getPeer();

       /*     String sender = data.getSender();

            List<String> identifiers = new ArrayList<>();
            identifiers.add(sender);*/

         /*   //获取用户资料
            String sender1 = data.getMessage().getSender();

            List<String> users = new ArrayList<String>();
            users.add(sender1);

            //获取用户资料
            TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
                @Override
                public void onError(int code, String desc) {
                    //错误码 code 和错误描述 desc，可用于定位请求失败原因
                    //错误码 code 列表请参见错误码表
                    Log.e(tag, "getUsersProfile failed: " + code + " desc");
                }

                @Override
                public void onSuccess(List<TIMUserProfile> result) {
                    Log.e(tag, "getUsersProfile succ");
                    for (final TIMUserProfile res : result) {
                        Log.e(tag, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                                + " remark: " + res.getRemark());
                        viewHolder.sdvleft.setImageURI(Uri.parse(res.getFaceUrl()));
                        viewHolder.sender.setText(res.getNickName());
                        viewHolder.sdvleft.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<String> identifiers = new ArrayList<String>();
                                identifiers.add(res.getIdentifier());
                                TIMGroupManagerExt.getInstance().getGroupMembersInfo(GroupId, identifiers, new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
                                    @Override
                                    public void onError(int i, String s) {

                                    }

                                    @Override
                                    public void onSuccess(List<TIMGroupMemberInfo> timGroupMemberInfos) {
                                        // Log.i("xxx", timGroupMemberInfos.toString());
                                        if (timGroupMemberInfos != null && timGroupMemberInfos.get(0).getRole() == TIMGroupMemberRoleType.Normal) {
                                            //是班级学生的

                                            Map<String, Object> studentmap = new HashMap<>();
                                            studentmap.put("studentId", res.getIdentifier());
                                            //  studentmap.put("studentId", "1805141152530010010000000");
                                            String aesparams = MapToStraes.getAesparams(studentmap, getContext());
                                            new DetailPresenter(new BaseView() {
                                                @Override
                                                public void getdata(Basebean basebean) {
                                                    if (basebean.getCode().equals("000000")) {
                                                        String data1 = basebean.getData();
                                                        try {
                                                            String aesoverdata = AesUtil.decryptAESnotneedkey(getContext(), data1);
                                                            //  android.util.Log.i("xxx",aesoverdata);
                                                            DetailBean detailBean = GsonFormat.getobject(aesoverdata, DetailBean.class);
                                                            if (detailBean != null && !TextUtils.isEmpty(detailBean.getFullName())) {
                                                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                                                intent.putExtra("detailBean", (Serializable) detailBean);
                                                                getContext().startActivity(intent);
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }).relevancegetstudentmessage(aesparams, GetSharedpreferenceuId.getsharedpreferenceuId(getContext()));
                                        } else {
                                            //是老师的
                                            Map<String, Object> teachermap = new HashMap<>();
                                            teachermap.put("teacherId", res.getIdentifier());
                                            //  studentmap.put("studentId", "1805141152530010010000000");
                                            String aesparams = MapToStraes.getAesparams(teachermap, getContext());
                                            new DetailPresenter(new BaseView() {
                                                @Override
                                                public void getdata(Basebean basebean) {
                                                    if (basebean.getCode().equals("000000")) {
                                                        String data1 = basebean.getData();
                                                        try {
                                                            String aesoverdata = AesUtil.decryptAESnotneedkey(getContext(), data1);
                                                            //  android.util.Log.i("xxx",aesoverdata);
                                                            DetailBean detailBean = GsonFormat.getobject(aesoverdata, DetailBean.class);
                                                            if (detailBean != null && !TextUtils.isEmpty(detailBean.getFullName())) {
                                                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                                                intent.putExtra("detailBean", (Serializable) detailBean);
                                                                intent.putExtra("isteacher", true);
                                                                getContext().startActivity(intent);
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }).relGetTeacherMessage(aesparams, GetSharedpreferenceuId.getsharedpreferenceuId(getContext()));
                                        }
                                    }
                                });


                            }
                        });
                    }
                }
            }); */

          /*  //获取自己的资料
            TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
                @Override
                public void onError(int code, String desc) {
                    //错误码 code 和错误描述 desc，可用于定位请求失败原因
                    //错误码 code 列表请参见错误码表
                    Log.e("txy", "getSelfProfile failed: " + code + " desc");
                }

                @Override
                public void onSuccess(TIMUserProfile result) {
                    Log.e("txy", "getSelfProfile succ");
                    Log.e("txy", "identifier: " + result.getIdentifier() + " nickName: " + result.getNickName()
                            + " remark: " + result.getRemark() + " allow: " + result.getAllowType());

                    String faceUrl = result.getFaceUrl();
                    //设置自己的头像
                    viewHolder.sdvright.setImageURI(Uri.parse(faceUrl));*/
          /*  viewHolder.sdvright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MinePresenter minePresenter = new MinePresenter(new BaseView() {
                        @Override
                        public void getdata(Basebean basebean) {
                            if (basebean.getCode().equals("000000")) {
                                String data = basebean.getData();
                                if (basebean.getDataType() == 1) {
                                    try {
                                        String aesoverdata = AesUtil.decryptAESnotneedkey(getContext(), data);
                                        TeacherInfoBean teacherInfoBean = GsonFormat.getobject(aesoverdata,
                                                TeacherInfoBean.class);
                                        Intent intent = new Intent(getContext(), PersonalDataActivity.class);
                                        intent.putExtra("teacherInfoBean", (Serializable) teacherInfoBean);
                                        getContext().startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            } else {

                            }
                        }
                    });


                    minePresenter.relevance(GetSharedpreferenceuId.getsharedpreferenceuId(getContext()));
                }
            });*/

            // }
           /* });*/

          /*//*  //创建回调
            TIMValueCallBack<List<TIMGroupMemberInfo>> cb = new TIMValueCallBack<List<TIMGroupMemberInfo>> () {
                @Override
                public void onError(int code, String desc) {
                }

                @Override
                public void onSuccess(List<TIMGroupMemberInfo> infoList) {//参数返回群组成员信息

                    for(TIMGroupMemberInfo info : infoList) {
                        Log.d(tag, "user: " + info.getUser() +
                                "join time: " + info.getJoinTime() +
                                "role: " + info.getRole());
                       // info.getCustomInfo().get()
                    }
                }
            };

//获取群组成员信息
            TIMGroupManagerExt.getInstance().getGroupMembers(
                    GroupId, //群组 ID
                    cb);     //回调*//*

            //  viewHolder.sdvleft.setImageURI(data.getMessage().getSender());*/
        }


        return view;
    }


    public class ViewHolder {
        public RelativeLayout leftMessage;
        public RelativeLayout rightMessage;
        public RelativeLayout leftPanel;
        public RelativeLayout rightPanel;
        public ProgressBar sending;
        public ImageView error;
        public TextView sender;
        public TextView systemMessage;
        public TextView rightDesc;
        public ImageView sdvleft;
        public ImageView sdvright;
    }
}
