import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMyEntity, MyEntity } from 'app/shared/model/my-entity.model';
import { MyEntityService } from './my-entity.service';

@Component({
  selector: 'jhi-my-entity-update',
  templateUrl: './my-entity-update.component.html'
})
export class MyEntityUpdateComponent implements OnInit {
  isSaving = false;
  jhDataDp: any;

  editForm = this.fb.group({
    id: [],
    jhPoleID: [],
    jhPoleNazwa: [],
    jhJakasLiczba: [],
    jhData: [],
    jhBoolean: [],
    jhLong: [],
    jhBigDecimal: [],
    jhDouble: [],
    jhZonedDT: [],
    jhInstant: []
  });

  constructor(protected myEntityService: MyEntityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ myEntity }) => {
      if (!myEntity.id) {
        const today = moment().startOf('day');
        myEntity.jhZonedDT = today;
        myEntity.jhInstant = today;
      }

      this.updateForm(myEntity);
    });
  }

  updateForm(myEntity: IMyEntity): void {
    this.editForm.patchValue({
      id: myEntity.id,
      jhPoleID: myEntity.jhPoleID,
      jhPoleNazwa: myEntity.jhPoleNazwa,
      jhJakasLiczba: myEntity.jhJakasLiczba,
      jhData: myEntity.jhData,
      jhBoolean: myEntity.jhBoolean,
      jhLong: myEntity.jhLong,
      jhBigDecimal: myEntity.jhBigDecimal,
      jhDouble: myEntity.jhDouble,
      jhZonedDT: myEntity.jhZonedDT ? myEntity.jhZonedDT.format(DATE_TIME_FORMAT) : null,
      jhInstant: myEntity.jhInstant ? myEntity.jhInstant.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const myEntity = this.createFromForm();
    if (myEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.myEntityService.update(myEntity));
    } else {
      this.subscribeToSaveResponse(this.myEntityService.create(myEntity));
    }
  }

  private createFromForm(): IMyEntity {
    return {
      ...new MyEntity(),
      id: this.editForm.get(['id'])!.value,
      jhPoleID: this.editForm.get(['jhPoleID'])!.value,
      jhPoleNazwa: this.editForm.get(['jhPoleNazwa'])!.value,
      jhJakasLiczba: this.editForm.get(['jhJakasLiczba'])!.value,
      jhData: this.editForm.get(['jhData'])!.value,
      jhBoolean: this.editForm.get(['jhBoolean'])!.value,
      jhLong: this.editForm.get(['jhLong'])!.value,
      jhBigDecimal: this.editForm.get(['jhBigDecimal'])!.value,
      jhDouble: this.editForm.get(['jhDouble'])!.value,
      jhZonedDT: this.editForm.get(['jhZonedDT'])!.value ? moment(this.editForm.get(['jhZonedDT'])!.value, DATE_TIME_FORMAT) : undefined,
      jhInstant: this.editForm.get(['jhInstant'])!.value ? moment(this.editForm.get(['jhInstant'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMyEntity>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
