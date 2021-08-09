import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:5000/go/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(loginForm: any): Observable<any> {
    return this.http.post(AUTH_API + 'login', loginForm, httpOptions);
  }

  register(firstName: string, lastName: string, email: string, password: string, phoneNumber: string, addressLine1: string,
    addressLine2: string, state: string, pincode: number): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      firstName, lastName, email, password, phoneNumber, addressLine1,
            addressLine2, state, pincode
    }, httpOptions);
  }
}
