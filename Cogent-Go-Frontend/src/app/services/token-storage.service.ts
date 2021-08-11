import { Injectable } from '@angular/core';
import { GoServiceService } from './go-service.service';
import { QueryServiceService } from './query-service.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor(private qs: QueryServiceService) { }

  signOut(): void {
    const query = this.getUser().email + " logged out.";
    var cust_query = {
      userId: this.getUser().userId,
      firstName: this.getUser().firstName,
      lastName: this.getUser().lastName,
      email: this.getUser().email, 
      query: query
    };
    this.qs.addQuery(cust_query);
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
}
