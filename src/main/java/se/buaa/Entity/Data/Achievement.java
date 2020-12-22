package se.buaa.Entity.Data;

public class Achievement {
    /* rap2 scholarInfo 响应数据 */
    String title;
    Integer num;

    public Achievement() {}

    public Achievement(String title, int citedQuantity) {
        this.title = title;
        this.num = citedQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
