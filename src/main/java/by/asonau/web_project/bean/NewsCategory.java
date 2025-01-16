package by.asonau.web_project.bean;

import java.util.Objects;

public class NewsCategory {

    private int newsCategoryId;
    private final String name;


    public NewsCategory(String name, int newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
        this.name = name;
    }

    public int getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(int newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCategory that = (NewsCategory) o;
        return newsCategoryId == that.newsCategoryId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsCategoryId, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "newsCategoryId=" + newsCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
