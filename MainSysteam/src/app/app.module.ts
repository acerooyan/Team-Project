import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { NavbarComponent } from './home/navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { HrpageComponent } from './home/hrpage/hrpage.component';
import { EmployeeProfileComponent } from './home/employee-profile/employee-profile.component';
import { EmpVisaStatusManagementComponent } from './home/emp-visa-status-management/emp-visa-status-management.component';
import { HireComponent } from './home/hire/hire.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginFormComponent,
    HomeComponent,
    HrpageComponent,
    EmployeeProfileComponent,
    EmpVisaStatusManagementComponent,
    HireComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
   
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule

    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
