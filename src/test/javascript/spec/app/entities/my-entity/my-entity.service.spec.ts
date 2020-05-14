import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MyEntityService } from 'app/entities/my-entity/my-entity.service';
import { IMyEntity, MyEntity } from 'app/shared/model/my-entity.model';

describe('Service Tests', () => {
  describe('MyEntity Service', () => {
    let injector: TestBed;
    let service: MyEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMyEntity;
    let expectedResult: IMyEntity | IMyEntity[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MyEntityService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MyEntity(0, 0, 'AAAAAAA', 0, currentDate, false, 0, 0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            jhData: currentDate.format(DATE_FORMAT),
            jhZonedDT: currentDate.format(DATE_TIME_FORMAT),
            jhInstant: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MyEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            jhData: currentDate.format(DATE_FORMAT),
            jhZonedDT: currentDate.format(DATE_TIME_FORMAT),
            jhInstant: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            jhData: currentDate,
            jhZonedDT: currentDate,
            jhInstant: currentDate
          },
          returnedFromService
        );

        service.create(new MyEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MyEntity', () => {
        const returnedFromService = Object.assign(
          {
            jhPoleID: 1,
            jhPoleNazwa: 'BBBBBB',
            jhJakasLiczba: 1,
            jhData: currentDate.format(DATE_FORMAT),
            jhBoolean: true,
            jhLong: 1,
            jhBigDecimal: 1,
            jhDouble: 1,
            jhZonedDT: currentDate.format(DATE_TIME_FORMAT),
            jhInstant: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            jhData: currentDate,
            jhZonedDT: currentDate,
            jhInstant: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MyEntity', () => {
        const returnedFromService = Object.assign(
          {
            jhPoleID: 1,
            jhPoleNazwa: 'BBBBBB',
            jhJakasLiczba: 1,
            jhData: currentDate.format(DATE_FORMAT),
            jhBoolean: true,
            jhLong: 1,
            jhBigDecimal: 1,
            jhDouble: 1,
            jhZonedDT: currentDate.format(DATE_TIME_FORMAT),
            jhInstant: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            jhData: currentDate,
            jhZonedDT: currentDate,
            jhInstant: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MyEntity', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
