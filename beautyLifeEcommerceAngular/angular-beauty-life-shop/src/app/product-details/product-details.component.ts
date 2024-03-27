import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../types';
import { ProductsService } from '../services/products.service';
import { CommonModule } from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule, RatingModule, FormsModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css',
})
export class ProductDetailsComponent {
  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService
  ) {}

  productData!: Product;

  ngOnInit() {
    let productId = this.route.snapshot.paramMap.get('id');
    console.log(productId);

    this.productService
      .getProductbyId(`http://localhost:3000/products/${productId}`)
      .subscribe((data) => (this.productData = data));
  }
}
