package com.klalibrary.bean;

public class Itinerary {

	private int itnieraryId;
	private String name;
	private String genre;
	private String author;
	private String publication;
	public int getBookId() {
		return itnieraryId;
	}
	public void setBookId(int bookId) {
		this.itnieraryId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	
}
