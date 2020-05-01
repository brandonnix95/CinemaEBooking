package movies;

import java.util.List;

public class Movie {
	private String title;
	private Category category;
	private String cast;
	private String director;
	private String synopsis;
	private String reviews;
	private String trailer;
	private Rating rating;
	private String length;
	private boolean isShowing;
	private List<Showtime> showtimes;
	
	public List<Showtime> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(List<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

	public Movie(String title, Category category, String cast, String director, String synopsis, String reviews, String trailer, Rating rating, String length, boolean isShowing, List<Showtime> showtimes) {
		this.title = title;
		this.category = category;
		this.cast = cast;
		this.director = director;
		this.synopsis = synopsis;
		this.reviews = reviews;
		this.trailer = trailer;
		this.rating = rating;
		this.length = length;
		this.isShowing = isShowing;
		this.showtimes = showtimes;
	} // Movie
	
	/**
	 * Useful for when I need to do something really quick -- a movie obj that only holds it's title! Wonderous...
	 * @param title
	 */
	public Movie(String title) {
		this.title = title;
	} // Movie

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	public boolean isShowing() {
		return isShowing;
	}
	
	public void setShowing(boolean isShowing) {
		this.isShowing = isShowing;
	}
} // class
