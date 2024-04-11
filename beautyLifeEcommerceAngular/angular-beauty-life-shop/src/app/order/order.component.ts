import { Component } from '@angular/core';
import { OrderCartComponent } from './order-cart/order-cart.component';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [OrderCartComponent],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css',
})
export class OrderComponent {}
