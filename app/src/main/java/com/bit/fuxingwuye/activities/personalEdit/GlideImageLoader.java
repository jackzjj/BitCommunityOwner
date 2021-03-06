package com.bit.fuxingwuye.activities.personalEdit;

import android.app.Activity;
import android.content.Context;

import com.bit.fuxingwuye.R;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

/**
 * GlideImageLoader
 */
public class GlideImageLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
        Glide.with(context)
                .load(path)
                .into(galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}