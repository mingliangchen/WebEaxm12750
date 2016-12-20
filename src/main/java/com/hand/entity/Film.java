package com.hand.entity;




public class Film {
	private Integer film_id;
	private String title;
	private String description;
	private Integer release_year;
	private Integer language_id;
	private Integer originalLanguage_id;
	private Integer rental_duration;
	private Double rental_rate;
	private Integer length;
	private Double replacement_cost;
	private String rating;
	public Film() {
	}
	public Film(Integer film_id, String title, String description, Integer release_year, Integer language_id,
			Integer originalLanguage_id, Integer rental_duration, Double rental_rate, Integer length,
			Double replacement_cost, String rating) {
		super();
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.originalLanguage_id = originalLanguage_id;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
	}
	public Integer getFilm_id() {
		return film_id;
	}
	public void setFilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRelease_year() {
		return release_year;
	}
	public void setRelease_year(Integer release_year) {
		this.release_year = release_year;
	}
	public Integer getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}
	public Integer getOriginalLanguage_id() {
		return originalLanguage_id;
	}
	public void setOriginalLanguage_id(Integer originalLanguage_id) {
		this.originalLanguage_id = originalLanguage_id;
	}
	public Integer getRental_duration() {
		return rental_duration;
	}
	public void setRental_duration(Integer rental_duration) {
		this.rental_duration = rental_duration;
	}
	public Double getRental_rate() {
		return rental_rate;
	}
	public void setRental_rate(Double rental_rate) {
		this.rental_rate = rental_rate;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Double getReplacement_cost() {
		return replacement_cost;
	}
	public void setReplacement_cost(Double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((film_id == null) ? 0 : film_id.hashCode());
		result = prime * result + ((language_id == null) ? 0 : language_id.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((originalLanguage_id == null) ? 0 : originalLanguage_id.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((release_year == null) ? 0 : release_year.hashCode());
		result = prime * result + ((rental_duration == null) ? 0 : rental_duration.hashCode());
		result = prime * result + ((rental_rate == null) ? 0 : rental_rate.hashCode());
		result = prime * result + ((replacement_cost == null) ? 0 : replacement_cost.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (film_id == null) {
			if (other.film_id != null)
				return false;
		} else if (!film_id.equals(other.film_id))
			return false;
		if (language_id == null) {
			if (other.language_id != null)
				return false;
		} else if (!language_id.equals(other.language_id))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (originalLanguage_id == null) {
			if (other.originalLanguage_id != null)
				return false;
		} else if (!originalLanguage_id.equals(other.originalLanguage_id))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (release_year == null) {
			if (other.release_year != null)
				return false;
		} else if (!release_year.equals(other.release_year))
			return false;
		if (rental_duration == null) {
			if (other.rental_duration != null)
				return false;
		} else if (!rental_duration.equals(other.rental_duration))
			return false;
		if (rental_rate == null) {
			if (other.rental_rate != null)
				return false;
		} else if (!rental_rate.equals(other.rental_rate))
			return false;
		if (replacement_cost == null) {
			if (other.replacement_cost != null)
				return false;
		} else if (!replacement_cost.equals(other.replacement_cost))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", description=" + description + ", release_year="
				+ release_year + ", language_id=" + language_id + ", originalLanguage_id=" + originalLanguage_id
				+ ", rental_duration=" + rental_duration + ", rental_rate=" + rental_rate + ", length=" + length
				+ ", replacement_cost=" + replacement_cost + ", rating=" + rating + "]";
	}
}
