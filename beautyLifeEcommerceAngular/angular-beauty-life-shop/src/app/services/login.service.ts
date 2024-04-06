import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/login';

  getLoginPage(): Observable<ShoppingCart> {
    return this.http.get<ShoppingCart>(this.apiUrl);
  }
}
