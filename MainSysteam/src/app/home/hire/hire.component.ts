import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Duration, DURATIONS} from "../../domain/Duration";
import {HireService} from "../../services/hire.service";

@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.css']
})
export class HireComponent implements OnInit {

  public durations!: any[];
  public selectedDuration!: Duration;

  public EmailControl = new FormControl();
  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    duration: new FormControl('', Validators.required)
  })

  constructor(private hireService: HireService) {
  }

  ngOnInit(): void {
    this.durations = DURATIONS;
    this.selectedDuration = this.durations[0];
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
