package com.yeliaung.samplel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProminentColor extends Activity {

  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prominent);
    imageView = (ImageView) findViewById(R.id.sample);
    Button getColorBtn = (Button) findViewById(R.id.get_color_btn);
    final View resultView = findViewById(R.id.result_view);

    getColorBtn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Bitmap myBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Palette palette = Palette.generate(myBitmap);
        resultView.setBackgroundColor(palette.getMutedColor().getRgb());
        /*
        Log.d("color", "getVibrantColor " + palette.getVibrantColor().getRgb());
        Log.d("color", "getDarkVibrantColor " + palette.getDarkVibrantColor().getRgb());
        Log.d("color", "getLightVibrantColor " + palette.getLightVibrantColor().getRgb());
        Log.d("color", "getMutedColor " + palette.getMutedColor().getRgb());
        Log.d("color", "getDarkMutedColor " + palette.getDarkMutedColor().getRgb());
        Log.d("color", "getLightMutedColor " + palette.getLightMutedColor().getRgb());
        */
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
