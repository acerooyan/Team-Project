import {Component, OnInit} from '@angular/core';
import {EmployeeProfiles} from "../../entity/EmployeeProfiles";
import {HrEmployeeProfileService} from "../../services/hr-employee-profile.service";

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

  constructor(private hrEmployeeProfileService: HrEmployeeProfileService) {
  }

  ngOnInit(): void {
    this.showData();
  }

  showData(): void {
    this.employeeProfiles = [];
    this.hrEmployeeProfileService.getEmployeeProfiles(this.curPage, this.totalNum, this.maxResult, this.email).subscribe(
      (data: any) => {
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
    // if(!email)
    // {
    //   console.log("cannot get email")
    // }
    // else{
    //   this.hireService.GetAllInfobyemail(email).subscribe(
    //     (data : any) =>{
    //       console.log(data)
    //     },
    //     (error: any) =>
    //     {
    //       if (error.status === 401) {
    //         this.router.navigate(['']);
    //       }
    //       else{
    //         console.log(error);
    //       }
    //     }
    //   )
    // }

  }

}
