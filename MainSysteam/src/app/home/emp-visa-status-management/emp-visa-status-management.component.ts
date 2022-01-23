import { Component, OnInit } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
import {Address} from "../../entity/address";
import {Router} from "@angular/router";

export class Status{
  fullName?:String;
  visa?:String;
  startDate?:String;
  endDate?:String;
  dayLeft?:String;
  documents?:string[];
  currentStep?:String;
  nextStep?:String;
  comment?:String;
  email?:String;
  workflowStatus?:String;
}


@Component({
  selector: 'app-emp-visa-status-management',
  templateUrl: './emp-visa-status-management.component.html',
  styleUrls: ['./emp-visa-status-management.component.css']
})
export class EmpVisaStatusManagementComponent implements OnInit {
  statusList :Status[] = [] ;
  test=[
    {fullName: "sy",
    visa:"OPT",
    startDate:"1997",
    endDate:"2021",
    dayLeft:"10"
  },
    {fullName: "yy",
      visa:"OPT",
      startDate:"1999",
      endDate:"2030",
      dayLeft:"20"
    },
  ]
  private statusInfoUrl = "";
  constructor(private httpClient: HttpClient,private router: Router) { }
  changedName?:String;
  ngOnInit(): void {
    this.getStatusInfo().subscribe(
      (data:any) =>{
        console.log(data);
        for(let i=0; i < data.statusDomains.length; i++) {
          this.statusList.push(data.statusDomains[i]);
        }
      },
      (error: any)=>{
        if (error.status === 401) {
          this.router.navigate(['']);
        }
        else{
          console.log(error);
        }

      }
    )
  }
  getStatusInfo():Observable<any> {
    return this.httpClient.get(this.statusInfoUrl);
  }
  public index=0
  showDetail = [false,false];
getDetail(i:number) {
  this.index = i;
this.showDetail[i] = !this.showDetail[i];

}
getArrayIndex(){
  return this.showDetail[this.index];
}

}

