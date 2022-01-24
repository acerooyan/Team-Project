import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Duration, DURATIONS} from "../../entity/Duration";
import {HireService} from "../../services/hire.service";
import{address,applicationWorkflow , alldata} from 'src/app/PersonalnfoEntity/Info'
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
  public Comments:string = "";

  public DecisonEmail = "";
  public Address:address[] = [];
  
  public alldata: alldata = new alldata();
  public EmailControl = new FormControl();
  public hidden = false;
  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    duration: new FormControl('', Validators.required)
  })

  constructor(private hireService: HireService, private router: Router ) {
  }

  ngOnInit(): void {
    this.hidden = false;
    this.workflows = [];
    this.durations = DURATIONS;
    this.selectedDuration = this.durations[0];
    // = {type:"onBoarding", status: "pending", email: "jamesh970327@gmail.com", comments:undefined}
    
//     email: "aceyanr0830@gmail.com"
// fullName: "acer yan"
// status: "complete"
// type: "onBoarding"
// workAuthorization: "OPT"
    
    this.hireService.getWorkflow().subscribe(
      (data: any) =>{

        
        for(let d of data)
        {

          let workflow : applicationWorkflow = {type:d.type, status: d.status, email: d.email,  name:d.fullName, workAuth:d.workAuthorization};
          this.workflows.push(workflow);
        }
       
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


  makeDecison(status: string)
  {
      console.log(this.DecisonEmail);
      console.log(this.Comments)
      this.hireService.ChangeWorkflow(this.DecisonEmail, status).subscribe(
        (data: any) =>{

          console.log("change workflow works");

          this.hireService.sendEmail(this.DecisonEmail, status, this.Comments).subscribe(
            (data: any) =>{

                window.alert("Decsion email sent!");
                this.hide();
                this.ngOnInit();
             
             
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
         
        },
        (error: any) =>
        {
          
        }
      )
  }


  onchange()
  {
    this.hidden = !this.hidden;
  }

  hide(){
     this.onchange();
    this.router.navigate(['nav/hire']);
  }
 
  showProfile(email:string|undefined){
     
      this.onchange();
      if(!email)
      {
        
        console.log("cannot get email")
      }
      else{

        this.DecisonEmail = email;
        this.hireService.GetAllInfobyemail(email).subscribe(
          (data : any) =>{
            console.log(data)


                //get personalInfo
                this.alldata.personalInfo = {fullName:data.personalInfoDomain.fullName, dob: data.personalInfoDomain.dob, age: data.personalInfoDomain.age, gender: data.personalInfoDomain.gender, ssn: data.personalInfoDomain.ssn }; 
                
            //get address[]
            for (let i = 0; i < data.addressDomains.length; i++) {
                this.Address.push(data.addressDomains[i])
            }

            this.alldata.Address = this.Address;
              
            
            // get employmentDomain
            this.alldata.employee = {workAuthorization:data.employmentDomain.workAuthorization, workAuthorizationSD: data.workAuthorizationStartDate
              , workAuthorizationED: data.employmentDomain.workAuthorizationEndDate , title: data.employmentDomain.title  };
              
              
            //get ContactInfoDomain
            this.alldata.ConatctInfo = {cellPhone: data.contactInfoDomain.cellPhone, alternatePhone: data.contactInfoDomain.alternatePhone, email:data.contactInfoDomain.email};

            //get EmergencyContactDomain
            this.alldata.EmergencyContact = {fullName: data.emergencyContactDomain.fullName, relationship:data.emergencyContactDomain.relationship, cellPhone: data.emergencyContactDomain.cellPhone, email:data.emergencyContactDomain.email}

              //get documentDomain
              this.alldata.fileNameArray = data.documentDomain.fileName;

              //get avator 
              this.alldata.avator = data.avatarDomain.avatar;

              this.hireService.setAlldata(this.alldata);

              this.router.navigate(['nav/hire/hireNav/step2']);
             
          },
          (error: any) =>
            {
              if (error.status === 401) {
               this.router.navigate([""]);
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