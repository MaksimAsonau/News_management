package by.asonau.web_project.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	private int newsId;
	private String title;
	private String brief;
	private String content;
	private String imageUrl;
	private LocalDate publishDate;
	private String categoryName;
	private int categoryId;
	private String loginOfAuthor;
	private int idOfAuthor;

	public News(int newsId, String imageUrl, String brief, String title) {
		this.newsId = newsId;
		this.imageUrl = imageUrl;
		this.brief = brief;
		this.title = title;
	}

	public News(int newsId, String title, String brief, String content, int categoryId) {
		this.newsId = newsId;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.categoryId = categoryId;
	}

	public News(String title, String brief, String content, LocalDate publishDate, int categoryId, int idOfAuthor) {
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.publishDate = publishDate;
		this.categoryId = categoryId;
		this.idOfAuthor = idOfAuthor;
	}

	public News(int newsId, String title, String brief, String content, LocalDate publishDate, String categoryName) {
		this.newsId = newsId;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.publishDate = publishDate;
		this.categoryName = categoryName;
	}

	public News(int newsId, String title, String brief, String content, String imageUrl, LocalDate publishDate, String categoryName, String loginOfAuthor) {
		this.newsId = newsId;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.categoryName = categoryName;
		this.loginOfAuthor = loginOfAuthor;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getLoginOfAuthor() {
		return loginOfAuthor;
	}

	public void setLoginOfAuthor(String loginOfAuthor) {
		this.loginOfAuthor = loginOfAuthor;
	}

	public int getIdOfAuthor() {
		return idOfAuthor;
	}

	public void setIdOfAuthor(int idOfAuthor) {
		this.idOfAuthor = idOfAuthor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		News news = (News) o;
		return newsId == news.newsId && categoryId == news.categoryId && idOfAuthor == news.idOfAuthor
				&& Objects.equals(title, news.title) && Objects.equals(brief, news.brief)
				&& Objects.equals(content, news.content) && Objects.equals(imageUrl, news.imageUrl)
				&& Objects.equals(publishDate, news.publishDate) && Objects.equals(categoryName, news.categoryName)
				&& Objects.equals(loginOfAuthor, news.loginOfAuthor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(newsId, title, brief, content, imageUrl, publishDate,
				categoryName, categoryId, loginOfAuthor, idOfAuthor);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"newsId=" + newsId +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", publishDate=" + publishDate +
				", categoryName='" + categoryName + '\'' +
				", categoryId=" + categoryId +
				", loginOfAuthor='" + loginOfAuthor + '\'' +
				", idOfAuthor=" + idOfAuthor +
				'}';
	}
}
