import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMyEntity } from 'app/shared/model/my-entity.model';

type EntityResponseType = HttpResponse<IMyEntity>;
type EntityArrayResponseType = HttpResponse<IMyEntity[]>;

@Injectable({ providedIn: 'root' })
export class MyEntityService {
  public resourceUrl = SERVER_API_URL + 'api/my-entities';

  constructor(protected http: HttpClient) {}

  create(myEntity: IMyEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(myEntity);
    return this.http
      .post<IMyEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(myEntity: IMyEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(myEntity);
    return this.http
      .put<IMyEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMyEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMyEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(myEntity: IMyEntity): IMyEntity {
    const copy: IMyEntity = Object.assign({}, myEntity, {
      jhData: myEntity.jhData && myEntity.jhData.isValid() ? myEntity.jhData.format(DATE_FORMAT) : undefined,
      jhZonedDT: myEntity.jhZonedDT && myEntity.jhZonedDT.isValid() ? myEntity.jhZonedDT.toJSON() : undefined,
      jhInstant: myEntity.jhInstant && myEntity.jhInstant.isValid() ? myEntity.jhInstant.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.jhData = res.body.jhData ? moment(res.body.jhData) : undefined;
      res.body.jhZonedDT = res.body.jhZonedDT ? moment(res.body.jhZonedDT) : undefined;
      res.body.jhInstant = res.body.jhInstant ? moment(res.body.jhInstant) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((myEntity: IMyEntity) => {
        myEntity.jhData = myEntity.jhData ? moment(myEntity.jhData) : undefined;
        myEntity.jhZonedDT = myEntity.jhZonedDT ? moment(myEntity.jhZonedDT) : undefined;
        myEntity.jhInstant = myEntity.jhInstant ? moment(myEntity.jhInstant) : undefined;
      });
    }
    return res;
  }
}
