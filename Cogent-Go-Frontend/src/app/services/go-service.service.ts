import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';
import { Cart } from '../common/cart';
import { User } from '../common/user';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';

@Injectable({
  providedIn: 'root'
})
export class GoServiceService {

  private baseUrl = 'http://localhost:5000/go/';

  private token:string = '';

  private currentUserId = 0;


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer ' + this.token
    })
  };

  constructor(private httpClient: HttpClient) { }

  addProduct(p:any): Observable<string> {
    const url = this.baseUrl+"addProduct";
    return this.httpClient.post<MessageResponse>(url,p,this.httpOptions).pipe(map(response => response.result));
  }

  addAddress(da:any): Observable<string> {
    const url = this.baseUrl+"addAddress";
    return this.httpClient.post<MessageResponse>(url,da,this.httpOptions).pipe(map(response => response.result));
  }

  addOrder(order:any): Observable<string> {
    const url = this.baseUrl+"placeOrder";
    return this.httpClient.post<MessageResponse>(url,order,this.httpOptions).pipe(map(response => response.result));
  }

  addUser(user:any): Observable<string> {
    const url = this.baseUrl+"signup";
    return this.httpClient.post<MessageResponse>(url,user,this.httpOptions).pipe(map(response => response.result));
  }

  addQuery(query:any): Observable<string> {
    const url = this.baseUrl+"createQuery";
    return this.httpClient.post<MessageResponse>(url,query,this.httpOptions).pipe(map(response => response.result));
  }

  // addCart(productId: number, price: number, userId: number): Observable<string> {
  //   const url = this.baseUrl+"saveCart";
  //   return this.httpClient.post<MessageResponse>(url,cart,this.httpOptions);
  // }

  getProducts(): Observable<Product[]>
  {
    const url = this.baseUrl + "findAllProducts";
    return this.httpClient.get<Product[]>(url);
  }

  getProductsById(id: number): Observable<Product[]>
  {
    const url = `${this.baseUrl}/findProductsById?id=${id}`;
    return this.httpClient.get<Product[]>(url);
  }
  getProductsByCategory(cName: string): Observable<Product[]>
  {
    const url = `${this.baseUrl}/findProductsByCategory?category=${cName}`;
    return this.httpClient.get<Product[]>(url);
  }

  getCarts(): Observable<Cart[]>{
    const url = this.baseUrl + "findAllCarts";
    return this.httpClient.get<Cart[]>(url);
  }

  getCartsById(id: number): Observable<Cart[]>{
    const url = `${this.baseUrl}/findCartsById?id=${id}`;
    return this.httpClient.get<Cart[]>(url);
  }

  getCartsByUserId(userId: number): Observable<Cart[]>{
    const url = `${this.baseUrl}/findCartsByUserId?id=${userId}`;
    return this.httpClient.get<Cart[]>(url);
  }

  login(user:any): void {
    const url = this.baseUrl+"login";
    this.httpClient.post<TokenResponse>(url,user,this.httpOptions).pipe(map(response => response.accessToken)).subscribe(data =>{this.token=data});
  }
}

interface MessageResponse{  
  "result": string;
}

interface TokenResponse{
  "id": number;
  "accessToken": string;
}
