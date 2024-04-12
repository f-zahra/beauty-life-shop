import { Component, ViewChild } from '@angular/core';
import { OrderCartComponent } from './order-cart/order-cart.component';
import { FormsModule, NgForm } from '@angular/forms';
import { NgClass } from '@angular/common';
import { NgIf } from '@angular/common';
import { AddressFormComponent } from './address-form/address-form.component';

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

  toggle() {
    this.isFormDisabled = !this.isFormDisabled;
  }
  // onSubmit(arg0: any) {
  //   throw new Error('Method not implemented.');
  // }

  isFormSubmited: boolean = false;

  userObj: any = {
    firstName: '',
    lastName: '',
    userName: '',
    city: '',
    state: '',
    zipcode: '',
    isAggree: false,
  };

  onSubmit(form: NgForm) {
    const isFormValid = form.form.valid;
    this.isFormSubmited = true;
  }
}
