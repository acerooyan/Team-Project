import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from './login/login-form/login-form.component';
import {NavbarComponent} from './home/navbar/navbar.component';
import {HrpageComponent} from './home/hrpage/hrpage.component';
import {EmployeeProfileComponent} from './home/employee-profile/employee-profile.component';
import {EmpVisaStatusManagementComponent} from './home/emp-visa-status-management/emp-visa-status-management.component';
import {HireComponent} from './home/hire/hire.component';
import {WildCardComponent} from './wild-card/wild-card.component';
import {Step1Component} from './register/step1/step1.component';
import {Step2Component} from './register/step2/step2.component';
import {Step3Component} from './register/step3/step3.component';
import {Step4Component} from './register/step4/step4.component';
import {Step5Component} from './register/step5/step5.component';
import {Step6Component} from './register/step6/step6.component';
import { Step7Component } from './register/step7/step7.component';
import {RegnavbarComponent} from "./register/regnavbar/regnavbar.component";
import { FileuploadComponent } from './test/fileupload/fileupload.component';
import { EnavbarComponent } from './Ehome/enavbar/enavbar.component';
import { PersonalInfoComponent } from './Ehome/personal-info/personal-info.component';
import { NavComponent } from './filled-info/nav/nav.component';
import { step2Component } from './filled-info/step2/step2.component'
import { step3Component } from './filled-info/step3/step3.component';
import { step4Component } from './filled-info/step4/step4.component';
import { step5Component } from './filled-info/step5/step5.component';
import { step6Component } from './filled-info/step6/step6.component';
import { ShowDeatilComponent } from './home/employee-profile/show-deatil/show-deatil.component';
const routes: Routes = [

  {path: '', component: LoginFormComponent},


  {
    path: 'nav',
    component: NavbarComponent,
    children: [
      {path: 'hr', component: HrpageComponent},
      {path: 'profile', component: EmployeeProfileComponent},
      
     
      {path: 'visa', component: EmpVisaStatusManagementComponent},
      
      
      {path: 'hire', component: HireComponent, 
    
      children: [
        
        {
          path: 'hireNav', component: NavComponent,
          children: [
           
            {path: 'step2', component: step2Component},
            {path: 'step3', component: step3Component},
            {path: 'step4', component: step4Component},
            {path: 'step5', component: step5Component},
            {path: 'step6', component: step6Component},
            
          ]
        },
        
      ]
      
    }
    ]
  },
  {path: 'deatils', component: ShowDeatilComponent},

  {
    path: 'Enav',
    component: EnavbarComponent,
    children: [
      {path: 'Home', component: PersonalInfoComponent},
      {path: 'profile', component: PersonalInfoComponent},
      // {path: 'visa', component: EmpVisaStatusManagementComponent},
     
    ]
  },

 
  {
    path: 'regnav', component: RegnavbarComponent,
    children: [
      {path: 'step1', component: Step1Component},
      {path: 'step2', component: Step2Component},
      {path: 'step3', component: Step3Component},
      {path: 'step4', component: Step4Component},
      {path: 'step5', component: Step5Component},
      {path: 'step6', component: Step6Component},
      
    ]
  },

  {path: 'step7', component: Step7Component},
  {path: 'upload', component:FileuploadComponent},
  
{path:'**', component:WildCardComponent}





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
