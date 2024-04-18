import { Component, ViewChild } from '@angular/core';
import { OrderCartComponent } from './order-cart/order-cart.component';
import { FormsModule, NgForm } from '@angular/forms';
import { NgClass } from '@angular/common';
import { NgIf } from '@angular/common';
import { AddressFormComponent } from './address-form/address-form.component';
import { UserService } from '../services/user.service';
import { Address, User } from '../types';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    OrderCartComponent,
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
  userAddress!: Address;
  userData!: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe((data) => {
      this.userData = data;
      this.userAddress = data.addresses[0];

      console.log(data);
    });
  }

  toggle() {
    this.isFormDisabled = !this.isFormDisabled;
  }

  isFormSubmited: boolean = false;

  onSubmit(form: NgForm) {
    const isFormValid = form.form.valid;
    this.isFormSubmited = true;
  }
}
