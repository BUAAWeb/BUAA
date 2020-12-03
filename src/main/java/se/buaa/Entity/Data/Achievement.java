package se.buaa.Entity.Data;

public class Achievement {
    /* rap2 scholarInfo 响应数据 */
    String title;
    int num;

    public Achievement() {}

    public Achievement(String title, int citedQuantity) {
        this.title = title;
        this.num = citedQuantity;
    }
}
