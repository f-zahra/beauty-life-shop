import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ShoppingCart, Address } from '../../types';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css',
})
export class PaymentComponent implements OnInit {
  @ViewChild('paypal', { static: true }) paypalElement!: ElementRef;
  @Input() cart!: ShoppingCart;
  @Input() shippingAddress!: Address;

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
              value: this.cart.total.toString(),
            },

            shipping: {
              address: {
                address_line_1: this.shippingAddress.street,
                address_line_2: '', // Add address line 2 if needed
                admin_area_1: this.shippingAddress.state,
                admin_area_2: this.shippingAddress.city,
                postal_code: this.shippingAddress.postalCode,
                country_code: 'CA', // ISO 3166-1 alpha-2 country code (e.g., CA for Canada)
              },
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
      //TODO redirect to order list
    },
    onError: (err: any) => {
      console.log(err);
    },
  };
  ngOnInit(): void {
    console.log(this.cart);
    paypal.Buttons(this.paypalConfig).render(this.paypalElement.nativeElement);
  }
}
