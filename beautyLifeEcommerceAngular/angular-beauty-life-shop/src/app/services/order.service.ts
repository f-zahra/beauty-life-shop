import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../types';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/api/user';
  private apiUrl2 = 'http://localhost:8080/api/sales-person';
  constructor(private http: HttpClient) {}

  placeOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.apiUrl + '/place-order', order, {
      withCredentials: true,
    });
  }
  getOrderHistory(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl + '/order-history', {
      withCredentials: true,
    });
  }

  updateOrder(orderId: number, order: Order): Observable<any> {
    return this.http.put(
      this.apiUrl + '/update-order?orderId=' + orderId,
      order,
      { responseType: 'text', withCredentials: true }
    );
  }

  getClientOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl2 + '/orders', {
      withCredentials: true,
    });
  }
}
