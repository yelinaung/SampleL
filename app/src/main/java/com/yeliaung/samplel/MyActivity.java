package com.yeliaung.samplel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewAnimationUtils;

public class MyActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction().add(R.id.container, new MyFragment()).commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class MyFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;

    String[] mDummy = new String[] {
        "SHA", "YLA", "YMK", "YMM", "ZY", "HWT", "ARK", "TAL", "NLH", "RV", "TRH", "MTHM", "PG",
        "HN", "THT"
    };

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_my, container, false);

      RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_view);
      mLayoutManager = new LinearLayoutManager(getActivity());
      recyclerView.setLayoutManager(mLayoutManager);

      recyclerView.setAdapter(new MyAdapter(mDummy));

      return rootView;
    }
  }

  private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mData;

    private MyAdapter(String[] mData) {
      this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
      public TextView mTextView;
      public ImageView mRefreshBtn;

      public ViewHolder(View v) {
        super(v);
        mTextView = (TextView) v.findViewById(R.id.txt);
        mRefreshBtn = (ImageButton) v.findViewById(R.id.ref);
      }
    }

    @Override public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      // create a new view
      View v =
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_card, viewGroup, false);
      return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(final MyAdapter.ViewHolder viewHolder, final int i) {
      viewHolder.mTextView.setText(mData[i]);
      viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Log.i("tag", "clicked " + mData[i]);
        }
      });

      // previously visible view
      viewHolder.mRefreshBtn.setVisibility(View.VISIBLE);

      // get the center for the clipping circle
      int cx = (viewHolder.mRefreshBtn.getLeft() + viewHolder.mRefreshBtn.getRight()) / 2;
      int cy = (viewHolder.mRefreshBtn.getTop() + viewHolder.mRefreshBtn.getBottom()) / 2;

      // get the initial radius for the clipping circle
      int initialRadius = viewHolder.mRefreshBtn.getWidth();

      // create the animation (the final radius is zero)
      final ValueAnimator anim =
          ViewAnimationUtils.createCircularReveal(viewHolder.mRefreshBtn, cx, cy, 0, initialRadius);

      // start the animation

      viewHolder.mRefreshBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              super.onAnimationEnd(animation);
              viewHolder.mRefreshBtn.setVisibility(View.INVISIBLE);
            }
          });
        }
      });
    }

    @Override public int getItemCount() {
      return mData.length;
    }
  }
}
