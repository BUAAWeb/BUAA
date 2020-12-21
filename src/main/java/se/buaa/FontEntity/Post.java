package se.buaa.FontEntity;

import lombok.Data;

@Data
public class Post {
    SearchWords search_words ;
    FilterWords filter_words;
    String sort;
    String page;
    String userID;

    public FilterWords getFilter_words() {
        return filter_words;
    }

    public void setFilter_words(FilterWords filter_words) {
        this.filter_words = filter_words;
    }

    public SearchWords getSearch_words() {
        return search_words;
    }

    public void setSearch_words(SearchWords search_words) {
        this.search_words = search_words;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
