/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Blog {
    
    private SimpleIntegerProperty id_blog;
    private SimpleStringProperty text_blog;
    private SimpleStringProperty image_blog;

    public Blog() {
    }

    
    public Blog(int id_blog, String text_blog, String image_blog) {
        this.id_blog = new SimpleIntegerProperty(id_blog);
        this.text_blog = new SimpleStringProperty(text_blog);
        this.image_blog = new SimpleStringProperty(image_blog);
    }

    public Blog(String text_blog, String image_blog) {
        this.text_blog = new SimpleStringProperty(text_blog);
        this.image_blog = new SimpleStringProperty(image_blog);
    }
    public int getId_blog() {
        return id_blog.get();
    }

    public void setId_blog(int id_blog) {
        this.id_blog = new SimpleIntegerProperty(id_blog);
    }

    public String gettext_blog() {
        return text_blog.get();
    }

    public void settext_blog(String text_blog) {
        this.text_blog= new SimpleStringProperty(text_blog);
    }

    public String getimage_blog() {
        return image_blog.get();
    }
    public void setimage_blog(String image_blog) {
        this.image_blog = new SimpleStringProperty(image_blog);
    }
    
    public SimpleStringProperty gettext_blogProperty(){
        return text_blog;
    }
    public SimpleStringProperty getimage_blogProperty(){
        return image_blog;
    }
    

    @Override
    public String toString() {
        return "Blog{" + "id=" + id_blog.get() + ", text_blog=" + text_blog.get() + ", image_blog=" + image_blog.get() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id_blog);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (!Objects.equals(this.id_blog, other.id_blog)) {
            return false;
        }
        return true;
    }
    
    
    
}
