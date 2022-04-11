import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PetEditPage } from './pet-edit.page';

const routes: Routes = [
  {
    path: '',
    component: PetEditPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PetEditPageRoutingModule {}
