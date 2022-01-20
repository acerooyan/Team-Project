import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RegUser} from "../../entity/reg-user";
import {Step1Service} from "../../services/step1.service";

@Component({
  selector: 'app-step1',
  templateUrl: './step1.component.html',
  styleUrls: ['./step1.component.css']
})
export class Step1Component implements OnInit {
  public token!: string;
  regUser: RegUser = new RegUser();

  constructor(private step1Service: Step1Service, public router: Router, private route: ActivatedRoute) {
  }

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
    this.step1Service.addUser(this.regUser).subscribe(
      (data: any) => {
        console.log(data);
      }, (error) => {
        console.log(error);
        // @ts-ignore
        this.router.navigateByUrl('/**?message=' + error.error);
      }
    );
  }

  regUserRegister() {
    console.log(this.regUser);
  }

  changePage() {
    this.router.navigate(["/regnav/step2"]);
  }

}
