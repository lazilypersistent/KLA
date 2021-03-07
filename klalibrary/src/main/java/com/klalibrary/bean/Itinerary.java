package com.klalibrary.bean;

public class Itinerary {

	private String name;
	private String genre;
	private String author;
	private String publication;
	private String itinerayType;
	
	public Itinerary() {
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
	public String getItinerayType() {
		return itinerayType;
	}
	public void setItinerayType(String itinerayType) {
		this.itinerayType = itinerayType;
	}

	@Override
	public String toString() {
		return "Itinerary [name=" + name + ", genre=" + genre + ", author=" + author + ", publication=" + publication
				+ ", itinerayType=" + itinerayType + "]";
	}
	
	
}
