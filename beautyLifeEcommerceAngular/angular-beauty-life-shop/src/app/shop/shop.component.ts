import { Component } from '@angular/core';
import { Product, Products } from '../types';
import { ProductsService } from '../services/products.service';
import { ProductComponent } from '../components/product/product.component';
import { CommonModule } from '@angular/common';
import { ProductDetailsComponent } from '../product-details/product-details.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [
    ProductComponent,
    CommonModule,
    ProductDetailsComponent,
    RouterOutlet,
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css',
})
export class ShopComponent {
  productList: Product[] = [];
  constructor(private productsService: ProductsService) {}

  ngOnInit() {
    this.productsService
      .getProducts('http://localhost:3000/products', { page: 0, perPage: 5 })
      .subscribe((products: Products) => {
        this.productList = products.products;
      });
  }

  onProductOutput(product: Product) {
    console.log(product);
  }
}
