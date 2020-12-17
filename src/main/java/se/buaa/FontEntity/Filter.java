package se.buaa.FontEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Filter {
    public String filter_name;
    public String title;
    public List<Filter_Item> filter_itemList=new ArrayList<>();
}
