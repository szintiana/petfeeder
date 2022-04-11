import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PetListPage } from './pet-list.page';

const routes: Routes = [
  {
    path: '',
    component: PetListPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PetListPageRoutingModule {}
