package com.example.timeline.collection;

import java.util.Objects;

public class Card {
    private String image;
    private String title;
    private int date;
    private String description;
	
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
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
