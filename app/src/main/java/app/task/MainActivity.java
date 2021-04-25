package app.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.Serializable;

import app.task.databinding.ActivityMainBinding;

import static app.task.MainService.equationArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String[] arraySpinner_time = new String[]{"5", "10", "15"};
    String[] arraySpinner_sign = new String[]{"+", "-", "*", "/"};
    String time = "", sign = "", selected_time;
    EquationAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initClicks();
        initSpinner();
        initRv();
    }

    private void initRv() {
        binding.myRv.setHasFixedSize(true);
        binding.myRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter = new EquationAdapter(MainActivity.this, equationArrayList);
        binding.myRv.setAdapter(adapter);
    }

    private void initSpinner() {

        // todo spinner timer
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner_time);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTime.setAdapter(adapter);

        binding.spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GlobalFunctions.showLog("spinnerTime --> " + adapterView.getSelectedItem().toString());
                time = adapterView.getSelectedItem().toString();
                if (time.equals("5")) {
                    selected_time = "5000";
                } else if (time.equals("10")) {
                    selected_time = "10000";
                } else if (time.equals("15")) {
                    selected_time = "15000";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // todo spinner signs
        ArrayAdapter<String> adapter_sign = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner_sign);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSign.setAdapter(adapter_sign);

        binding.spinnerSign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GlobalFunctions.showLog("spinnerSign --> " + adapterView.getSelectedItem().toString());
                sign = adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private boolean isAllFieldValid() {

        boolean result = true;

        if (binding.etFirstNum.getText().toString().length() == 0) {
            GlobalFunctions.showWarningToast(this, getString(R.string.enterFirst));
            return false;
        }

        if (binding.etSecNum.getText().toString().length() == 0) {
            GlobalFunctions.showWarningToast(this, getString(R.string.enterSec));
            return false;
        }

        return result;
    } // validation for fields


    private void initClicks() {
        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAllFieldValid()) {
                    Equation equation = new Equation();
                    setEquObj(equation);
                    Intent intent = new Intent(MainActivity.this, MainService.class);
                    intent.putExtra("Equation", equation);
                    startService(intent);
                }
            }
        });
    }

    // todo set equation object
    private void setEquObj(Equation equation) {
        equation.setFirst_num(Integer.parseInt(binding.etFirstNum.getText().toString().trim()));
        equation.setSec_num(Integer.parseInt(binding.etSecNum.getText().toString().trim()));
        equation.setSign(sign);
        equation.setCompleted(false);
        equation.setTime(Integer.parseInt(selected_time));
    }


    @Override
    public void onResume() {
        super.onResume();
        // Todo This registers messageReceiver to receive messages.
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("my-message"));
    }

    // Todo Handling the received Intents for the "my-integer" event
    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Equation my_result = (Equation) intent.getSerializableExtra("Equation");
            GlobalFunctions.showLog("my_resultTime --> " + my_result.getTime());
            initRv();
        }
    };

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
        super.onPause();
    }

}