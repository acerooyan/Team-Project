import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { NavbarComponent } from './home/navbar/navbar.component';
import { HrpageComponent } from './home/hrpage/hrpage.component';
import { EmployeeProfileComponent } from './home/employee-profile/employee-profile.component';
import { EmpVisaStatusManagementComponent } from './home/emp-visa-status-management/emp-visa-status-management.component';
import { HireComponent } from './home/hire/hire.component';
const routes: Routes = [

{path: '', component:LoginFormComponent},
{path: 'nav', 
component:NavbarComponent,
children: [
  {path: 'hr', component: HrpageComponent},
  {path: 'profile', component:EmployeeProfileComponent},
  {path:'visa', component:EmpVisaStatusManagementComponent},
  {path:'hire', component:HireComponent}
  
]
},





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
