import { Component, Input, ViewEncapsulation } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
@Component({
  selector: 'app-add-to-cart-button',
  standalone: true,
  imports: [],
  templateUrl: './add-to-cart-button.component.html',
  styleUrl: './add-to-cart-button.component.css',
  encapsulation: ViewEncapsulation.None,
})
export class AddToCartButtonComponent {
  @Input() productId!: number;

  constructor(private cartService: CartService, private router: Router) {}
  addToCart() {
    this.cartService.addItemToCart(this.productId).subscribe(() => {
      this.router.navigate(['/cart']);
    });
  }
}
