import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Duration, DURATIONS} from "../../entity/Duration";
import {HireService} from "../../services/hire.service";
import{personalInfo, employee, address,EmergencyContact,ConatctInfo,applicationWorkflow} from 'src/app/PersonalnfoEntity/Info'

import { Router } from '@angular/router';

@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.css']
})
export class HireComponent implements OnInit {

  public workflows: applicationWorkflow[] = [];
  public durations!: any[];
  public selectedDuration!: Duration;
  
  public EmailControl = new FormControl();
  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    duration: new FormControl('', Validators.required)
  })

  constructor(private hireService: HireService, private router: Router ) {
  }

  ngOnInit(): void {
    this.durations = DURATIONS;
    this.selectedDuration = this.durations[0];
    // = {type:"onBoarding", status: "pending", email: "jamesh970327@gmail.com", comments:undefined}
    let workflow : applicationWorkflow = {type:"onBoarding", status: "pending", email: "jamesh970327@gmail.com", comments:undefined, name:"james"};

    this.workflows.push(workflow);
    // this.hireService.getWorkflow().subscribe(
    //   (data: any) =>{

    //     console.log(data);
        
    //   },
    //   (error: any) =>
    //   {
    //     if (error.status === 401) {
    //       this.router.navigate(['']);
    //     }
    //     else{
    //       console.log(error);
    //     }
    //   }
    // )
  }

  showProfile(email:string|undefined){
      if(!email)
      {
        console.log("cannot get email")
      }
      else{
        this.hireService.GetAllInfobyemail(email).subscribe(
          (data : any) =>{
            console.log(data)
          },
          (error: any) =>
            {
              if (error.status === 401) {
                this.router.navigate(['']);
              }
              else{
                console.log(error);
              }
            }
        )
      }
     
  }


  get email() {
    return this.loginForm.get('email')
  }

  onSelect(duration: Duration): void {
    this.selectedDuration = duration;
  }


  GenerateToken() {
    this.hireService.addToken(this.EmailControl.value,this.selectedDuration.value).subscribe(
      (data:any)=>{
        console.log(data);
      },(error) => {
        console.log(error);
      }
    )
  }
}