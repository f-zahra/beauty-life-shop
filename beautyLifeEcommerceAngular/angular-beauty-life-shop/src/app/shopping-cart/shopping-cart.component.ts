import { Component, Output, EventEmitter } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Router } from '@angular/router';
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
  items!: ShoppingCartItem[];
  cart!: ShoppingCart;
  constructor(private cartService: CartService, private router: Router) {}

  @Output() cartItemsChange = new EventEmitter<ShoppingCart[]>();

  ngOnInit(): void {
    this.getCart();
  }

  navigateToOrder(): void {
    this.router.navigate(['/checkout']);
  }

  getCart(): void {
    this.cartService.getShoppingCart().subscribe((data) => {
      this.items = data.cartItems;
      this.cart = data;
      console.log(this.items);
      console.log(data);
    });
  }

  increment(id: number) {
    this.cartService.addItemToCart(id).subscribe(() => {
      this.getCart();
    });
  }

  removeItem(cartId: String) {
    console.log(cartId);
    this.cartService.removeitemFromCart(cartId).subscribe(() => {
      this.getCart();
    });
  }
  decrementQuatity(id: number) {
    this.cartService.decrementItem(id).subscribe(() => {
      this.getCart();
    });
  }
}
