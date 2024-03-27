import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ShopComponent } from './shop/shop.component';
import { ProductComponent } from './components/product/product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { TestComponent } from './test/test.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

export const routes: Routes = [
  {
    path: 'shop',
    component: ShopComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
  {
    path: 'cart',
    component: ShoppingCartComponent,
  },
  {
    path: 'test',
    component: TestComponent,
  },
];
