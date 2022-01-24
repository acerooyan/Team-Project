import {Component, OnInit} from '@angular/core';
import {EmployeeProfiles} from "../../entity/EmployeeProfiles";
import {HireService} from "../../services/hire.service";
import {HrEmployeeProfileService} from "../../services/hr-employee-profile-service.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {
  public employeeProfiles: EmployeeProfiles[] = [];
  public curPage!: number;
  public totalNum!: number;
  public pages: number[] = [];
  public totalPage!: number;
  public maxResult!: number;
  public email!: string;
  public pager: any = {};

  constructor(private hrEmployeeProfileService: HrEmployeeProfileService, private hire:HireService, private router:Router ) {
  }

  ngOnInit(): void {
    this.showData();
  }

  search(email: string): void {
    this.email = email;
    this.showData();
  }

  showData(): void {
    this.employeeProfiles = [];
    this.hrEmployeeProfileService.getEmployeeProfiles(this.curPage, this.totalNum, this.maxResult, this.email).subscribe(
      (data: any) => {
        console.log(data.employeesDomains);

        // {"employeesDomains":[{"name":"James Huang","startDate":"2012-00-31","visaStatus":"OPT","email":null,"ssn":"12345678"},{"name":"acer yan","startDate":"2013-00-23","visaStatus":"Citizen","email":null,"ssn":"3014"}],"email":null,"curPage":1,"maxResult":2,"totalNum":5}



        for (let i = 0; i < data.employeesDomains.length; i++) {
          let employeeProfile = new EmployeeProfiles();
          employeeProfile.name = data.employeesDomains[i].name;
          employeeProfile.SSN = data.employeesDomains[i].ssn;
          employeeProfile.startDate = data.employeesDomains[i].startDate;
          employeeProfile.visaStatus = data.employeesDomains[i].visaStatus;
          this.employeeProfiles.push(employeeProfile);
        }
        this.curPage = data.curPage;
        this.totalNum = data.totalNum;
        this.maxResult = data.maxResult;
        this.pages = [];
        this.totalPage = Math.ceil(this.totalNum / this.maxResult);
        for (let i = 0; i < this.totalPage; i++) {
          this.pages.push(i + 1);
        }
      }
    );
  }

  pageChanged(page: any): void {
    this.curPage = page;
    console.log(this.curPage);
    this.showData();
  }

  showProfile(email: string | undefined) {
    if(!email) return;

    this.hire.setEmail(email);
  
    this.router.navigate(['deatil']);
  

  }

}
