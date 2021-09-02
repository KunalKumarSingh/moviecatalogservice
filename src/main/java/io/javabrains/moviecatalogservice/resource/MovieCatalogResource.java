package io.javabrains.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userid}")
	public List<CatalogItem> getCatalog(@PathVariable("userid") String userid){
		
		// rating list got from calling rating service
		List<Rating> ratings = Arrays.asList(new Rating("1234",4),new Rating("5678",3));
		UserRating userRatings=restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingsdata/user/"+userid, UserRating.class);
		
		return userRatings.getRatings().stream().map(rating -> {
			
			//Rest Template method of calling MS
			Movie movie=restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieid(), Movie.class);
			
			//Web Client method of calling MS
			/*Movie movie=webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movies/"+rating.getMovieid())
					.retrieve()
					.bodyToMono(Movie.class)
					.block(); */
			
			return new CatalogItem(movie.getName(), "Test",  rating.getRatingid());// 2 MS is called one Movie,2nd is Ratings
			
			}).collect(Collectors.toList());
		
	}

}
