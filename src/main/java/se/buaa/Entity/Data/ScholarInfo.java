package se.buaa.Entity.Data;

import java.util.ArrayList;
import java.util.List;

public class ScholarInfo {
    /* rap2 响应数据 */
    public String name;
    public Integer volume;
    public String scholar_id;
    public String affiliate;
    public List<Achievement> achList = new ArrayList<>();
    public boolean isVerified;
    public boolean isFocus;
}
