import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { NavbarComponent } from './home/navbar/navbar.component';
import { HrpageComponent } from './home/hrpage/hrpage.component';
import { EmployeeProfileComponent } from './home/employee-profile/employee-profile.component';
import { EmpVisaStatusManagementComponent } from './home/emp-visa-status-management/emp-visa-status-management.component';
import { HireComponent } from './home/hire/hire.component';
import { WildCardComponent } from './wild-card/wild-card.component';
import {Step1Component} from './register/step1/step1.component';
import {Step2Component} from './register/step2/step2.component';
import {Step3Component} from './register/step3/step3.component';
import {Step4Component} from './register/step4/step4.component';
import {Step5Component} from './register/step5/step5.component';
import {Step6Component} from './register/step6/step6.component';
import {RegisterComponent} from "./register/register.component";
import {RegnavbarComponent} from "./register/regnavbar/regnavbar.component";
import { FileuploadComponent } from './test/fileupload/fileupload.component';


const routes: Routes = [

{path: '', component:LoginFormComponent},


{
path: 'nav', 
component:NavbarComponent,
children: [
  {path: 'hr', component: HrpageComponent},
  {path: 'profile', component:EmployeeProfileComponent},
  {path:'visa', component:EmpVisaStatusManagementComponent},
  {path:'hire', component:HireComponent}
  ]
},

{path: 'regnav', component: RegisterComponent},
  {
    path: 'regnav', component: RegnavbarComponent,
    children: [
      {path: 'step1', component: Step1Component},
      {path: 'step2', component: Step2Component},
      {path: 'step3', component: Step3Component},
      {path: 'step4', component: Step4Component},
      {path: 'step5', component: Step5Component},
      {path: 'step6', component: Step6Component}
    ]
  },

  {path: 'upload', component:FileuploadComponent},
  
{path:'**', component:WildCardComponent}





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
