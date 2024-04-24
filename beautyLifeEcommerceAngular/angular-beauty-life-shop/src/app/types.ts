import { HttpContext, HttpHeaders, HttpParams } from '@angular/common/http';
import { Url } from 'url';
export interface Product {
  id: number;
  name: string;
  category: string;
  url: string;
  price: number;
  rating: number;
  description: string;
}

export interface Products {
  page: number;
  perPage: number;
  products: Product[];
  total: number;
  totalPage: number;
}

export interface ShoppingCartItem {
  itemId: String;
  quantity: number;
  shoppingCart: ShoppingCart;
  product: Product;
}

export interface ShoppingCart {
  cartId: number; // Assuming Long maps to number in TypeScript
  isEmpty: boolean;
  quantity: number;
  total: number; // Assuming Total maps to number in TypeScript
  cartItems: ShoppingCartItem[];
  shippingCost: number;
}

export interface Order {
  orderId: number;
  orderDate: Date;
  shippingAddress: Address;
  items: OrderItem[];
  orderStatus: OrderStatus;
  shippingCost: number;
}

export enum OrderStatus {
  PENDING = 'PENDING',
}
export interface Options {
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  observe?: 'body';
  context?: HttpContext;
  params?:
    | HttpParams
    | {
        [param: string]:
          | string
          | number
          | boolean
          | ReadonlyArray<string | number | boolean>;
      };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
  transferCache?:
    | {
        includeHeaders?: string[];
      }
    | boolean;
}

export interface Address {
  id: number;
  street: string;
  city: string;
  state: string;
  postalCode: string;
  country: string;
  default: boolean;
  user: User;
}

export interface OrderItem {
  id: number;
  quantity: number;
  order?: any; // You can define the Order interface if needed
  product: Product;
}

export interface User {
  id: number;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;
  phoneNumber: number;
  addresses: Address[];
}

export interface AuthenticationRequest {
  username: String;
  password: String;
}
