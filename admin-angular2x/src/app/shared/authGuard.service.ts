import { Injectable } from '@angular/core';
import { CanActivate, RouterStateSnapshot, ActivatedRouteSnapshot } from "@angular/router";
import { Observable, Subject } from "rxjs";
import { AuthService } from './auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(protected router: Router, protected authService: AuthService) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        if (!this.authService.isAuthenticated()) {
            this.router.navigate(['/login']);
            return false;
        }

        // if (state.url !== '/login' && !this.authService.isAuthenticated()) {
        //     this.router.navigate(['/login']);
        //     return false;
        // }

        return true;
    }
}