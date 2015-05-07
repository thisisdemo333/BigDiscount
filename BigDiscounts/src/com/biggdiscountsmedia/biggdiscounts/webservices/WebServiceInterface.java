package com.biggdiscountsmedia.biggdiscounts.webservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.biggdiscountsmedia.biggdiscounts.dto.Categories;
import com.biggdiscountsmedia.biggdiscounts.dto.Cities;
import com.biggdiscountsmedia.biggdiscounts.dto.Images;
import com.biggdiscountsmedia.biggdiscounts.dto.ProductDetail;
import com.biggdiscountsmedia.biggdiscounts.dto.ProductsResponse;
import com.biggdiscountsmedia.biggdiscounts.dto.RatingRequest;
import com.biggdiscountsmedia.biggdiscounts.dto.RatingResponse;

public interface WebServiceInterface {

	public ArrayList<Cities> getCityList();

	public ArrayList<Categories> getCategoriesList();

	public ProductsResponse getProduct(List<NameValuePair> params);

	public ArrayList<Images> getSliderImages(List<NameValuePair> params);
	
	public ProductDetail getProductDetail(String productId);
	
	public RatingResponse postRating(RatingRequest ratingRequest);
	
	public boolean isAlreadyRegistered(List<NameValuePair> params);
}
