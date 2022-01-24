import { Component, OnInit ,Input} from '@angular/core';
import{HireService} from 'src/app/services/hire.service';
import{personalInfo, employee, address,EmergencyContact,ConatctInfo} from 'src/app/PersonalnfoEntity/Info'
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-show-deatil',
  templateUrl: './show-deatil.component.html',
  styleUrls: ['./show-deatil.component.css']
})
export class ShowDeatilComponent implements OnInit {


  
  public personalInfo: personalInfo = {} ;
  public employee: employee = {};
  public Address:address[] = [];
  public EmergencyContact: EmergencyContact = {};
  public ConatctInfo: ConatctInfo = {};
  public fileNameArray:string[] = [];
  public avator?: string;



  constructor(private service: HireService, private router: Router) { }

  ngOnInit(): void {



 
 
    this.service.getDetail().subscribe(
      (data: any) =>{
        
        
        

        //get personalInfo
        this.personalInfo = {fullName:data.personalInfoDomain.fullName, dob: data.personalInfoDomain.dob, age: data.personalInfoDomain.age, gender: data.personalInfoDomain.gender, ssn: data.personalInfoDomain.ssn }; 

        //get address[]
        for (let i = 0; i < data.addressDomains.length; i++) {
            this.Address.push(data.addressDomains[i])
         }
        
         // get employmentDomain
         this.employee = {workAuthorization:data.employmentDomain.workAuthorization, workAuthorizationSD: data.workAuthorizationStartDate
          , workAuthorizationED: data.employmentDomain.workAuthorizationEndDate , title: data.employmentDomain.title  };
          
          
         //get ContactInfoDomain
         this.ConatctInfo = {cellPhone: data.contactInfoDomain.cellPhone, alternatePhone: data.contactInfoDomain.alternatePhone, email:data.contactInfoDomain.email};

         //get EmergencyContactDomain
          this.EmergencyContact = {fullName: data.emergencyContactDomain.fullName, relationship:data.emergencyContactDomain.relationship, cellPhone: data.emergencyContactDomain.cellPhone, email:data.emergencyContactDomain.email}

          //get documentDomain
          this.fileNameArray = data.documentDomain.fileName;

          //get avator 
          this.avator = data.avatarDomain.avatar;
          
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

}
