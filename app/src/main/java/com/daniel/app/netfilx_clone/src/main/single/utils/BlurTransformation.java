package com.daniel.app.netfilx_clone.src.main.single.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class BlurTransformation extends BitmapTransformation {

    private static final int VERSION = 1;

    private static final String ID = "blurTransformation."+VERSION;



    private static int MAX_RADIUS = 25;

    private static int DEFAULT_DOWN_SAMPLING = 1;



    private Context ctx;



    private int mRadius;

    private int mSampling;



    public BlurTransformation(Context ctx) {

        this(ctx, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);

    }

    public BlurTransformation(Context ctx, int radius){

        this(ctx, radius, DEFAULT_DOWN_SAMPLING);

    }

    public BlurTransformation(Context ctx, int radius, int sampling){

        this.ctx = ctx.getApplicationContext();

        this.mRadius = Math.max(1, Math.min(radius, MAX_RADIUS));

        this.mSampling = sampling;

    }



    private Bitmap getBlurBitmap(Context ctx, Bitmap resource, int radius) {

        RenderScript renderScript = null;

        try{

            renderScript = RenderScript.create(ctx);

            Allocation input = Allocation.createFromBitmap(renderScript, resource, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);

            Allocation output = Allocation.createTyped(renderScript, input.getType());

            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));

            blur.setInput(input);

            blur.setRadius(radius);

            blur.forEach(output);

            output.copyTo(resource);

        } finally {

            if(renderScript != null){

                renderScript.destroy();

            }

        }



        return resource;

    }



    @Override

    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        int scaledWidth = toTransform.getWidth() / mSampling;

        int scaleHeight = toTransform.getHeight() / mSampling;



        Bitmap bitmap = pool.get(scaledWidth, scaleHeight, Bitmap.Config.ARGB_8888);



        Canvas canvas = new Canvas(bitmap);

        canvas.scale(1f / (float) mSampling, 1f / (float) mSampling);

        Paint paint = new Paint();

        paint.setFlags(Paint.FILTER_BITMAP_FLAG);

        canvas.drawBitmap(toTransform, 0, 0, paint);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){

            try{

                bitmap = getBlurBitmap(ctx, toTransform, mRadius);

            }catch(RSRuntimeException e){

                e.printStackTrace();;

            }

        }



        return bitmap;

    }



    @Override

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        messageDigest.update((ID+mRadius+mSampling).getBytes());

    }



    @Override

    public String toString() {

        return "BlurTransformation(radius="+mRadius+", sampling="+mSampling+")";

    }



    @Override

    public boolean equals(Object obj) {

        return obj instanceof BlurTransformation

                && ((BlurTransformation) obj).mRadius == mRadius

                && ((BlurTransformation) obj).mSampling == mSampling;

    }

}