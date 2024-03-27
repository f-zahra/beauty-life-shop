import { Component, Output, EventEmitter } from '@angular/core';
import { CartService } from '../services/cart.service';
import { CommonModule } from '@angular/common';
import { ShoppingCart, ShoppingCartItem, Product } from '../types';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css',
})
export class ShoppingCartComponent {
  constructor(private cartService: CartService) {}

  @Output() cartItemsChange = new EventEmitter<ShoppingCart[]>();
  items?: ShoppingCartItem[];

  ngOnInit(): void {
    this.cartService
      .getCartItems('http://localhost:3000/cart')
      .subscribe((data: ShoppingCart) => {
        this.items = data.items;
        console.log(data.items);
      });
  }

  getSubtotal(): number {
    return this.items!.reduce(
      (total, item) => total + item.quantity * item.product.price,
      0
    );
  }
}
