package com.example.exvignetteeffect;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import java.io.InputStream;

import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "MainActivity";
    ImageViewVignette mImageView;
    SeekBar mSeekBar1;
    SeekBar mSeekBar2;
    Bitmap mOriginaBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageViewVignette) findViewById(R.id.image);

        mSeekBar1 = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        mImageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);

        mSeekBar1.setOnSeekBarChangeListener(this);

        mSeekBar2.setOnSeekBarChangeListener(this);

//      ===============================================================================

        mImageView.setImageBitmap(Splash.bitmap);

        int intensity = mImageView.getVignetteIntensity();

        intensity = (intensity + 100) / 2;
        Log.d(TAG, "intensity: " + intensity);

        mSeekBar1.setProgress(intensity);

        float feather = mImageView.getVignetteFeather();

        mSeekBar2.setProgress((int) (feather * 100));
        // new TestTask().execute();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) return;

        if (seekBar.getId() == mSeekBar1.getId()) {

            int value = progress * 2 - 100;

            mImageView.setVignetteIntensity(value);

        } else {

            mImageView.setVignetteFeather((float) progress / 100);

        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    class TestTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(final String... params) {

           /* try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }*/

            InputStream stream = null;

            Bitmap bitmap = null;

            try {

                // stream = getResources().getAssets().open(params[0]);

                // bitmap = BitmapFactory.decodeStream(stream);

                //stream.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {

            mOriginaBitmap = bitmap;

            mImageView.setImageBitmap(bitmap);

            int intensity = mImageView.getVignetteIntensity();

            intensity = (intensity + 100) / 2;
            Log.d(TAG, "intensity: " + intensity);

            mSeekBar1.setProgress(intensity);

            float feather = mImageView.getVignetteFeather();

            mSeekBar2.setProgress((int) (feather * 100));
        }
    }
}
