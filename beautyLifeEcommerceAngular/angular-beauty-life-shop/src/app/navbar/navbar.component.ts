import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { SearchComponent } from '../components/search/search.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [SearchComponent, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {}
