import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationRequest } from '../types';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/api/login';
  private authToken: string | null = null;
  constructor(private http: HttpClient) {}

  setAuthToken(token: string): void {
    this.authToken = token;
  }

  getAuthToken(): string | null {
    return this.authToken;
  }

  isAuthenticated(): boolean {
    return !!this.authToken;
  }
  authenticate(auth: AuthenticationRequest): Observable<any> {
    return this.http.post(this.apiUrl, auth);
  }
}
