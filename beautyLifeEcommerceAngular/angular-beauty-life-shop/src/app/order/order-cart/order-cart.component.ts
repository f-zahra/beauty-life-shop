import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ShoppingCart } from '../../types';
import { CartService } from '../../services/cart.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-order-cart',
  standalone: true,
  imports: [],
  templateUrl: './order-cart.component.html',
  styleUrl: './order-cart.component.css',
})
export class OrderCartComponent {
  @Input() shippingCost!: number;
  @Input() cart!: ShoppingCart;
  constructor(private router: Router) {}

  ngOnInit(): void {}
}
