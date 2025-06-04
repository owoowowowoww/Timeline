package com.example.timeline.collection;

import com.example.timeline.pojo.CardPOJO;

import java.util.Objects;

public class Card {
    private String image;
    private String title;
    private int date;
	private int position;
	private String description;

	public Card(String title, int date, int position, String urlImage) {
		super();
		this.title = title;
		this.date = date;
		this.position = position;
		this.image = urlImage;
	}

	public Card(CardPOJO cardP, int position) {
		super();
		this.title = cardP.name;
		this.date = cardP.date;
		this.position = position;
		this.image = cardP.url;
	}

	public String getImage() {
		return image;
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

	public String getDescription() {
		return description;
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
