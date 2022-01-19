import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl} from '@angular/forms';
import { VerifyUserService } from 'src/app/services/verify-user.service';
import { CookieService } from 'ngx-cookie-service';

;

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  public nameControl = new FormControl();
  public PasswordControl = new FormControl();


  constructor(private router: Router, private service: VerifyUserService , private cookie:CookieService) { }

  ngOnInit(): void {
  }



  verify(){
    // this.cookie.set("JWT-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjIyNDQiLCJpZCI6MSwicm9sZSI6IkhSIiwiaWF0IjoxNjQyNDIwNjg4LCJleHAiOjE2NDI0MjA4MDh9.-x36iJRG8VbcyTadGx6ykJpwwVspF7k7UoVAPQATO1Y"  )
  //   document.cookie =
  //  'path=em/';
    this.service.verify(this.nameControl.value, this.PasswordControl.value).subscribe(
      {
        next: data => {
            console.log(data);
        },
        error: e => {
            
            // if(e.status == 200)
            // {
            //   this.cookie.set("JWT-TOKEN",e.error.text  )
            // }
            console.log('There was an error!', e );
            
        }
    })
     

    
  }


  
  // verify(){
    
  //   this.service.verify(this.nameControl.value, this.PasswordControl.value).subscribe(
  //     (data:any) =>{
  //       this.cookie.set("1","12323232");
  //       console.log('no error')
  //       console.log(data);
        
        
  //     },
  //     (error) =>{
  //       console.log('error')
  //       console.log(error);
  //     }

  //   );
     

    
  // }

  // RedirectTohome(){
  //   this.router.navigate(['nav']);
  // }
}
