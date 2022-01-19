import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from './login/login-form/login-form.component';
import {NavbarComponent} from './home/navbar/navbar.component';
import {Step1Component} from './register/step1/step1.component';
import {Step2Component} from './register/step2/step2.component';
import {Step3Component} from './register/step3/step3.component';
import {Step4Component} from './register/step4/step4.component';
import {Step5Component} from './register/step5/step5.component';
import {Step6Component} from './register/step6/step6.component';
import {RegisterComponent} from "./register/register.component";
import {RegnavbarComponent} from "./register/regnavbar/regnavbar.component";

const routes: Routes = [

  {path: '', component: RegisterComponent},
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
