package se.buaa.Entity.Data;

import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.FontEntity.Filter;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class Data {
    public int total;
    public List<ES_Document> result_list = new ArrayList<>();
    public List<Filter> filter_list = new ArrayList<>();

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
