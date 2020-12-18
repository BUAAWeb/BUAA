package se.buaa.FontEntity;

import lombok.Data;

@Data
public class Post {
    SearchWords search_words ;
    Filter filter_words;
    String sort;
    String page;
    String userID;
}
