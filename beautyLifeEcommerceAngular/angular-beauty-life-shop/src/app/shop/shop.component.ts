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
    this.productsService.getAllProducts().subscribe((products) => {
      this.productList = products;
    });
  }

  onProductOutput(product: Product) {
    console.log(product);
  }
}
