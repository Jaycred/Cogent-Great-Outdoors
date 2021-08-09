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
import { QueryAddComponentComponent } from './components/query-add-component/query-add-component.component';
import { OrderAddComponentComponent } from './components/order-add-component/order-add-component.component';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { CategoryViewComponent } from './components/category-view/category-view.component';
import { ProductSpecsComponent } from './components/product-specs/product-specs.component';
import { CartViewComponent } from './components/cart-view/cart-view.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { SecurityServiceComponent } from './security-service/security-service.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';


const route: Routes = [
  {path: 'categories/:categoryName', component: ProductViewComponent},
  {path: 'categories', component: CategoryViewComponent},
  {path: 'products/:id', component: ProductSpecsComponent},
  {path: 'products', component: ProductViewComponent},
  {path: 'signup', component: UserAddComponentComponent},
  {path: 'addProduct', component: ProductAddComponentComponent},
  {path: 'addCart', component: CartAddComponentComponent},
  {path: 'login', component: UserLoginComponent},
  {path: 'cart', component: CartViewComponent},
  {path: 'cart/:cartId', component:CartViewComponent},
  {path: 'cart/user/:userId', component:CartViewComponent},
  {path: '**', redirectTo: 'categories', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    ProductAddComponentComponent,
    AddressAddComponentComponent,
    UserAddComponentComponent,
    QueryAddComponentComponent,
    OrderAddComponentComponent,
    ProductViewComponent,
    CategoryViewComponent,
    ProductSpecsComponent,
    CartViewComponent,
    UserAccountComponent,
    UserLoginComponent,
    SecurityServiceComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent

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
