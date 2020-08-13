package com.jkuhail.threadpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * We are going to use the Thread Pool here.
 */

//TODO: Implement 'Handler.Callback' interface.
public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = "MainActivity";

    //TODO: Initialize 'ExecutorService' and 'Handler' objects
    private ExecutorService mExecutorService = null;
    private Handler mMainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Implement 'Handler' object
        mMainThreadHandler = new Handler(this);

        initExecutorThreadPool();
        executeThreadPool();
    }
    //TODO: Here we shutdown the thread pool if the activity stopped.
    @Override
    protected void onStop() {
        super.onStop();
        mExecutorService.shutdownNow(); //shutdown immediately
//        mExecutorService.shutdown(); // finish all tasks then shutdown
    }

    //TODO: This method is responsible for calculating the number of the generated the threads pool
    // based on the number of devices's cores.
    private void initExecutorThreadPool(){
        int cores = Runtime.getRuntime().availableProcessors();
        Log.d(TAG, "initExecutorThreadPool: cores: " + cores);
        mExecutorService = Executors.newFixedThreadPool(cores);
    }

    //TODO: This method is responsible for adding the thread to the thread pool.
    private void executeThreadPool(){
        /*you can use the commented logic in order to perform multiple thread based on the available resources.
            But in owr case, we only need one.
         */
//        int numTasks = Runtime.getRuntime().availableProcessors();

//        for(int i = 0; i < numTasks; i++){
            ThreadPoolRunnable runnable = new ThreadPoolRunnable(
                    1,
                    2,
                    mMainThreadHandler
            );
            mExecutorService.submit(runnable);
//        }
    }

    //TODO: This method is responsible for receiving the data from the other threads.
    @Override
    public boolean handleMessage(@NonNull Message message) {
        switch (message.what){
            case 111:{
                int subtotal =message.getData().getInt(("subtotal")); //subtotal = 3
                Toast.makeText(getApplicationContext(), "The subtotal = " + subtotal, Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}