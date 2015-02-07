package ru.chand.codepathweekoneinstagramviewer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvcaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        ImageView ivUserPhoto = (ImageView) convertView.findViewById(R.id.ivUserImage);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvlikes = (TextView) convertView.findViewById(R.id.tvLike);
        TextView tvComments = (TextView) convertView.findViewById(R.id.tvComments);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();


        ivUserPhoto.setImageResource(0);
        Picasso.with(getContext())
                .load(photo.userProfileImageUrl)
                .fit()
                .transform(transformation)
                .into(ivUserPhoto);
        tvUserName.setText(photo.username);
        tvTime.setText(photo.timestamp);

        tvcaption.setText(photo.caption);
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);

        tvlikes.setText(Integer.toString(photo.likeCounts) +
                getContext().getResources().getString(R.string.space) +
                getContext().getResources().getString(R.string.likes));

        tvComments.setText(Integer.toString(photo.commentCounts) +
                getContext().getResources().getString(R.string.space) +
                getContext().getResources().getString(R.string.comments));

        return convertView;
    }
}
