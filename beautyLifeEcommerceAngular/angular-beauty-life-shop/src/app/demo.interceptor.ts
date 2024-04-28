import { HttpInterceptorFn } from '@angular/common/http';
import { error } from 'console';
import { catchError, throwError } from 'rxjs';
import { AuthenticationService } from '../app/services/authentication.service';
import { inject } from '@angular/core';
export const demoInterceptor: HttpInterceptorFn = (req, next) => {
  debugger;
  const authService = inject(AuthenticationService);
  const token = authService.getAuthToken();

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`,
    },
  });

  return next(authReq);
};
