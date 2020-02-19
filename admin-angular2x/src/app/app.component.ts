import { Component } from '@angular/core';
import {ToasterService, ToasterConfig} from 'angular2-toaster/angular2-toaster';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private toastrConfig: ToasterConfig;
  constructor() {
    this.toastrConfig = new ToasterConfig({
      newestOnTop: true,
      showCloseButton: true,
      tapToDismiss: false
    });
  }
}
