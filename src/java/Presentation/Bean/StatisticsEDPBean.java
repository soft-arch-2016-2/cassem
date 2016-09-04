/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleEDP;
import DataAccess.Entity.EmployeeDecreasePart;
import DataAccess.Entity.Part;
import DataAccess.Entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class StatisticsEDPBean implements Serializable {

    private List<EmployeeDecreasePart> edps;
    private List<AmountPart> amounts;
    private Integer top;
    private LineChartModel modelEDP;

    public StatisticsEDPBean() {
        HandleEDP handler = new HandleEDP();
        edps = handler.getAllEDP();
        amounts = getAmountPartList();
    }

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public List<EmployeeDecreasePart> getEdps() {
        return edps;
    }

    public void setEdps(List<EmployeeDecreasePart> edps) {
        this.edps = edps;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public void getAllEDP() {
        HandleEDP handler = new HandleEDP();
        edps = handler.getAllEDP();
    }

    public LineChartModel getModelEDP() {
        return modelEDP;
    }

    public void setModelEDP(LineChartModel modelEDP) {
        this.modelEDP = modelEDP;
    }

    public List<AmountPart> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<AmountPart> amounts) {
        this.amounts = amounts;
    }
    
    private void createAnimatedModels() {
        modelEDP = initLinearModel();
        modelEDP.setTitle("Most part used");
        modelEDP.setAnimate(true);
        modelEDP.setLegendPosition("ne");
        Axis yAxis = modelEDP.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
        yAxis.setMax(10);
        Axis xAxis = modelEDP.getAxis(AxisType.X);
        xAxis.setLabel("Part ID");

    }
    
    private ArrayList<AmountPart> getAmountPartList(){
        int id = 0, index = 0;
        HashMap<Part, Integer> idPart = new HashMap<>();
        ArrayList<AmountPart> parts = new ArrayList<>();

        Part part;
        for (int i = 0; edps != null && i < edps.size(); i++) {
            part = edps.get(i).getPartId();
            if (!idPart.containsKey(part)) {
                idPart.put(part, id++);
                parts.add(new AmountPart(part, 0));
            }
            index = idPart.get(part);
            parts.get(index).setAmount(parts.get(index).getAmount() + 1);
        }
        Collections.sort(parts);
        return parts;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        // Best display
        top = 10;

        Collections.sort(amounts);

        int limit = Math.min(top, amounts.size());

        AmountPart amountPart;

        LineChartSeries series = new LineChartSeries();
        series.setLabel("Parts");

        for (int i = 0; i < limit; i++) {
            amountPart = amounts.get(i);
            series.set(amountPart.getPart().getPartId(), amountPart.getAmount());
        }

        model.addSeries(series);

        return model;
    }

    public static class AmountPart implements Comparable<AmountPart> {

        private Part part;
        private Integer amount;

        public AmountPart(Part part, Integer amount) {
            this.part = part;
            this.amount = amount;
        }

        public Part getPart() {
            return part;
        }

        public void setPart(Part part) {
            this.part = part;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        @Override
        public int compareTo(AmountPart o) {
            if (amount == o.amount) {
                return part.getName().compareTo(o.part.getName());
            }
            return o.amount - amount;
        }

    }

}
