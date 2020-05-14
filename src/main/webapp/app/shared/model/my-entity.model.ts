import { Moment } from 'moment';

export interface IMyEntity {
  id?: number;
  jhPoleID?: number;
  jhPoleNazwa?: string;
  jhJakasLiczba?: number;
  jhData?: Moment;
  jhBoolean?: boolean;
  jhLong?: number;
  jhBigDecimal?: number;
  jhDouble?: number;
  jhZonedDT?: Moment;
  jhInstant?: Moment;
}

export class MyEntity implements IMyEntity {
  constructor(
    public id?: number,
    public jhPoleID?: number,
    public jhPoleNazwa?: string,
    public jhJakasLiczba?: number,
    public jhData?: Moment,
    public jhBoolean?: boolean,
    public jhLong?: number,
    public jhBigDecimal?: number,
    public jhDouble?: number,
    public jhZonedDT?: Moment,
    public jhInstant?: Moment
  ) {
    this.jhBoolean = this.jhBoolean || false;
  }
}
