import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Address, User, UserDto } from '../types';
import { Observable } from 'rxjs';
import { response } from 'express';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/user/user-dashboard';

  constructor(private http: HttpClient) {}

  getUser(): Observable<User> {
    return this.http.get<User>(this.apiUrl_user + '/user-dashboard', {
      withCredentials: true,
    });
  }
  fetchAllUser(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl_admin + '/users', {
      // withCredentials: true,
    });
  }
  updateUser(userId: number, user: UserDto) {
    debugger;
    const url = `${this.apiUrl_admin}/update-user?userId=${userId}`;

    return this.http.put<User>(url, user, {
      withCredentials: true,
    });
  }

  deleteAddress(address: Address): Observable<any> {
    const options = {
      body: address,
      withCredentials: true,
    };

    return this.http.delete<any>(`${this.apiUrl_user}/delete-address`, options);
  }

  addAddress(address: Address) {
    debugger;

    return this.http.post<any>(`${this.apiUrl_user}/add-address`, address, {
      withCredentials: true,
    });
  }
}
