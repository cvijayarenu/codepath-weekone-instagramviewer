package ru.chand.codepathweekoneinstagramviewer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by chandrav on 2/6/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {


    // View lookup cache
    private static class ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView time;
        ImageView image;
        TextView likes;
        TextView comments;
        TextView content;
    }


    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            TextView tvcaption = (TextView) convertView.findViewById(R.id.tvCaption);
            ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivImage);
            TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            ImageView ivUserPhoto = (ImageView) convertView.findViewById(R.id.ivUserImage);
            TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            TextView tvlikes = (TextView) convertView.findViewById(R.id.tvLike);
            TextView tvComments = (TextView) convertView.findViewById(R.id.tvComments);

            viewHolder.userImage = ivUserPhoto;
            viewHolder.userName = tvUserName;
            viewHolder.time = tvTime;
            viewHolder.image = ivPhoto;
            viewHolder.likes = tvlikes;
            viewHolder.comments = tvComments;
            viewHolder.content = tvcaption;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();


        viewHolder.userImage.setImageResource(0);
        Picasso.with(getContext())
                .load(photo.userProfileImageUrl)
                .fit()
                .transform(transformation)
                .into(viewHolder.userImage);
        viewHolder.userName.setText(photo.username);

        long timevalue = Long.valueOf(photo.timestamp);
        String time = (String) DateUtils.getRelativeTimeSpanString(timevalue * 1000);
        viewHolder.time.setText(time);
        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl)
                .fit()
                .centerInside()
                .into(viewHolder.image);

        viewHolder.likes.setText(Integer.toString(photo.likeCounts) +
                getContext().getResources().getString(R.string.space) +
                getContext().getResources().getString(R.string.likes));

        viewHolder.comments.setText(Integer.toString(photo.commentCounts) +
                getContext().getResources().getString(R.string.space) +
                getContext().getResources().getString(R.string.comments));
        viewHolder.content.setText(photo.caption);

        return convertView;
    }
}
