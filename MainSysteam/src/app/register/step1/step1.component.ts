import { Component, OnInit } from '@angular/core';
import {RegUser} from "../../entity/reg-user";
import {ActivatedRoute, Router} from '@angular/router';
import {RegisterService} from "../../services/register.service";
import { errors } from 'src/app/entity/errors';
import {Step1Service} from "../../services/step1.service";
@Component({
  selector: 'app-step1',
  templateUrl: './step1.component.html',
  styleUrls: ['./step1.component.css']
})
export class Step1Component implements OnInit {
  public token!: string;
  regUser:RegUser = new RegUser();
  errorObject: errors = {code:"", message:""}
  constructor(private step1Service: Step1Service,public registerService: RegisterService, public router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.token = params['registrationToken'];
      }
    );
    this.step1Service.validateToken(this.token).subscribe(
      (data: any) => {
        this.regUser.email = data;
      }, (error) => {
        // @ts-ignore
        this.router.navigateByUrl('/**?message=' + error.error);
      }
    );
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
