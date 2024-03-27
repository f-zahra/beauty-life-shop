import { Component, EventEmitter, Input, Output, model } from '@angular/core';
import { Product } from '../../types';
import { RatingModule } from 'primeng/rating';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [RatingModule, FormsModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent {
  //! init will always be provided
  @Input() product!: Product;

  @Output() ProductOutput: EventEmitter<Product> = new EventEmitter<Product>();

  ngOnInit() {
    this.ProductOutput.emit(this.product);
  }
}
