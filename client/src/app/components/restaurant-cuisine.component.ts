import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit, OnDestroy{
	
	// TODO Task 3
	// For View 2
  

  params$!: Subscription;
  restaurants: Restaurant[] = [];



  constructor(private router: Router, private activatedRoute: ActivatedRoute
    , private restaurantSvc: RestaurantService) { }

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      params => {
        const cuisine = params['cuisine']
        console.log('>>> cuisine: ', cuisine)
        this.restaurantSvc.getRestaurantsByCuisine(cuisine)
          .then(result => {
            this.restaurants = result
            console.info('>>> restaurants: ', this.restaurants)
          })
          .catch(error => {
            console.error('>> error: ', error)
          })
      }
    )
  }

  backToHome() {
    this.router.navigate(['/'])
  }

ngOnDestroy(): void {
    this.params$.unsubscribe();

}


}
