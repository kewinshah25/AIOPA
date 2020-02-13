package com.se_proj.aiopa;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

public class finance_stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(this);
        String uname = getSharedPreferences("SP_Uname", 0).getString("uname", "NA");
        Cursor c1 = db.getExpenditure1(uname);
        Cursor c2 = db.getBudget(uname);
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_stats);


        while (c1.moveToNext()) {
            buffer1.append(c1.getString(c1.getColumnIndex("expense")));
        }
        //Toast.makeText(this, "Exp: "+buffer1, Toast.LENGTH_SHORT).show();
        while (c2.moveToNext()) {
            buffer2.append(c2.getString(c2.getColumnIndex("budget")));
        }
        //Toast.makeText(this, "Budget: "+buffer2, Toast.LENGTH_SHORT).show();

        double exp = Double.parseDouble(buffer1.toString());
        double bud = Double.parseDouble(buffer2.toString());
        if(exp>=bud) Toast.makeText(this,"Warning!!! Your expense exceeds your budget.",Toast.LENGTH_LONG).show();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {

                new DataPoint(50, exp)
                //new DataPoint(78, Integer.parseInt((buffer2).toString()))
        });

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(50, bud),

        });

        series.setValuesOnTopSize(50.0f);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"                  Expenditure", "Budget                  "});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);

        series2.setValuesOnTopSize(50.0f);
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.BLACK);


        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10000);

        graph.getViewport().setScrollable(true);
        series.setAnimated(true);
        graph.addSeries(series); graph.addSeries(series2);


        series.setColor(Color.BLUE);
        series2.setColor(Color.RED);

        series.setTitle("Expenditure");
        series2.setTitle("Budget");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


    }
}
