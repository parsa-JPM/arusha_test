package ir.codefather.doc.transfers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SentDocument {
    private String name;
    private Date date = new Date();

    private List<String> labels = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
