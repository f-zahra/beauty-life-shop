import { Component, ViewChild } from '@angular/core';
import { OrderCartComponent } from './order-cart/order-cart.component';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [OrderCartComponent, FormsModule],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css',
})
export class OrderComponent {
  isFormDisabled: boolean = false;

  toggle() {
    this.isFormDisabled = !this.isFormDisabled;
  }

  onSubmit(formData: any) {
    console.log(formData); // Access the form data here
  }
}
