import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ShopComponent } from './shop/shop.component';
import { ProductComponent } from './components/product/product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderComponent } from './order/order.component';
import { LoginComponent } from './login/login.component';
import { authenticationGuard } from './authentication.guard';
import { UserDashboardComponent } from './dashboard/user-dashboard/user-dashboard.component';
import { SalesPersonDashboardComponent } from './dashboard/sales-person-dashboard/sales-person-dashboard.component';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import { HomeComponent } from './home/home.component';
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
  {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    canActivate: [authenticationGuard],
  },
  {
    path: 'sales-dashboard',
    component: SalesPersonDashboardComponent,
    canActivate: [authenticationGuard],
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [authenticationGuard],
  },
  {
    path: '',
    component: HomeComponent,
  },
];
