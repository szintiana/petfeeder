import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { PetComponent } from './pet/pet.component';
import { UserComponent } from './user/user.component';
import { CalendarComponent } from './calendar/calendar.component';

@NgModule({
  declarations: [
    PetComponent,
    UserComponent,
    CalendarComponent
  ],
  imports: [
    CommonModule,
    MainRoutingModule
  ]
})
export class MainModule { }
