import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GoServiceService } from './go-service.service';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:5000/go/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient, private gs: GoServiceService, private ts: TokenStorageService) { }

  login(loginForm: any): Observable<any> {
    const query = loginForm.email + " just logged in.";
    this.gs.addQuery(query);
    return this.http.post(AUTH_API + 'login', loginForm, httpOptions);
  }

  register(firstName: string, lastName: string, email: string, password: string, phoneNumber: string, addressLine1: string,
    addressLine2: string, state: string, pincode: number): Observable<any> {
      const query = "New User registered: " + email;
      var cust_query = {
        firstName: this.ts.getUser().firstName,
        lastName: this.ts.getUser().lastName,
        email: this.ts.getUser().email, 
        query: query
      };
      this.gs.addQuery(cust_query);
    return this.http.post(AUTH_API + 'signup', {
      firstName, lastName, email, password, phoneNumber, addressLine1,
            addressLine2, state, pincode
    }, httpOptions);
  }
}
