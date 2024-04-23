import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/user/user-dashboard';

  constructor(private http: HttpClient) {}

  getUser(): Observable<User> {
    return this.http.get<User>(this.apiUrl, { withCredentials: true });
  }
}
