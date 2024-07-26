export class User {
  id ?:bigint;
  firstName? : string;
  lastName ?: string;
  email? : string ;
  validation? : boolean;
  code ?: bigint;
  image ?: string;
  password!:string;
}

