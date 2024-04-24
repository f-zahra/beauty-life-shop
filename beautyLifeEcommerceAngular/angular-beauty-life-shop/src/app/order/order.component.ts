import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { OrderCartComponent } from './order-cart/order-cart.component';
import { FormsModule, NgForm } from '@angular/forms';
import { NgClass } from '@angular/common';
import { NgIf } from '@angular/common';
import { AddressFormComponent } from './address-form/address-form.component';
import { UserService } from '../services/user.service';
import { Address, User } from '../types';
import { NgxPayPalModule } from 'ngx-paypal';
@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    OrderCartComponent,
    NgxPayPalModule,
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
  // copied
  @ViewChild('paypal', { static: true }) paypalElement!: ElementRef; // HTML element for PayPal Smart button
  product = {
    price: '1.00',
    description: 'Check Amount',
  };
  payeeEmail: string = ''; // Merchant Account to credit the charge Amount
  paidFor: boolean = false; // Payment Successful Message handling

  paypalConfig = {
    // Configuration for PayPal Smart Button
    createOrder: (data: any, actions: any) => {
      return actions.order.create({
        purchase_units: [
          {
            description: 'Manager To Owner Payment',
            amount: {
              currency_code: 'CAD',
              value: this.product.price,
            },
            payee: {
              email_address: this.payeeEmail, // Send amount to corresponding Merchant
            },
            invoice_id: '', // You can generate on your own logic
          },
        ],
      });
    },
    onApprove: async (data: any, actions: any) => {
      const order = await actions.order.capture();
      this.paidFor = true;
      console.log(order);
    },
    onError: (err: any) => {
      console.log(err);
    },
  };
  // end copied

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe((data) => {
      this.userData = data;
      this.userAddress = data.addresses[0];
    });

    paypal.Buttons().render(this.paypalElement.nativeElement);
  }

  toggle() {
    this.isFormDisabled = !this.isFormDisabled;
  }

  isFormSubmited: boolean = false;

  onSubmit(form: NgForm) {
    const isFormValid = form.form.valid;
    this.isFormSubmited = true;
  }
  cancel() {
    throw new Error('Method not implemented.');
  }
}
