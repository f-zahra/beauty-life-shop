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

  productData: Product | null = null;

  ngOnInit() {
    let productId = this.route.snapshot.paramMap.get('id') as string;

    this.productService
      .getProductById(parseInt(productId))
      .subscribe((data: Product) => {
        this.productData = data;
        console.log(this.productData);
      });
  }
}
