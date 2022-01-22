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

export interface Address{
    AddressLine1?: string,
    workAuthorizationSD?: string,
    workAuthorizationED?: string,
    title?:string;
}

export interface EmergencyContact{
    fullName?: string,
    RelationShip?: string,
    Cellphone?: string,
    email?:string;
}

export interface ConatctInfo{
    cellPhone?: string,
    Alternatephone?: string,
    email?: string,
   
}