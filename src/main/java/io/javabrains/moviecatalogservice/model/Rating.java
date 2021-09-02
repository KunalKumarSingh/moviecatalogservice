package io.javabrains.moviecatalogservice.model;

public class Rating {
	
	private String movieid;
	private int ratingid;
	
	public Rating() {
		
	}
	
	public Rating(String movieid, int ratingid) {
		super();
		this.movieid = movieid;
		this.ratingid = ratingid;
	}
	public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	public int getRatingid() {
		return ratingid;
	}
	public void setRatingid(int ratingid) {
		this.ratingid = ratingid;
	}

}
