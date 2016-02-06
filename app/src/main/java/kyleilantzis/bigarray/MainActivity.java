package kyleilantzis.bigarray;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private BigArrayHolder mBigArrayHolder;

    private TextView mTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        BigArrayHolder bigArrayHolder = (BigArrayHolder) getLastCustomNonConfigurationInstance();

        if ( bigArrayHolder != null ) {
            Log.d(TAG, "last custom non configuration instance is a BigArrayHolder");
            mBigArrayHolder = bigArrayHolder;
        } else {
            Log.d(TAG, "creating new BigArrayHolder");
            mBigArrayHolder = BigArrayHolder.newInstance();
        }

        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        String text = Integer.toString(mBigArrayHolder.getRandom());

        mTextView.setText(text);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, "onRetainCustomNonConfigurationInstance");
        return mBigArrayHolder;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mBigArrayHolder = null;
        System.gc();
    }

    public static class BigArrayHolder {

        private final int[] mBigArray;

        private final Random mRandom;

        public static BigArrayHolder newInstance() {
            BigArrayHolder bigArrayHolder = new BigArrayHolder();
            bigArrayHolder.randomize();
            return bigArrayHolder;
        }

        private BigArrayHolder() {
            mBigArray = new int[5000000];
            mRandom = new Random();
        }

        public synchronized void randomize() {
            for (int i = 0, len = mBigArray.length; i < len; i++) {
                mBigArray[i] = mRandom.nextInt();
            }
        }

        public synchronized int getRandom() {
            return mBigArray[mRandom.nextInt(mBigArray.length)];
        }
    }
}
