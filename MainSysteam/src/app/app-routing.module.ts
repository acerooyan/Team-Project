import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { NavbarComponent } from './home/navbar/navbar.component';
const routes: Routes = [

{path: '', component:LoginFormComponent},
{path: 'nav', component:NavbarComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
