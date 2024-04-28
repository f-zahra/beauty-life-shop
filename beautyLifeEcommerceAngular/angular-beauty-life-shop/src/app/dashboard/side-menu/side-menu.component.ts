import { Component, Output, EventEmitter, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationService } from '../../services/authentication.service';
import { Router } from '@angular/router';
import { response } from 'express';
import { error } from 'console';
@Component({
  selector: 'app-side-menu',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './side-menu.component.html',
  styleUrl: './side-menu.component.css',
})
export class SideMenuComponent {
  @Output() tabChanged = new EventEmitter<string>();
  @Input() userRole!: any;

  constructor(private auth: AuthenticationService, private router: Router) {}
  openTab(tab: string) {
    this.tabChanged.emit(tab);
  }

  logout() {
    debugger;
    // this.auth.logout().subscribe(() => {
    //   localStorage.removeItem('token');
    //   this.router.navigateByUrl('/login');
    // });
    this.auth.logout().subscribe(
      (response) => {
        localStorage.removeItem('token');
        console.log(response); // You could display a success message to the user
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
