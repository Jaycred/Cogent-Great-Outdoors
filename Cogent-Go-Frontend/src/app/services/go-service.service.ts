import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

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
    const url = this.baseUrl+"addProducts/";
    return this.httpClient.post<MessageResponse>(url,p,this.httpOptions).pipe(map(response => response.result));
  }

  placeOrder(o:any): Observable<string> {
    const url = this.baseUrl+"placeOrder/";
    return this.httpClient.post<MessageResponse>(url,o,this.httpOptions).pipe(map(response => response.result));
  }

}

interface MessageResponse{  
  "result": string;
}
