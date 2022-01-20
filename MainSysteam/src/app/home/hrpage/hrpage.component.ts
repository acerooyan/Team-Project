import { Component, OnInit } from '@angular/core';
import { VerifyUserService } from 'src/app/services/verify-user.service';
@Component({
  selector: 'app-hrpage',
  templateUrl: './hrpage.component.html',
  styleUrls: ['./hrpage.component.css']
})
export class HrpageComponent implements OnInit {
  data: Array<any>;
  headers = ["NO.","Name","Work Authorization","Application Type", "Status"]
  public testarray = [
    {
      "Name":"Joey",
      "Work Authorization": "H1B",
      "Application Type":"Onboarding",
      "Status":"Complete"},
    {
      "Name":"Jason",
      "Work Authorization": "H1B",
      "Application Type":"Onboarding",
      "Status":"Complete"},
  ]
  constructor(private service: VerifyUserService) {
    this.data = [
      {
        Name:"Joey",
        Work_Authorization: "H1B",
        Application_Type:"Onboarding",
        Status:"Complete"},
      {
        Name:"Jason",
        Work_Authorization: "H1B",
        Application_Type:"Onboarding",
        Status:"Complete"},
    ]
  }


  ngOnInit(): void {

  
   
      this.service.HrHome().subscribe(
        {
          next: data => {
              console.log(data);
              
          },
          error: e => {
              
            console.log(e);
              
          }
      })
       
  
      
    
  

  }

}
