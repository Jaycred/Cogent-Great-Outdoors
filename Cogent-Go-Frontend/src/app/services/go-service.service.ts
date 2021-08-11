import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';
import { Cart } from '../common/cart';
import { User } from '../common/user';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';
import { TokenStorageService } from './token-storage.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class GoServiceService {

  private baseUrl = 'http://localhost:5000/go/';
  private currentUserId = 0;

  private loginArray: Array<any> = [];



  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(private httpClient: HttpClient, private ts: TokenStorageService) { }

  updateHttpOptions() {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': this.ts.getToken()
      })
    };
  }


  addProduct(p:any): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"addProduct";
    return this.httpClient.post<MessageResponse>(url,p,this.httpOptions).pipe(map(response => response.result));
  }

  addAddress(da:any): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"addAddress";
    return this.httpClient.post<MessageResponse>(url,da,this.httpOptions).pipe(map(response => response.result));
  }

  addOrder(order:any): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"placeOrder";
    return this.httpClient.post<MessageResponse>(url,order,this.httpOptions).pipe(map(response => response.result));
  }

 addUser(user:any): Observable<string> {
  this.updateHttpOptions();
  const url = this.baseUrl+"signup";
     return this.httpClient.post<MessageResponse>(url,user,this.httpOptions).pipe(map(response => response.result));
   }

  addQuery(query:any): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"createQuery";
    return this.httpClient.post<MessageResponse>(url,query,this.httpOptions).pipe(map(response => response.result));
  }

  getProducts(): Observable<Product[]>
  {
    this.updateHttpOptions();
    const url = this.baseUrl + "findAllProducts";
    return this.httpClient.get<Product[]>(url);
  }

  getProductsById(id: number): Observable<Product[]>
  {
    this.updateHttpOptions();
    const url = `${this.baseUrl}findProductsById?id=${id}`;
    return this.httpClient.get<Product[]>(url);
  }
  getProductsByCategory(cName: string): Observable<Product[]>
  {
    this.updateHttpOptions();
    const url = `${this.baseUrl}findProductsByCategory?category=${cName}`;
    return this.httpClient.get<Product[]>(url);
  }

  getCarts(): Observable<Cart[]>{
    this.updateHttpOptions();
    const url = this.baseUrl + "findAllCarts";
    return this.httpClient.get<Cart[]>(url);
  }

  getCartsById(id: number): Observable<Cart[]>{
    this.updateHttpOptions();
    const url = `${this.baseUrl}findCartsById?id=${id}`;
    return this.httpClient.get<Cart[]>(url);
  }

  getCartsByUserId(userId: number): Observable<Cart[]>{
    this.updateHttpOptions();
    const url = `${this.baseUrl}getCart/${userId}`;
    return this.httpClient.get<Cart[]>(url);
  }

  /*
  login(user: any): void{
    const url = this.baseUrl + "login";
    this.httpClient.post<TokenResponse>(url,user,this.httpOptions).pipe(map(response => response.accessToken)).subscribe(data=>this.token = data);
    //this.token = this.loginArray[0];
    //this.currentUserId = this.loginArray[1];
    console.log(this.token);
  }
  login(email: string, password: string): Observable<any>{
    return this.httpClient.post<MessageResponse>(this.baseUrl + "login", {email, password}, this.httpOptions).pipe(map(response => response.result));
  }
  */

  register(email: string, password: string, firstName: string, lastName: string, phoneNumber: string, addressLine1: string, addressLine2: string, state: string, pincode: number): Observable<string>{
    this.updateHttpOptions();
    return this.httpClient.post<MessageResponse>(this.baseUrl + "signup", {email, password, firstName, lastName, phoneNumber, addressLine1, addressLine2, state, pincode}, this.httpOptions).pipe(map(response => response.result));
  }

  /*
  signOut(): void {
    window.sessionStorage.clear();
    this.updateHttpOptions();
  }
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    this.updateHttpOptions();
  }
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
  */

  public saveUser(user: any): void {
    this.updateHttpOptions();
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  addCart(productId: number, price: number, userId: number): Observable<string> {
    this.updateHttpOptions();

    const url = this.baseUrl+"saveCart?productId=" + productId + "&price=" + price + "&userId=" + userId;
    const query = "User #" + userId + " just added Product #" + productId + " to their cart.";
    var cust_query = {
      userId: userId,
      firstName: this.ts.getUser().firstName,
      lastName: this.ts.getUser().lastName,
      email: this.ts.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);

    return this.httpClient.post<MessageResponse>(url,{productId, price, userId},this.httpOptions).pipe(map(response => response.result));
  }

  changeCart(productId: number, quantity: number, cartId: number): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"changeCart?cartId=" + cartId + "&quantity=" + quantity + "&productId=" + productId;

    const query = "Cart #" + cartId + " updated: Product ID: " + productId + ", Quantity: " + quantity;
    var cust_query = {
      userId: this.ts.getUser().userId,
      firstName: this.ts.getUser().firstName,
      lastName: this.ts.getUser().lastName,
      email: this.ts.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);
    return this.httpClient.post<MessageResponse>(url,{productId, quantity, cartId},this.httpOptions).pipe(map(response => response.result));
  }

  deleteCart(cartId: number): Observable<string>{
    this.updateHttpOptions();
    const url = this.baseUrl+"deleteCart?cartId=" + cartId;
    const query = "Cart #" + cartId + " deleted";
    var cust_query = {
      userId: this.ts.getUser().userId,
      firstName: this.ts.getUser().firstName,
      lastName: this.ts.getUser().lastName,
      email: this.ts.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);
    return this.httpClient.delete<MessageResponse>(url).pipe(map(response => response.result));

  }

}

interface MessageResponse{  
  "result": string;
}

interface TokenResponse{
  "id": number;  
  "accessToken": string;
}
