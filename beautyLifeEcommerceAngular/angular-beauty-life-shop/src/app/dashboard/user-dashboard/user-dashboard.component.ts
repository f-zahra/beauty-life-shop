import { Component, ElementRef, ViewChild } from '@angular/core';
import { Order, User, OrderStatus } from '../../types';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  NgForm,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { OrderService } from '../../services/order.service';
import { Address } from '../../types';
import { SideMenuComponent } from '../side-menu/side-menu.component';
import { SearchFilterPipe } from '../../search-filter.pipe';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    SideMenuComponent,
    ReactiveFormsModule,
    SearchFilterPipe,
    RouterModule,
  ],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css',
})
export class UserDashboardComponent {
  searchText: string = '';
  addressForm!: FormGroup;
  userInfo!: User;
  orderInfo!: Order[];
  showAlert = false;

  Order!: Order;
  actionMessage = '';
  cancelButtonDisabled = false;
  activeTab: string = 'tab1';

  constructor(
    private userService: UserService,
    private orderService: OrderService,
    private fb: FormBuilder
  ) {}

  openTab(tab: string) {
    this.actionMessage = '';
    this.activeTab = tab;
  }
  ngOnInit(): void {
    //get user info
    debugger;
    this.userService.getUser().subscribe((data) => {
      console.log(data);
      this.userInfo = data;
    });
    //get user orders
    this.orderService.getOrderHistory().subscribe((data) => {
      this.orderInfo = data;
      this.addressForm.patchValue({
        user: this.userInfo.id, // Set user field in the form
      });
      console.log(data);
    });

    this.addressForm = this.fb.group({
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      postalCode: ['', Validators.required],
      country: ['', Validators.required],
      user: [],
    });
  }

  cancelOrder(order: Order) {
    this.orderService.updateOrder(order.orderId, this.Order).subscribe(
      (next) => {
        window.location.reload();
        this.actionMessage = next;
        console.log(next);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getOrder(order: Order) {
    this.Order = order;
  }
  deleteAddress(address: Address) {
    this.userService.deleteAddress(address).subscribe(() => {
      window.location.reload();
    });
  }
  addAddress(): void {
    this.openAlert();
    this.userService.addAddress(this.addressForm.value).subscribe(() => {
      window.location.reload();
    });
  }
  openAlert() {
    this.showAlert = true;
  }

  closeAlert() {
    this.showAlert = false;
  }
}
