import { Injectable } from '@angular/core';

import { Observable, catchError, tap } from 'rxjs';
import { Product, ShoppingCart, ShoppingCartItem } from '../types';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/cart';

  constructor(private http: HttpClient) {}

  getShoppingCart() {
    return this.http.get<ShoppingCart>(this.apiUrl, {
      withCredentials: true,
    });
  }

  getCartItems() {
    return this.http.get<ShoppingCartItem[]>(this.apiUrl + '/cartItems', {
      withCredentials: true,
    });
  }

  addItemToCart(productId: number) {
    return this.http.post<string>(
      `${this.apiUrl}/add?productId=${productId}`,
      null,
      {
        withCredentials: true,
      }
    );
  }

  decrementItem(id: number) {
    return this.http.post<string>(
      `${this.apiUrl}/remove?productId=${id}`,
      null,
      {
        withCredentials: true,
      }
    );
  }

  removeitemFromCart(cartId: String) {
    return this.http.post<String>(
      `${this.apiUrl}/removeItem?cartId=${cartId}`,
      null,
      {
        withCredentials: true,
      }
    );
  }
}
