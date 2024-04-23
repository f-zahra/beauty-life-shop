import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../types';
import { TestBed } from '@angular/core/testing';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/api/user/place-order';
  private payUrl = 'http://localhost:8080/payment/create';

  constructor(private http: HttpClient) {}

  //TODO modify

  payOrder() {
    const params = new HttpParams()
      .set('method', 'PAYPAL')
      .set('amount', '50')
      .set('currency', 'CAD')
      .set('description', 'purchase');
    return this.http.post(this.payUrl, null, {
      params: params,
      responseType: 'text',
    }) as Observable<any>;
  }
  placeOrder(order: Order) {
    return this.http.post<Order>(`${this.apiUrl}`, order, {
      withCredentials: true,
    });
  }
}
