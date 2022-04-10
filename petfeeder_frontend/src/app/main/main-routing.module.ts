import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import { UserComponent } from './user/user.component';
import { PetComponent } from './pet/pet.component';

const routes: Routes = [
  {path: "", component: CalendarComponent },
  {path: "me", component: UserComponent },
  {path: "pets", component: PetComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
