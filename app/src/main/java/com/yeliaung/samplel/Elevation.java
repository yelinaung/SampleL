package com.yeliaung.samplel;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Elevation extends Activity {

  private Outline mOutlineCircle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_elevation);

    final ImageButton imageButton = (ImageButton) findViewById(R.id.elevation_btn);

    int shapeSize = getResources().getDimensionPixelSize(R.dimen.shape_size);
    mOutlineCircle = new Outline();
    mOutlineCircle.setRoundRect(0, 0, shapeSize, shapeSize, shapeSize / 2);

    imageButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(Elevation.this, "I am clicked", Toast.LENGTH_SHORT).show();
        imageButton.setElevation(64);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.elevation, menu);
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
