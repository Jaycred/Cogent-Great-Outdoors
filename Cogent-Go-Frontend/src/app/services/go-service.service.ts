import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class GoServiceService {

  private baseUrl = 'http://localhost:5000/go/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(private httpClient: HttpClient) { }

  addProduct(p:any): Observable<string> {
    const url = this.baseUrl+"addProducts";
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
    const url = this.baseUrl+"addUser";
    return this.httpClient.post<MessageResponse>(url,user,this.httpOptions).pipe(map(response => response.result));
  }

  addQuery(query:any): Observable<string> {
    const url = this.baseUrl+"createQuery";
    return this.httpClient.post<MessageResponse>(url,query,this.httpOptions).pipe(map(response => response.result));
  }

  addCart(cart:any): Observable<string> {
    const url = this.baseUrl+"saveCart";
    return this.httpClient.post<MessageResponse>(url,cart,this.httpOptions).pipe(map(response => response.result));
  }

  getProducts(): Observable<Product[]>
  {
    const url = this.baseUrl + "findAllProducts";
    return this.httpClient.get<Product[]>(url);
  }
}

interface MessageResponse{  
  "result": string;
}
