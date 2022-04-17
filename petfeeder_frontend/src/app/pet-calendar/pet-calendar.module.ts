import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PetCalendarPageRoutingModule } from './pet-calendar-routing.module';

import { PetCalendarPage } from './pet-calendar.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PetCalendarPageRoutingModule
  ],
  declarations: [PetCalendarPage]
})
export class PetCalendarPageModule {}
