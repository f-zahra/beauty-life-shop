import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShoppingCart } from '../../types';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-order-cart',
  standalone: true,
  imports: [],
  templateUrl: './order-cart.component.html',
  styleUrl: './order-cart.component.css',
})
export class OrderCartComponent {
  cart!: ShoppingCart;
  @Input() shippingCost!: number;
  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.getShoppingCart().subscribe((data: ShoppingCart) => {
      this.cart = data;
      console.log(this.cart);
    });
  }
}
