package com.droiduino.bluetoothconn;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovingChart {

    private LineChart chart;
    private LineDataSet dataSet;
    private LineData lineData;
    private Float[] x_vals;
    private Float[] y_vals;

    MovingChart(LineChart chart, Float[] x_vals, Float[] y_vals) {

        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < x_vals.length; i++)
            entries.add(new Entry(x_vals[i], y_vals[i]));

        this.x_vals = x_vals;
        this.y_vals = y_vals;

        this.chart = chart;

        dataSet = new LineDataSet(entries, "Label");
        lineData = new LineData(dataSet);
        this.chart.setData(lineData);
        this.chart.invalidate(); // refresh
    }


    public void updateChart(float val) {

        dataSet.removeFirst();
        for (Entry entry : dataSet.getValues())
            entry.setX(entry.getX() - 1);

        dataSet.addEntry(new Entry(dataSet.getEntryCount(), val));
        lineData.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.invalidate();
    }
}
