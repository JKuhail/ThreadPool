package com.jkuhail.threadpool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * This is just a template of the ideal way of using Thread pool.
 *
 * You will notice that there's no actual data. all the data here are just an example.
 * Feel free to change whatever you want to make the template suitable for your app logic.
 *
 */
public class ThreadPoolRunnable implements Runnable {

    private static final String TAG = "ThreadPoolRunnable";

    //TODO: Here we gonna implement the variables that we need to it to be modified.
    private int mFirstNumber;
    private int mSecondNumber;

    //TODO: This object is responsible for sending the message to the mainThread after the work is done
    private WeakReference<Handler> mMainThreadHandler;

    //TODO: Constructor that takes all the needed parameters
    public ThreadPoolRunnable(int mFirstNumber, int mSecondNumber, Handler mMainThreadHandler) {
        this.mFirstNumber = mFirstNumber;
        this.mSecondNumber = mSecondNumber;
        this.mMainThreadHandler = new WeakReference<>(mMainThreadHandler);
    }

    //TODO: Implement the code you want to execute in the thread.
    @Override
    public void run() {
        Log.d(TAG, "This is from thread: " + Thread.currentThread().getName());
        int subtotal = mFirstNumber + mSecondNumber;
        //send the data to the main thread
        Message message = Message.obtain(null, 111); // I chose 111 randomly
        Bundle bundle =new Bundle();
        bundle.putInt("subtotal", subtotal);
        message.setData(bundle);
        mMainThreadHandler.get().sendMessage(message);
    }
}
