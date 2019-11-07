package io.github.arkits.jaswand.elements;

import java.util.ArrayList;
import java.util.List;

public class ChartDataset{

    public List<Integer> data;
    public String label;
    public String borderColor;
    public boolean fill;

    public ChartDataset(){
        this.data = new ArrayList<>();
    }
}