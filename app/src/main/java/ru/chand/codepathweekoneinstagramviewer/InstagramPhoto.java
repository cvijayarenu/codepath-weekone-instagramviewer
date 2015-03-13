package ru.chand.codepathweekoneinstagramviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandrav on 2/6/15.
 */
public class InstagramPhoto {
    public String id;
    public String username;
    public String userProfileImageUrl;
    public String caption;
    public String imageUrl;
    public int imageHeight;
    public int likeCounts;
    public int commentCounts;
    public String timestamp;

    public ArrayList<InstagramPhotoComments> comments;
}


