export interface personalInfo{
    fullName?: string,
    dob?: string,
    age?: string,
    gender?: string,
    ssn?: string

   
}

export interface employee{
    workAuthorization?: string,
    workAuthorizationSD?: string,
    workAuthorizationED?: string,
    title?:string
    
}


export interface EmergencyContact{
    fullName?: string,
    relationship?: string,
    cellPhone?: string,
    email?:string;
}

export interface ConatctInfo{
    cellPhone?: string,
    alternatePhone?: string,
    email?: string,
   
}


export interface address{
    addressLine1?: string,
    addressLine2?: string,
    city?: string,
    state?: string,
    zipcode?: string,
   
}

export interface applicationWorkflow
{
    status?:string,
    type? : string,
    comments? : string
    email?:string,
    name? :string
    workAuth:string
}

export class alldata{
    public personalInfo: personalInfo = {} ;
    public employee: employee = {};
    public Address:address[] = [];
    public EmergencyContact: EmergencyContact = {};
    public ConatctInfo: ConatctInfo = {};
    public fileNameArray:string[] = [];
    public avator?: string;
  }
  

export class HomeProfile{
    fullName?:string
    visa? : string
    startDate? : string
    endDate?:string
    dayLeft? :string
    email?:string

}
