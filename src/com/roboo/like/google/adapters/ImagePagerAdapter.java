package com.roboo.like.google.adapters;

import java.util.List;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.roboo.like.google.BaseActivity;
import com.roboo.like.google.BaseLayoutActivity;
import com.roboo.like.google.GoogleApplication;
import com.roboo.like.google.R;
import com.roboo.like.google.utils.BitmapUtils;
import com.roboo.like.google.views.PhotoView;

public class ImagePagerAdapter extends PagerAdapter
{
	private List<String> mImageUrls;
	private BaseActivity baseActivity;
	private ImageLoader mImageLoader;

	public ImagePagerAdapter(BaseActivity context, List<String> mImageUrls)
	{

		this.baseActivity = context;
		this.mImageUrls = mImageUrls;
		mImageLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
		mImageLoader.init(configuration);
	}

	public int getCount()
	{
		return mImageUrls.size();
	}
	public boolean isViewFromObject(View view, Object object)
	{
		return view == object;
	}
 
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView((View) object);
	}
 
	public Object instantiateItem(ViewGroup container, int position)
	{
		PhotoView photoView = new PhotoView(baseActivity);
		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		String mImagePath = mImageUrls.get(position);
		if (isNetworkImg(mImagePath))
		{
			DisplayImageOptions options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY_STRETCHED).showStubImage(R.drawable.ic_default_image).showImageForEmptyUri(R.drawable.ic_default_image).bitmapConfig(Config.RGB_565).build();
			mImageLoader.displayImage(mImagePath, photoView, options);
		}
		else
		{
			photoView.setImageBitmap(BitmapUtils.getBitmap(mImagePath, baseActivity.getResources().getDisplayMetrics().widthPixels, baseActivity.getResources().getDisplayMetrics().heightPixels));
		}
		return photoView;
	}

	private boolean isNetworkImg(String mImagePath)
	{
	 return baseActivity.isImg(mImagePath);
	}
}
