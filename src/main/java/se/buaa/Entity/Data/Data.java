package se.buaa.Entity.Data;

import se.buaa.Entity.ESDocument.ES_Document;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class Data {
    private int total;
    private List<ES_Document> result_list = new ArrayList<>();
//    private List<> filter_list = new ArrayList<>();
}
