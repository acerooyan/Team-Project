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

// export interface Address{
//     AddressLine1?: string,
//     workAuthorizationSD?: string,
//     workAuthorizationED?: string,
//     title?:string;
// }

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
