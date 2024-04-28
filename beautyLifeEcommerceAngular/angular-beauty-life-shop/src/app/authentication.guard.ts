import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../app/services/authentication.service';

export const authenticationGuard: CanActivateFn = (route, state) => {
  debugger;
  const router = inject(Router);
  const authService = inject(AuthenticationService);
  const token = authService.getAuthToken();
  if (!token) {
    router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  } else {
    return true;
  }
};
