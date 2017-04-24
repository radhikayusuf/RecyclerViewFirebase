package id.bandits.recyclerviewfirebase.dao;

/**
 * Created by root on 24/04/17.
 */

public class MainDao {
    private String id, image, title;


    public MainDao(String id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
