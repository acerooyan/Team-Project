import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validator, Validators} from '@angular/forms';
@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.css']
})
export class HireComponent implements OnInit {
  
  public EmailControl = new FormControl();
  loginForm = new FormGroup({
    email: new FormControl('', Validators.required)
  })

  constructor() { }

  ngOnInit(): void {
  }
  
  get email(){return this.loginForm.get('email')}
  

  GenerateToken(){
   
    console.log(this.EmailControl.value)
  }
}
