package app.task;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.ArrayList;

public class MainService extends Service {

    Handler mHandler;
    int time = 0;
    Equation intent_result;
    public static ArrayList<Equation> equationArrayList = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            mHandler = new Handler();
            startTimer(intent);
        }
        return START_STICKY;
    }

    private void startTimer(Intent intent) {
        intent_result = (Equation) intent.getSerializableExtra("Equation");
        equationArrayList.add(intent_result);
        time = intent_result.getTime();

        GlobalFunctions.showLog("intent_result time  --> " + intent_result.getTime());
        addMethod(intent.getSerializableExtra("Equation"));

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent_result.setCompleted(true);
                handleEquation(intent_result);
                addMethod(intent.getSerializableExtra("Equation"));
            }
        }, time);
    }

    private void handleEquation(Equation intent_result) {
        if (intent_result.getSign().equals("+")) {
            int x = intent_result.getFirst_num();
            int y = intent_result.getSec_num();
            intent_result.setResult(x + y);
        } else if (intent_result.getSign().equals("-")) {
            int x = intent_result.getFirst_num();
            int y = intent_result.getSec_num();
            intent_result.setResult(x - y);
        } else if (intent_result.getSign().equals("*")) {
            int x = intent_result.getFirst_num();
            int y = intent_result.getSec_num();
            intent_result.setResult(x * y);
        } else if (intent_result.getSign().equals("/")) {
            int x = intent_result.getFirst_num();
            int y = intent_result.getSec_num();
            intent_result.setResult(x / y);
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void addMethod(Serializable equation) {
        Intent intent = new Intent("my-message");
        intent.putExtra("Equation", equation);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        GlobalFunctions.showLog("Value --> " + (equation));
    }


}
