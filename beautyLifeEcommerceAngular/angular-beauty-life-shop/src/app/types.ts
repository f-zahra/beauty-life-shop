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
  product: Product;
  quantity: number;
}

export interface ShoppingCart {
  cartId: number;
  items: ShoppingCartItem[];
}

export interface PaginationParams {
  [param: string]:
    | string
    | number
    | boolean
    | ReadonlyArray<string | number | boolean>;
  page: number;
  perPage: number;
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
