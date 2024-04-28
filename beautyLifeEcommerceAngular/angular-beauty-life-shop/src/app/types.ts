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
  user?: User;
}

export enum OrderStatus {
  PENDING,
  SHIPPED,
  DELIVERED,
  CANCELED,
}
export enum UserRole {
  USER,
  ADMIN,
  VISITOR,
  SALESPERSON,
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
  phoneNumber: string;
  locked: boolean;
  role: UserRole;
  addresses: Address[];
}

export interface UserDto {
  id: number;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;
  phoneNumber: string;
  locked: boolean;
  role: string;
}

export interface AuthenticationRequest {
  username: String;
  password: String;
}
export interface RegistrationRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phoneNumber: string;
}
