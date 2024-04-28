import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationRequest, RegistrationRequest } from '../types';

function isBrowser(): boolean {
  return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
}
@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  setAuthToken(token: string): void {
    debugger;
    localStorage.setItem('token', token);
  }

  getAuthToken(): string {
    if (isBrowser()) {
      localStorage.getItem('token');
    }
    return '';
  }

  isAuthenticated(): boolean {
    if (this.getAuthToken()) return true;
    else return false;
  }

  isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
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
