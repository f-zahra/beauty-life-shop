import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ShopComponent } from './shop/shop.component';
import { ProductComponent } from './components/product/product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderComponent } from './order/order.component';
import { LoginComponent } from './login/login.component';
import { authenticationGuard } from './authentication.guard';
import { PaymentComponent } from './order/payment/payment.component';

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
    path: 'checkout',
    component: OrderComponent,
    canActivate: [authenticationGuard],
  },

  {
    path: 'login',
    component: LoginComponent,
  },
];
