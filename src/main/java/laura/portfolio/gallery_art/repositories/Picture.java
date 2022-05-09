package laura.portfolio.gallery_art.repositories;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="pictures")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String img;
    private String title;
    private int year;

    public Picture(){

    }

    public Picture(String img, String title, int year){
        this.img=img;
        this.title=title;
        this.year=year;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getImg(){
        return img;
    }

    public void setImg(String img){
        this.img=img;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year=year;
    }

    @Override
    public String toString(){
        return "Picture{"+
                "id"+id+
                ", img='"+img+'\''+
                ", title='"+title+'\''+
                ", year='"+year+'\''+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) && Objects.equals(img, picture.img) && Objects.equals(title, picture.title) && Objects.equals(year, picture.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img, title, year);
    }
}

