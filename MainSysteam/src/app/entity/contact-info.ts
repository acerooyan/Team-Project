import {Address} from "./address";

export class ContactInfo {

  cellphone!: String;
  alternatePhone!: String;
  email!: String;
  // addressDaoList!:[
  //   addressLine1!: "";
  //   addressLine2!:"";
  //   city!: "";
  //   status!: "";
  //   Zipcode!: "";
  // ]
  addressDaoList: any[] = [{
    address:Address
  }]

//public addresses: any[] = [{
//     address: '',
//     street: '',
//     city: '',
//     country: ''
//   }];
}
