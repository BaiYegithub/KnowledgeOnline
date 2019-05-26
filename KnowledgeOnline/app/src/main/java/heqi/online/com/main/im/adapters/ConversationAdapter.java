package heqi.online.com.main.im.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupBaseInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;

import java.util.List;

import heqi.online.com.R;
import heqi.online.com.main.im.model.Conversation;
import heqi.online.com.utils.DateUtil;


/**
 * 会话界面adapter
 */
public class ConversationAdapter extends ArrayAdapter<Conversation> {

    private int resourceId;
    private View view;
    private ViewHolder viewHolder;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ConversationAdapter(Context context, int resource, List<Conversation> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
            //  viewHolder.avatar = view.findViewById(R.id.avatar);
            viewHolder.tv_ahead = view.findViewById(R.id.tv_aheadname_item_conversation);
            viewHolder.lastMessage = (TextView) view.findViewById(R.id.last_message);
            viewHolder.time = (TextView) view.findViewById(R.id.message_time);
            viewHolder.unread = (TextView) view.findViewById(R.id.unread_num);
            view.setTag(viewHolder);
        }
        final Conversation data = getItem(position);

        //创建回调  为了得到所有已加入的群组
        TIMValueCallBack<List<TIMGroupBaseInfo>> cb = new TIMValueCallBack<List<TIMGroupBaseInfo>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.e("converada", "get gruop list failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMGroupBaseInfo> timGroupInfos) {//参数返回各群组基本信息

                Log.d("converada", "get gruop list succ");

                for (TIMGroupBaseInfo info : timGroupInfos) {
                    Log.i("converada", "group id: " + info.getGroupId() +
                            " group name: " + info.getGroupName() +
                            " group faceurl: " + info.getFaceUrl() +
                            " group type: " + info.getGroupType());

                    if (data.getIdentify().equals(info.getGroupId())) {
                        viewHolder.tvName.setText(info.getGroupName());
                        //viewHolder.avatar.setImageResource(info.getFaceUrl());
                        //   viewHolder.avatar.setImageURI(Uri.parse(info.getFaceUrl()));
                        viewHolder.tv_ahead.setText(info.getFaceUrl());
                    }


                }
            }
        };

        //获取已加入的群组列表
        TIMGroupManagerExt.getInstance().getGroupList(cb);

        //  viewHolder.tvName.setText(data.getName());
        // viewHolder.avatar.setImageURI(Uri.parse(data.getAvatar()));

        viewHolder.lastMessage.setText(data.getLastMessageSummary());
        viewHolder.time.setText(DateUtil.getTimeStr(data.getLastMessageTime()));
        long unRead = data.getUnreadNum();
        if (unRead <= 0) {
            viewHolder.unread.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.unread.setVisibility(View.VISIBLE);
            String unReadStr = String.valueOf(unRead);
            if (unRead < 10) {
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point1));
            } else {
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                if (unRead > 99) {
                    unReadStr = getContext().getResources().getString(R.string.time_more);
                }
            }
            viewHolder.unread.setText(unReadStr);
        }
        return view;
    }

    public class ViewHolder {
        public TextView tvName;
        public TextView lastMessage;
        public TextView time;
        public TextView unread;
        public TextView tv_ahead;

    }
}
