package se.buaa.FontEntity;
import lombok.Data;

import java.util.List;

@Data
public class Filter {
    String filter_name;
    String title;
    List<Filter_Item> filter_itemList;
}
