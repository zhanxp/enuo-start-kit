import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";


import { AppComponent } from './app.component';
import { LoginComponent } from './account/login.component';

import { RouterModule, PreloadAllModules } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { MainComponent } from './main/main.component'

import { ROUTES } from './app.routes';

import { AuthService } from './shared/auth.service';
import { AccountService } from './shared/account.service';

import { ToasterService } from 'angular2-toaster/angular2-toaster';
import { NotificationService } from './shared/notification.service';
import { AuthGuard } from './shared/authGuard.service';
import { HttpService } from './shared/http.service';
import { HttpModule } from '@angular/http';

import { HttpInterceptorModule } from 'angular2-http-interceptor';
import { APIInterceptor } from './shared/api.tnterceptor'
import { ToasterModule } from 'angular2-toaster/angular2-toaster';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    ErrorComponent
  ],
  imports: [
    ToasterModule,
    HttpModule,
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    //HttpInterceptorModule.withInterceptors([APIInterceptor]),
    RouterModule.forRoot(ROUTES, { useHash: true, preloadingStrategy: PreloadAllModules })
  ],
  providers: [
    HttpService,
    AccountService,
    AuthService,
    AuthGuard,
    ToasterService,
    NotificationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
