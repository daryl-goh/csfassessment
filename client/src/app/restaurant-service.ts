import { HttpClient, HttpParams } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Restaurant, Comment } from './models'
import { lastValueFrom } from 'rxjs';
@Injectable()
export class RestaurantService {

	constructor(private httpClient: HttpClient) {}

	// getCuisineList(): Promise<string> {

	// 	return lastValueFrom(this.httpClient.get<string>(`http://localhost:8080/api/cuisines`));
	// }

	// TODO Task 2 
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getCuisineList(): Promise<Restaurant[]> {
		// Implememntation in here
		return lastValueFrom(this.httpClient.get<Restaurant[]>(`/api/cuisines`));

	}
	

	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getRestaurantsByCuisine(cuisine: string): Promise<Restaurant[]> {
		// Implememntation in here
		return lastValueFrom(this.httpClient.get<Restaurant[]>(`/api/${cuisine}/restaurants`));

	}
	
	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public getRestaurant(coordinates:number): Promise<Restaurant> {
		// Implememntation in here
		const params = new HttpParams()
        .set('coordinates', coordinates)
        
		return lastValueFrom(this.httpClient.get<Restaurant>(`/map/${coordinates}`, {params: params}));


	}

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	public postComment(c: Comment): Promise<any> {
		// Implememntation in here
		const body = {
			name: c.name,
			rating: c.rating,
			text: c.text
		}
		return lastValueFrom(this.httpClient.post<any>(`/api/comments`, body));
	}
}
