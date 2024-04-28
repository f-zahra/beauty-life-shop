import { Component, ViewChild } from '@angular/core';
import { SideMenuComponent } from '../side-menu/side-menu.component';
import { Order } from '../../types';
import { OrderService } from '../../services/order.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SearchFilterPipe } from '../../search-filter.pipe';

@Component({
  selector: 'app-sales-person-dashboard',
  standalone: true,
  imports: [SideMenuComponent, FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './sales-person-dashboard.component.html',
  styleUrl: './sales-person-dashboard.component.css',
})
export class SalesPersonDashboardComponent {
  activeTab: string = 'tab1';
  orderInfo!: Order[];
  actionMessage!: string;
  Order!: Order;
  searchText = '';
  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    //get order
    this.orderService.getClientOrders().subscribe((data) => {
      console.log(data);
      this.orderInfo = data;
    });
  }
  openTab(tab: string) {
    this.activeTab = tab;
  }

  getOrder(order: Order) {
    console.log('Selected order:', order);
    this.Order = order;
  }

  onSubmit(): void {
    if (this.Order) {
      this.orderService.updateOrder(this.Order.orderId, this.Order).subscribe(
        (response) => {
          window.location.reload();
          console.log('Order updated successfully:', response);
        },
        (error) => {
          console.error('Error updating order:', error);
        }
      );
    }
  }
}
