package app.task;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class GlobalFunctions {


//============================= TODO show log ====================================================================================================

    public static void showLog(String message) {
        Log.d(AppConstants.TAG, message);
    }

//============================= TODO show warning toast ====================================================================================================

    public static void showWarningToast(Context context, String message) {
        Toasty.warning(context, message, Toast.LENGTH_LONG).show();
    }

}
