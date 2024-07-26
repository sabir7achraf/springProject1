export interface Payments{
  payementId : bigint,
  datepayement: Date,
  payementEtat :PayementEtat;
  payementType :PayementType;
  file:string;

}
export enum PayementEtat{
  VALIDER,REFUSER
}
export enum PayementType{
  CARTE,CASH,CHECK
}

