import { Component, OnInit } from '@angular/core';
import {VerifyUserService} from 'src/app/services/verify-user.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-regnavbar',
  templateUrl: './regnavbar.component.html',
  styleUrls: ['./regnavbar.component.css']
})
export class RegnavbarComponent implements OnInit {

  constructor(private VerifyUserService: VerifyUserService, private router: Router) { }

  ngOnInit(): void {


  }


  logout(){
    this.VerifyUserService.logout().subscribe(
      (data:any) =>{
        this.router.navigate(['']);
      },
      (error: any) =>{
        console.log(error);
      }
    )
    
  }

}
