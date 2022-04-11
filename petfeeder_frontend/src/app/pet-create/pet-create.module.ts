import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PetCreatePageRoutingModule } from './pet-create-routing.module';

import { PetCreatePage } from './pet-create.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PetCreatePageRoutingModule
  ],
  declarations: [PetCreatePage]
})
export class PetCreatePageModule {}
