import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';
import { Cart } from '../common/cart';
import { User } from '../common/user';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';

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

  constructor(private httpClient: HttpClient) { }

  updateHttpOptions() {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.getToken()
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
    const url = `${this.baseUrl}getCartById?cartId=${id}`;
    return this.httpClient.get<Cart[]>(url);
  }

  getCartsByUserId(userId: number): Observable<Cart[]>{
    this.updateHttpOptions();
    const url = `${this.baseUrl}getCart?userId=${userId}`;
    return this.httpClient.get<Cart[]>(url);
  }

  register(email: string, password: string, firstName: string, lastName: string, phoneNumber: string, addressLine1: string, addressLine2: string, state: string, pincode: number): Observable<string>{
    this.updateHttpOptions();
    return this.httpClient.post<MessageResponse>(this.baseUrl + "signup", {email, password, firstName, lastName, phoneNumber, addressLine1, addressLine2, state, pincode}, this.httpOptions).pipe(map(response => response.result));
  }



  signOut(): void {
    const query = this.getUser().email + " logged out.";
    var cust_query = {
      userId: this.getUser().userId,
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };

    this.addQuery(cust_query);

    window.sessionStorage.clear();
    window.location.reload();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
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
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);

    return this.httpClient.post<MessageResponse>(url,this.httpOptions).pipe(map(response => response.result));
  }

  changeCart(productId: number, quantity: number, cartId: number): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"changeCart?cartId=" + cartId + "&quantity=" + quantity + "&productId=" + productId;

    /*
    const query = "Cart #" + cartId + " updated: Product ID: " + productId + ", Quantity: " + quantity;
    var cust_query = {
      userId: this.getUser().userId,
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);

    console.log(url);
    console.log(this.httpOptions.headers);
    */

    return this.httpClient.post<MessageResponse>(url,this.httpOptions).pipe(map(response => response.result));
  }

  deleteCart(cartId: number): Observable<string>{
    this.updateHttpOptions();
    const url = this.baseUrl+"deleteCart?cartId=" + cartId;
    /*
    const query = "Cart #" + cartId + " deleted";
    var cust_query = {
      userId: this.getUser().userId,
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);
    
    console.log(url);
    console.log(this.httpOptions.headers.get('Authorization'));
    */

    return this.httpClient.delete<MessageResponse>(url,this.httpOptions).pipe(map(response => response.result));

  }



  login(loginForm: any): Observable<any> {
    const query = loginForm.email + " just logged in.";
    var cust_query = {
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };
    this.addQuery(cust_query);
    return this.httpClient.post(this.baseUrl + 'login', loginForm, this.httpOptions);
  }
/*
  register(firstName: string, lastName: string, email: string, password: string, phoneNumber: string, addressLine1: string,
    addressLine2: string, state: string, pincode: number): Observable<any> {
      const query = "New User registered: " + email;
      var cust_query = {
        firstName: this.getUser().firstName,
        lastName: this.getUser().lastName,
        email: this.getUser().email, 
        query: query
      };
      this.addQuery(cust_query);
    return this.httpClient.post(this.baseUrl + 'signup', {
      firstName, lastName, email, password, phoneNumber, addressLine1,
            addressLine2, state, pincode
    }, this.httpOptions);
  }
*/
}

interface MessageResponse{  
  "result": string;
}

interface TokenResponse{
  "id": number;  
  "accessToken": string;
}
