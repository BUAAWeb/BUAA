package se.buaa.Entity.Data;

import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.ESDocument.ES_Expert;
import se.buaa.FontEntity.Filter;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class Data {
    public int total;
    public List<ES_Document> result_list = new ArrayList<>();
    public List<ES_Expert> expert_list = new ArrayList<>();
    public List<Filter> filter_list = new ArrayList<>();
    public  int time;

    public List<ES_Expert> getExpert_list() {
        return expert_list;
    }

    public void setExpert_list(List<ES_Expert> expert_list) {
        this.expert_list = expert_list;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ES_Document> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<ES_Document> result_list) {
        this.result_list = result_list;
    }

    public List<Filter> getFilter_list() {
        return filter_list;
    }

    public void setFilter_list(List<Filter> filter_list) {
        this.filter_list = filter_list;
    }
}
