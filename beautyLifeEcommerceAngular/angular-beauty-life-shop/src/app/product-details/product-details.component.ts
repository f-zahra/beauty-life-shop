import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../types';
import { ProductsService } from '../services/products.service';
import { CommonModule } from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { CartService } from '../services/cart.service';
import { AddToCartButtonComponent } from './add-to-cart-button/add-to-cart-button.component';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule, RatingModule, FormsModule, AddToCartButtonComponent],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css',
})
export class ProductDetailsComponent {
  productId!: number;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private cartService: CartService
  ) {}

  productData!: Product;

  ngOnInit() {
    let product_id = this.route.snapshot.paramMap.get('id') as string;
    this.productId = parseInt(product_id);
    this.productService
      .getProductById(this.productId)
      .subscribe((data: Product) => {
        this.productData = data;
        console.log(this.productData);
      });
  }
}
