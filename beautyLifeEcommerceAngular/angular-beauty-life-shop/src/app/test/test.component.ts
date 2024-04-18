import { Component } from '@angular/core';
import { RatingModule } from 'primeng/rating';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [RatingModule, FormsModule],
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css', '../../styles.css'],
})
export class TestComponent {
  value: number = 5;
  quantity: number = 2;
}
