import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CuisineListComponent } from './components/cuisine-list.component';
import { RestaurantCuisineComponent } from './components/restaurant-cuisine.component';
import { RestaurantDetailsComponent } from './components/restaurant-details.component';
import { RestaurantService } from './restaurant-service';

const appRoutes: Route[] = [
  { path: '', component: CuisineListComponent },
  { path: 'api/:cuisine/restaurants', component: RestaurantCuisineComponent },
  { path: 'restaurantdetails', component: RestaurantDetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'}
 
];

@NgModule({
  declarations: [
    AppComponent,
    CuisineListComponent,
    RestaurantCuisineComponent,
    RestaurantDetailsComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [RestaurantService],
  bootstrap: [AppComponent]
})
export class AppModule { }
