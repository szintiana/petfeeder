import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PetEditPageRoutingModule } from './pet-edit-routing.module';

import { PetEditPage } from './pet-edit.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PetEditPageRoutingModule
  ],
  declarations: [PetEditPage]
})
export class PetEditPageModule {}
