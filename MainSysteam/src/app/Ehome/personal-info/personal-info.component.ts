import { Component, OnInit ,Input} from '@angular/core';
import{PersonalInfoService} from 'src/app/services/personal-info.service';
import{personalInfo, employee, address,EmergencyContact,ConatctInfo} from 'src/app/PersonalnfoEntity/Info'
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {


  public personalInfo: personalInfo = {} ;
  public employee: employee = {};
  public Address:address[] = [];
  public EmergencyContact: EmergencyContact = {};
  public ConatctInfo: ConatctInfo = {};
  public fileNameArray:string[] = [];
  public avator?: string;

  fileInfos?: Observable<any>;
  change_personalInfo = false;
  change_employee = false;
  change_Address = false;
  change_EmergencyContact= false;
  change_ConatctInfo= false;
  
  constructor(private service: PersonalInfoService, private router: Router) { }

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

    this.change_personalInfo = false;
    this.change_employee = false;
    this.change_Address = false;
    this.change_EmergencyContact= false;
    this.change_ConatctInfo= false;
    this.service.GetAllInfo().subscribe(
      (data: any) =>{
        console.log(data);
        
        

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


  
  displayStyle = "none";
  
  openPopup() {
    this.displayStyle = "block";
  }
  closePopupWithYes() {
    this.ngOnInit();
    this.displayStyle = "none";

  }

  closePopupWithNo() {
    this.displayStyle = "none";
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
