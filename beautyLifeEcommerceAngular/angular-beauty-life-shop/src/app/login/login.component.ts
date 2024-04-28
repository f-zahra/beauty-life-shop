import { Component, ViewChild } from '@angular/core';
import { AuthenticationRequest, RegistrationRequest, UserRole } from '../types';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  isSignDivVisiable: boolean = true;
  authRequest: AuthenticationRequest = {
    username: '',
    password: '',
  };
  errorMsg: Array<string> = [];
  sessionId: any = '';
  signUpObj: RegistrationRequest = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phoneNumber: '',
  };

  constructor(
    private auth: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.auth.isAuthenticated()) {
      this.router.navigateByUrl('');
    }
  }
  login() {
    debugger;
    this.errorMsg = [];

    this.auth.authenticate(this.authRequest).subscribe({
      next: (res): void => {
        debugger;

        this.auth.setAuthToken(res);
        console.log(res);

        const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
        console.log(returnUrl);
        this.router.navigateByUrl(returnUrl);
      },
      error: (err): void => {
        console.log(err);
      },
    });
  }
  onRegister() {
    debugger;
    this.auth.register(this.signUpObj).subscribe({
      next: (res) => {
        this.authRequest.username = this.signUpObj.email;
        this.authRequest.password = this.signUpObj.password;
        this.login();
        console.log(res);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
