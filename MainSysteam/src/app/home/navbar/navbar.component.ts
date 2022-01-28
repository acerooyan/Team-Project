import { Component, OnInit } from '@angular/core';
import {VerifyUserService} from 'src/app/services/verify-user.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

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
