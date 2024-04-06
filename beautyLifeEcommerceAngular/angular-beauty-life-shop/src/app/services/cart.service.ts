import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Product, ShoppingCart } from '../types';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/api/cart';

  getShoppingCart(): Observable<ShoppingCart> {
    return this.http.get<ShoppingCart>(this.apiUrl);
  }
}
