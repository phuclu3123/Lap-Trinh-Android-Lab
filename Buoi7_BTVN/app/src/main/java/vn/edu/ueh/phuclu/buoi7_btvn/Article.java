package vn.edu.ueh.phuclu.buoi7_btvn;

public class Article {
    private int id;
    private String title;
    private String shortDescription;
    private int imageResource;
    private int views;

    public Article(int id, String title, String shortDescription, int imageResource, int views) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.imageResource = imageResource;
        this.views = views;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public int getImageResource() {
        return imageResource;
    }
    public int getViews() {
        return views;
    }
    public void increaseViews() {
        views++;
    }
}
