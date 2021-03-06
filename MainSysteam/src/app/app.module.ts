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
import { WildCardComponent } from './wild-card/wild-card.component';
import { Step1Component } from './register/step1/step1.component';
import { Step2Component } from './register/step2/step2.component';
import { Step3Component } from './register/step3/step3.component';
import { Step4Component } from './register/step4/step4.component';
import { Step5Component } from './register/step5/step5.component';
import { Step6Component } from './register/step6/step6.component';
import {RegisterService} from "./services/register.service";
import { RegnavbarComponent } from './register/regnavbar/regnavbar.component';
import { RegisterComponent } from './register/register.component';
import { FileuploadComponent } from './test/fileupload/fileupload.component';
import { CommonModule } from '@angular/common';
import { Step7Component } from './register/step7/step7.component';
import { EnavbarComponent } from './Ehome/enavbar/enavbar.component';
import { PersonalInfoComponent } from './Ehome/personal-info/personal-info.component';
import { EhomeComponent } from './Ehome/ehome/ehome.component';
import { FilledInfoComponent } from './filled-info/filled-info.component';
import { NavComponent } from './filled-info/nav/nav.component';
import { step2Component } from './filled-info/step2/step2.component'
import { step3Component } from './filled-info/step3/step3.component';
import { step4Component } from './filled-info/step4/step4.component';
import { step5Component } from './filled-info/step5/step5.component';
import { step6Component } from './filled-info/step6/step6.component';
import { ShowDeatilComponent } from './home/employee-profile/show-deatil/show-deatil.component';
import { VisaStatusComponent} from "./Ehome/visa-status/visa-status.component";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginFormComponent,
    HomeComponent,
    HrpageComponent,
    EmployeeProfileComponent,
    EmpVisaStatusManagementComponent,
    HireComponent,
    WildCardComponent,
    Step1Component,
    Step2Component,
    Step3Component,
    Step4Component,
    Step5Component,
    Step6Component,
    RegnavbarComponent,
    RegisterComponent,
    FileuploadComponent,
    Step7Component,
    EnavbarComponent,
    PersonalInfoComponent,
    EhomeComponent,
    FilledInfoComponent,
    NavComponent,
    VisaStatusComponent,

    // filled info
    step2Component,
    step3Component,
    step4Component,
    step5Component,
    step6Component,
    ShowDeatilComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule




  ],
  providers: [RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
