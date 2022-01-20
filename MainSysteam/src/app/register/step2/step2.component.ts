import { Component, OnInit } from '@angular/core';
import {BasicInfo} from "../../entity/basic-info";
import {RegisterService} from "../../services/register.service";
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {fn} from "@angular/compiler/src/output/output_ast";

@Component({
  selector: 'app-step2',
  templateUrl: './step2.component.html',
  styleUrls: ['./step2.component.css']
})
export class Step2Component implements OnInit {
  basicInfo: BasicInfo = new BasicInfo();
  //
  // exform! : FormGroup;
  constructor(public registerService: RegisterService, public router: Router) { }

  ngOnInit(): void {
    // this.exform = new FormGroup({
    //   'firstname' : new FormControl(null, Validators.required),
    //   'lastname' : new FormControl(null, Validators.required),
    //   'SSN' : new FormControl(null, Validators.required),
    //   'dateofbirth' : new FormControl(null, Validators.required),
    //   'gender' : new FormControl(null, Validators.required),
      // 'l' : new FormControl(null, [Validators.required, Validators.email]),
      // 'phone' : new FormControl(
      //   null,
      //   [
      //     Validators.required,
      //     Validators.pattern('^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$')
      //   ]),
      // 'message' : new FormControl(null, [Validators.required, Validators.minLength(10)])
    // });
  }
  saveBasicInfo() {
    this.registerService.setBasicInfo(this.basicInfo);
  }
  basicInfoRegister() {
    console.log(this.registerService.getRegUser());
    console.log(this.basicInfo);
    // this.registerService.registerBasicInfo(this.basicInfo).subscribe(data=>{
    //   alert("Successful")
    // },error=>alert("Error"));


  }

  changePage() {
    this.router.navigate(["/regnav/step3"]);
  }
}
