import { Component, OnInit ,Input} from '@angular/core';
import{PersonalInfoService} from 'src/app/services/personal-info.service';
import{personalInfo, employee, Address,EmergencyContact,ConatctInfo} from 'src/app/PersonalnfoEntity/Info'
import { error } from '@angular/compiler/src/util';
@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {


  public personalInfo: personalInfo = {} ;
  public employee: employee = {};
  public Address:any[] = [];
  public EmergencyContact: EmergencyContact = {};
  public ConatctInfo: ConatctInfo = {};


  change_personalInfo = false;
  change_employee = false;
  change_Address = false;
  change_EmergencyContact= false;
  change_ConatctInfo= false;
  
  constructor(private service: PersonalInfoService) { }

  // PersonalInfoDomain personalInfoDomain;
  // AddressDomain[] addressDomains;
  // EmploymentDomain employmentDomain;
  // EmergencyContactDomain emergencyContactDomain;
  // ContactInfoDomain contactInfoDomain;
  // DocumentDomain documentDomain;
//   {
//     "workAuthorization": null,
//     "workAuthorizationStartDate": "today",
//     "workAuthorizationEndDate": "tmw",
//     "title": null
// }

  ngOnInit(): void {

    this.service.GetAllInfo().subscribe(
      (data: any) =>{
        console.log(data);
        console.log(data.addressDomains);

        //get personalInfo
        this.personalInfo = {fullName:"Jimmy",Dob: "Yan",Age: "23",Gender: "male",SSN: "1234" }; 

        //get address[]
        for (let i = 0; i < data.addressDomains.length; i++) {
            this.Address.push(data.addressDomains[i])
         }
        
         // get employmentDomain
         this.employee = {workAuthorization:data.employmentDomain.workAuthorization, workAuthorizationSD: data.workAuthorizationStartDate
          , workAuthorizationED: data.employmentDomain.workAuthorizationEndDate , title: data.employmentDomain.title  };
          
          
         //get ContactInfoDomain
         this.ConatctInfo = {cellPhone: "6264923795", Alternatephone: "6267578821", email:"acer0830@hotmail.com"};

         //get EmergencyContactDomain
          this.EmergencyContact = {fullName: "James", RelationShip:"Freind", Cellphone: "911", email:"james123@gmail.com"}

      },
      (error: any)=>{
        console.log(error);

      }

    )
  }



  edit(url:string, info:any)
  {
    this.service.EditInfo(url, info).subscribe(
      (data:any)=>{
        console.log(data);
      },
      (error :any)=>{
        console.log(error);
      }
      
    )


  }

  change_personalInfo1(){
    this.change_personalInfo =  !this.change_personalInfo;
  }


 
  change_employee1()
  {
    this.change_employee = !this.change_employee;
  }


  change_Address1()
  {
    this.change_Address  = !this.change_Address ;
  }


  change_EmergencyContact1()
  {
    this.change_EmergencyContact = !this.change_EmergencyContact;
  }

  change_ConatctInfo1()
  {
    this.change_ConatctInfo = !this.change_ConatctInfo;
  }
  
  
  


}
