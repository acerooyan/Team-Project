import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl} from '@angular/forms';
import { VerifyUserService } from 'src/app/services/verify-user.service';
import { CookieService } from 'ngx-cookie-service';
import {RegUser} from "../../entity/reg-user";
import {RegisterService} from "../../services/register.service";

@Component({
  selector: 'app-step1',
  templateUrl: './step1.component.html',
  styleUrls: ['./step1.component.css']
})
export class Step1Component implements OnInit {
  regUser:RegUser = new RegUser();
  constructor(public registerService: RegisterService, public router: Router) { }

  ngOnInit(): void {
  }
  saveRegUser() {
    this.registerService.setRegUser(this.regUser);
  }
  regUserRegister() {
    console.log(this.regUser);
  }
  changePage() {
    this.router.navigate(["/regnav/step2"]);
  }

}
