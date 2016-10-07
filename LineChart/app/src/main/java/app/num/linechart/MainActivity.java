package app.num.linechart;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sampleJsonData = "{\n" +
                "\t\"data\": [{\n" +
                "\t\t\"entry\": \"4\",\n" +
                "\t\t\"month\": \"Jan\"\n" +
                "\n" +
                "\t}, {\n" +
                "\t\t\"entry\": \"15\",\n" +
                "\t\t\"month\": \"Feb\"\n" +
                "\n" +
                "\t}, {\n" +
                "\t\t\"entry\": \"6\",\n" +
                "\t\t\"month\": \"Mar\"\n" +
                "\n" +
                "\t}, {\n" +
                "\t\t\"entry\": \"2\",\n" +
                "\t\t\"month\": \"Apr\"\n" +
                "\n" +
                "\t}, {\n" +
                "\t\t\"entry\": \"18\",\n" +
                "\t\t\"month\": \"May\"\n" +
                "\n" +
                "\t}, {\n" +
                "\t\t\"entry\": \"7\",\n" +
                "\t\t\"month\": \"June\"\n" +
                "\t}]\n" +
                "}";


        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        // Get arraylists to store the JSON data
        ArrayList<Entry> entries = new ArrayList<>();

        ArrayList<String> monthLabel = new ArrayList<String>();

        LineDataSet dataSet = new LineDataSet(entries, "Data Values");

        //Parse the Sample JSON
        try {
            JSONObject jsonObj = new JSONObject(sampleJsonData);
            JSONArray data= jsonObj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                String entry = c.getString("entry");
                String month = c.getString("month");
                entries.add(new Entry(Float.parseFloat(entry),i));
                monthLabel.add(month);

            }
        }
            catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }



        //Plot the data
        LineData data = new LineData(monthLabel, dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawCubic(false);
        dataSet.setDrawFilled(true);
        lineChart.setData(data);


    }
}
