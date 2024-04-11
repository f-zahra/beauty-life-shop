import { Injectable } from '@angular/core';

import { Observable, catchError, tap } from 'rxjs';
import { Product, ShoppingCart } from '../types';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/cart';

  constructor(private http: HttpClient) {}

  getShoppingCart(): Observable<ShoppingCart> {
    return this.http.get<ShoppingCart>(this.apiUrl, { withCredentials: true });
  }

  addItemToCart(productId: number): Observable<string> {
    return this.http.post<string>(
      `${this.apiUrl}/add?productId=${productId}`,
      null,
      {
        withCredentials: true,
      }
    );
  }
}
