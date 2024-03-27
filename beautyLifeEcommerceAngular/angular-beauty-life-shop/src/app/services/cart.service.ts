import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Product, ShoppingCart } from '../types';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private apiService: ApiService) {}

  getCartItems = (url: string): Observable<ShoppingCart> => {
    return this.apiService.get(url, {
      responseType: 'json',
    });
  };
}
