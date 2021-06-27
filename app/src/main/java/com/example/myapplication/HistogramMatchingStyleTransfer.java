culpackage com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class HistogramMatchingStyleTransfer {

    private final Bitmap sourceImage, targetImage;
    private final Context applicationContext;

    HistogramMatchingStyleTransfer(Context applicationContext, Bitmap sourceImage, Bitmap targetImage) {
        this.sourceImage = sourceImage;
        this.targetImage = targetImage;
        this.applicationContext = applicationContext;
    }

    Bitmap styleImage() {

        // save images to disk
        saveBitmap(applicationContext, sourceImage, "sourceImage.jpg");
        saveBitmap(applicationContext, targetImage, "targetImage.jph");

        Intent intent = new Intent(applicationContext, HistogramMatchingStyleTransferJobIntentService.class);
        HistogramMatchingStyleTransferJobIntentService service = new HistogramMatchingStyleTransferJobIntentService();
        service.enqueueWork(applicationContext, intent);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                      
            }
        };
    }

    private void saveBitmap (Context context, Bitmap bitmap, String filename) {

        try {
            //Write file
            FileOutputStream stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            //Cleanup
            stream.close();
            bitmap.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
