package com.example.seungkyu.dreamtodream;

/**
 * Created by seungkyu on 2016-12-05.
 */

public class BoardVO {
    private String title;
    private String content;
    private String date;
    private int hit;
    private String author;

    public BoardVO() {

    }
    public BoardVO(String title, String content, String date, int hit, String author) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.hit = hit;
        this.author = author;
    }
    public BoardVO(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", hit=" + hit +
                ", author='" + author + '\'' +
                '}';
    }
}
