import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { NgFor } from '@angular/common';
import { OrderCartComponent } from './order-cart/order-cart.component';
import { FormsModule, NgForm } from '@angular/forms';
import { NgClass } from '@angular/common';
import { NgIf } from '@angular/common';
import { AddressFormComponent } from './address-form/address-form.component';
import { UserService } from '../services/user.service';
import { Address, ShoppingCart, User } from '../types';
import { PaymentComponent } from './payment/payment.component';
import { CartService } from '../services/cart.service';
@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    OrderCartComponent,
    NgFor,
    PaymentComponent,
    FormsModule,
    NgClass,
    NgIf,
    AddressFormComponent,
  ],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css',
})
export class OrderComponent {
  isFormDisabled: boolean = true;
  userAddressList!: Address[];
  userData!: User;
  cartData!: ShoppingCart;

  selectedAddress!: Address;
  constructor(
    private userService: UserService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe((data) => {
      this.userData = data;
      this.userAddressList = data.addresses;
      this.selectedAddress = this.userAddressList[0];
    });
    this.cartService.getShoppingCart().subscribe((data) => {
      this.cartData = data;
    });
  }

  onAddressChange(selectedUserAddress: Address) {
    this.selectedAddress = selectedUserAddress;
    console.log(this.selectedAddress);
  }
}
