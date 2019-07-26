package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.model.chart.*;
import beans.session.Authorization;
import db.RecordsDb;
import db.entities.Record;

@Named
@RequestScoped
public class DashboardController {
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @EJB
    private RecordsDb db;
    @Inject
    private Authorization auth;
    
    private Record record = new Record();
    private List<Record> records;
    private LineChartModel chart;

    public DashboardController() {
        super();
    }
    
    @PostConstruct
    private void init() {
        update();
    }

    private void update() {
        updateRecords();
        updateChart();
    }

    private void updateRecords() {
        Integer accountId = auth.getAccountId();
        records = db.findByAccountId(accountId);
    }

    private void updateChart() {
        chart = new LineChartModel();
        chart.setLegendPosition("e");
        chart.setZoom(true);
        chart.setShowPointLabels(true);
        
        List<LineChartSeries> seriesSet = createSeriesSet();
        seriesSet.forEach(s -> chart.addSeries(s));
        
        Axis xAxis = new DateAxis();
        xAxis.setTickAngle(-50);
        chart.getAxes().put(AxisType.X, xAxis);
    }
    
    private List<LineChartSeries> createSeriesSet() {
        List<LineChartSeries> seriesSet = new ArrayList<>();

        LineChartSeries minBpSeries = new LineChartSeries("最低血圧");
        seriesSet.add(minBpSeries);
        LineChartSeries maxBpSeries = new LineChartSeries("最高血圧");
        seriesSet.add(maxBpSeries);
        LineChartSeries heartRateSeries = new LineChartSeries("心拍数");
        seriesSet.add(heartRateSeries);

        for (Record r : records) {
            Date date = r.getDate();
            String dateString = DATE_FORMAT.format(date);
            int minBp = r.getMinBp();
            minBpSeries.set(dateString, minBp);
            int maxBp = r.getMaxBp();
            maxBpSeries.set(dateString, maxBp);
            int heartRate = r.getHeartRate();
            heartRateSeries.set(dateString, heartRate);
        }

        return seriesSet;
    }

    public String register() {
        Integer accountId = auth.getAccountId();
        record.setAccountId(accountId);
        db.create(record);
        record = new Record();
        update();
        return null;
    }

//    public String buildCsv() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("日付,最高血圧,最低血圧,心拍数,備考\n");
//        records.forEach(r -> sb.append(r.toCsv()).append("\n"));
//        return sb.toString();
//    }

    public List<Record> getRecords() {
        return records;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public LineChartModel getChart() {
        return chart;
    }

    public boolean getShowChart() {
        return records.size() > 0;
    }
}
