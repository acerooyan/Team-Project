import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl} from '@angular/forms';
import { VerifyUserService } from 'src/app/services/verify-user.service';
import { errors } from 'src/app/entity/errors';
import { PersonalInfoService } from 'src/app/services/personal-info.service';
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  public nameControl = new FormControl();
  public PasswordControl = new FormControl();
  public loginAsHr: boolean = false;
  errorObject: errors = {code:"", message:""}
  
  
  constructor(private router: Router, private service: VerifyUserService,private personalInfoService:PersonalInfoService  ) { }

  ngOnInit(): void {
   
  }

  changerole(){
    this.loginAsHr = !this.loginAsHr;
   
  }

  verify(){
   
    this.service.verify(this.nameControl.value, this.PasswordControl.value, this.loginAsHr).subscribe(
      {
        next: data => {
            console.log(data);
            if(this.loginAsHr)
            {
              // go hr home 
              this.router.navigate(['nav/hr']);
            }
            else
            { 

              //this.router.navigate(['Enav/profile'])
              //if empoly call 8080 controller -> {200 : homepage , }
              this.personalInfoService.IsApproved().subscribe(
                {
                  next:data1 =>{
                    console.log(data1);
                    this.router.navigate(['Enav/profile'])
                  },

                  error: e1 =>{
                    this.errorObject.code = e1.status;
                    this.errorObject.message = e1.error;
                  }
                }
              )
                
            }
                 
        },
        error: e => {
            
           this.errorObject.code = e.status;
           this.errorObject.message = e.error;
            
        }
    })
     

    
  }


 
}
