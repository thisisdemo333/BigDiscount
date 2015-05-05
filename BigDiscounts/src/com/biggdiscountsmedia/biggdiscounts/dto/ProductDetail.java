package com.biggdiscountsmedia.biggdiscounts.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail {
	private int id;
	private int selling_price;
	private int mrp;
	private int total_views;

	private String description;
	private String offer;
	private String product_name;

	private Advertiser advertiser;
	private Rating rating;

	private List<AdvertisementImage> advertisement_images = new ArrayList<AdvertisementImage>();
	private List<Categories> categories = new ArrayList<Categories>();
	private List<Address> addresses = new ArrayList<Address>();
	private List<Cities> cities = new ArrayList<Cities>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellingPrice() {
		return selling_price;
	}

	public void setSellingPrice(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getMrp() {
		return mrp;
	}

	public void setMrp(int mrp) {
		this.mrp = mrp;
	}

	public int getTotalViews() {
		return total_views;
	}

	public void setTotalViews(int total_views) {
		this.total_views = total_views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(String product_name) {
		this.product_name = product_name;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public List<AdvertisementImage> getAdvertisementImages() {
		return advertisement_images;
	}

	public void setAdvertisementImages(
			List<AdvertisementImage> advertisement_images) {
		this.advertisement_images = advertisement_images;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Cities> getCities() {
		return cities;
	}

	public void setCities(List<Cities> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", selling_price=" + selling_price
				+ ", mrp=" + mrp + ", total_views=" + total_views
				+ ", description=" + description + ", offer=" + offer
				+ ", product_name=" + product_name + ", advertiser="
				+ advertiser + ", rating=" + rating + ", advertisement_images="
				+ advertisement_images + ", categories=" + categories
				+ ", addresses=" + addresses + ", cities=" + cities + "]";
	}

}
