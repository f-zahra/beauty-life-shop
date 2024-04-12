import { Component, Input } from '@angular/core';

import { FormsModule, NgForm } from '@angular/forms';
import { NgClass } from '@angular/common';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-address-form',
  standalone: true,
  imports: [FormsModule, NgIf, NgClass],
  templateUrl: './address-form.component.html',
  styleUrl: './address-form.component.css',
})
export class AddressFormComponent {
  // isFormDisabled: boolean = true;
  @Input() isFormDisabled: boolean = false;
  isFormSubmited: boolean = false;

  onSubmit(form: NgForm) {
    const isFormValid = form.form.valid;
    this.isFormSubmited = true;
  }
}
