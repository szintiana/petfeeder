import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PetCalendarPage } from './pet-calendar.page';

const routes: Routes = [
  {
    path: '',
    component: PetCalendarPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PetCalendarPageRoutingModule {}
