import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit, OnDestroy {
	
	// TODO Task 4 and Task 5
	// For View 3

  form!: FormGroup;
  params$!: Subscription;
  restaurantDetails: Restaurant[] = [];
  coordinates!: string;

  constructor(private fb: FormBuilder
    , private restaurantSvc: RestaurantService
    , private router: Router
    , private activatedRoute: ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.form = this.createForm();

    this.params$ = this.activatedRoute.queryParams.subscribe(
      params => {
        const coord = params['coordinates']
        console.log('>>> coord: ', coord)
        this.restaurantSvc.getRestaurant(coord)
          .then(result => {
            this.coordinates = params['coordinates']
        
          })
          .catch(error => {
            console.error('>> error: ', error)
          })
        }
    )
      }
  

  createForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(4)]),
      rating: this.fb.control<number>(1, [Validators.required, Validators.min(1), Validators.max(5)]),
      text: this.fb.control<string>('', [Validators.required])
    })
  }

  submitForm() {
    console.log('>>> form: ', this.form.value)
  }

  backToViewTwo() {
    this.router.navigate(['/api/:cuisine/restaurants'])
  }

  formValid(){
    return this.form.valid;
  }

  ngOnDestroy(): void {
    this.params$.unsubscribe();
  }


}


