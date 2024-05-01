import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationRequest, RegistrationRequest } from '../types';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080';
  private token: any = '';

  constructor(private http: HttpClient) {}

  setAuthToken(token: string): void {
    debugger;
    localStorage.setItem('token', token);
  }

  getAuthToken(): string | null {
    debugger;
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('token');
    } else {
      return '';
    }
  }

  isAuthenticated(): boolean {
    if (this.getAuthToken()) return true;
    else return false;
  }

  authenticate(auth: AuthenticationRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/login`, auth, {
      responseType: 'text',
    });
  }

  register(reg: RegistrationRequest) {
    debugger;
    return this.http.post(this.apiUrl + '/api/user/register', reg, {
      responseType: 'text',
    });
  }

  logout() {
    return this.http.post(`${this.apiUrl}/api/logout`, {
      responseType: 'text',
      withCredentials: true,
    });
  }
}
