package se.buaa.Entity.Data;

import se.buaa.Entity.Document;
import se.buaa.Entity.ESDocument.ES_Document;

import java.util.ArrayList;
import java.util.List;

public class SearchResultData {
    public int total;
    public List<ES_Document> documentList=new ArrayList<>();
    public List<Filter> filterList =new ArrayList<>();
}
