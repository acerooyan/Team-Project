import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl} from '@angular/forms';
import { VerifyUserService } from 'src/app/services/verify-user.service';
import { CookieService } from 'ngx-cookie-service';
import {RegUser} from "../../entity/reg-user";
import {RegisterService} from "../../services/register.service";
import { errors } from 'src/app/entity/errors';
@Component({
  selector: 'app-step1',
  templateUrl: './step1.component.html',
  styleUrls: ['./step1.component.css']
})
export class Step1Component implements OnInit {
  regUser:RegUser = new RegUser();
  errorObject: errors = {code:"", message:""}
  constructor(public registerService: RegisterService, public router: Router) { }

  ngOnInit(): void {
  }
  saveRegUser() {
    this.registerService.setRegUser(this.regUser).subscribe(
      {
        next: data => {
            console.log(data);
            this.router.navigate(["/regnav/step2"]);
            
        },
        error: e => {
           console.log(e);
           this.errorObject.code = e.status;
           this.errorObject.message = e.error;
            
        }
    });
  }
  regUserRegister() {
    console.log(this.regUser);
  }
 

}
