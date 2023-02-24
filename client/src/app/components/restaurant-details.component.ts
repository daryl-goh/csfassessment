import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {
	
	// TODO Task 4 and Task 5
	// For View 3

  form!: FormGroup;

  constructor(private fb: FormBuilder
    , private restaurantSvc: RestaurantService
    , private router: Router
    ) {}

  ngOnInit(): void {
    this.form = this.createForm();
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
}
