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
import { QueryAddComponentComponent } from './components/query-add-component/query-add-component.component';
import { OrderAddComponentComponent } from './components/order-add-component/order-add-component.component';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { CategoryViewComponent } from './components/category-view/category-view.component';
import { ProductSpecsComponent } from './components/product-specs/product-specs.component';
import { CartViewComponent } from './components/cart-view/cart-view.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { authInterceptorProviders } from './_helpers/auth-interceptor';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';

const route: Routes = [
  {path: 'categories/:categoryName', component: ProductViewComponent},
  {path: 'categories', component: CategoryViewComponent},
  {path: 'products/:id', component: ProductSpecsComponent},
  {path: 'products', component: ProductViewComponent},
  {path: 'addProduct', component: ProductAddComponentComponent},
  {path: 'login', component: LoginComponent},
  {path: 'cart', component: CartViewComponent},
  {path: 'cart/:cartId', component:CartViewComponent},
  {path: 'cart/user/:userId', component:CartViewComponent},
  {path: 'signup', component: RegisterComponent},
  {path: 'login', component:LoginComponent},
  {path: 'token', component:ProfileComponent},
  {path: '**', redirectTo: 'categories', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    ProductAddComponentComponent,
    AddressAddComponentComponent,
    QueryAddComponentComponent,
    OrderAddComponentComponent,
    ProductViewComponent,
    CategoryViewComponent,
    ProductSpecsComponent,
    CartViewComponent,
    UserAccountComponent,
    UserLoginComponent,
    RegisterComponent,
    LoginComponent,
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
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
