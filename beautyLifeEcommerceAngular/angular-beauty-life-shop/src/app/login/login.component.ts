import { Component } from '@angular/core';
import { AuthenticationRequest } from '../types';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { TokenServiceService } from '../services/token-service.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  authRequest: AuthenticationRequest = { username: '', password: '' };
  errorMsg: Array<string> = [];
  sessionId: any = '';

  constructor(
    private auth: AuthenticationService,
    private router: Router,
    private tokenService: TokenServiceService
  ) {}

  login() {
    //delete previous msg
    this.errorMsg = [];

    this.auth.authenticate(this.authRequest).subscribe({
      next: (res): void => {
        localStorage.setItem('token', res.token);
        console.log('user authenticated');
      },
      error: (err): void => {
        console.log(err);
      },
    });
  }
}
