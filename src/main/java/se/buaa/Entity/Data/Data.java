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

}
