import { Component, EventEmitter, Input, Output, model } from '@angular/core';
import { Product } from '../../types';
import { RatingModule } from 'primeng/rating';
import { FormsModule, NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { AddToCartButtonComponent } from '../../product-details/add-to-cart-button/add-to-cart-button.component';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-product',
  standalone: true,
  imports: [RatingModule, FormsModule, AddToCartButtonComponent, NgIf],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent {
  //! init will always be provided
  @Input() product!: Product;

  @Output() ProductOutput: EventEmitter<Product> = new EventEmitter<Product>();

  constructor(private router: Router, cartService: CartService) {}

  ngOnInit() {
    this.ProductOutput.emit(this.product);
  }
}
