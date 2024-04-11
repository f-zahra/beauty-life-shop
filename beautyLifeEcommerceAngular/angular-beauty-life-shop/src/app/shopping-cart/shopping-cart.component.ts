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
  constructor(private cartService: CartService, private router: Router) {}

  @Output() cartItemsChange = new EventEmitter<ShoppingCart[]>();
  items?: ShoppingCartItem[];
  cart?: ShoppingCart;

  ngOnInit(): void {
    this.cartService.getShoppingCart().subscribe((data: ShoppingCart) => {
      this.items = data.cartItems;
      console.log(this.items);
      console.log(data);
    });
  }

  navigateToAbout(): void {
    this.router.navigate(['/order']);
  }
}
