import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from './token-storage.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class QueryServiceService {

  private baseUrl = 'http://localhost:5000/go/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(private httpClient: HttpClient, private ts: TokenStorageService) { }

  updateHttpOptions() {
    console.log(this.ts.getToken());
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': this.ts.getToken()
      })
    };
  }

  addQuery(query:any): Observable<string> {
    this.updateHttpOptions();
    const url = this.baseUrl+"createQuery";
    return this.httpClient.post<MessageResponse>(url,query,this.httpOptions).pipe(map(response => response.result));
  }

}

interface MessageResponse{  
  "result": string;
}
