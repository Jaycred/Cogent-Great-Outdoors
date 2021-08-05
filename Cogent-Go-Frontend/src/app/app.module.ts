import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductAddComponentComponent } from './components/product-add-component/product-add-component.component';
import { AddressAddComponentComponent } from './components/address-add-component/address-add-component.component';
import { UserAddComponentComponent } from './components/user-add-component/user-add-component.component';
import { CartAddComponentComponent } from './components/cart-add-component/cart-add-component.component';
import { QueryAddComponentComponent } from './components/query-add-component/query-add-component.component';
import { OrderAddComponentComponent } from './components/order-add-component/order-add-component.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { CategoryViewComponent } from './components/category-view/category-view.component';


const route: Routes = [
  {path: 'categories', component: CategoryViewComponent},
  {path: 'products/all', component: ProductViewComponent},
  {path: 'signup', component: UserAddComponentComponent},
  {path: '**', redirectTo: 'categories', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    ProductAddComponentComponent,
    AddressAddComponentComponent,
    UserAddComponentComponent,
    CartAddComponentComponent,
    QueryAddComponentComponent,
    OrderAddComponentComponent,
    ProductViewComponent,
    CategoryViewComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(route),
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
