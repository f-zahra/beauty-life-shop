import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { User } from '../../types';
import { ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboardComponent {
  selectedClient!: User;
  clientForm!: FormGroup;
  activeTab: string = 'tab1';
  clients!: User[];
  constructor(private userService: UserService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.userService.fetchAllUser().subscribe((data) => {
      this.clients = data;
    });

    // Initialize the FormGroup with default values and validation
    this.clientForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Email validation
      firstname: ['', Validators.required], // Required field
      lastname: ['', Validators.required], // Required field
      role: ['', Validators.required], // Required field
    });
    // •	Ajouter, modifier, supprimer un produit
    // •	Ajouter, modifier, supprimer une catégorie
    // •	Ajouter, modifier, supprimer ou désactiver un client
    // •	Ajouter, modifier, supprimer ou désactiver une commande
    // •	Ajouter, modifier, supprimer un magasin
  }
  openTab(tab: string) {
    this.activeTab = tab;
  }

  selectClient(client: User) {
    this.selectedClient = client;

    // Update the FormGroup with the selected client's data
    this.clientForm.patchValue({
      email: client.email,
      firstname: client.firstname,
      lastname: client.lastname,
      role: client.role,
    });
  }

  // Form submission handler
  // Function that submits the user update request
  onSubmit() {
    if (this.clientForm) {
      const updatedUser = { ...this.selectedClient, ...this.clientForm.value };

      console.log('Submitting user update with data:', updatedUser); // Log to confirm data
      this.userService.updateUser(updatedUser.id, updatedUser).subscribe(
        (response) => {
          console.log('User updated successfully:', response);
        },
        (error) => {
          console.error('Error updating user:', error);
        }
      );
    }
  }
}
