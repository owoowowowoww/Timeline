package com.example.timeline.collection;

import com.example.timeline.pojo.CardPOJO;

import java.util.Objects;

public class Card {
    private String image;
    private String title;
    private int date;
	private String description;

	public Card() {
		super();
	}

	public Card(String title, int date, String image, String description) {
		this();
		this.title = title;
		this.date = date;
		this.image = image;
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public int getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return title + " [" + date + "] [" + description + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Card card)) return false;
        return date == card.date
				&& Objects.equals(image, card.image)
				&& Objects.equals(title, card.title)
				&& Objects.equals(description, card.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(image, title, date, description);
	}
}
