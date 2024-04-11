import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authenticationGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (state.url == '/login') {
    return true;
  }
  if (typeof localStorage !== 'undefined') {
    let token = localStorage.getItem('token');
    if (!token) {
      return router.parseUrl('/login');
    }
  }
  return true;
};
