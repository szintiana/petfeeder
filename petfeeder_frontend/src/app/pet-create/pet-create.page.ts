import { Component, OnInit } from '@angular/core';
import { Pet } from '../models/pet';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-create',
  templateUrl: './pet-create.page.html',
  styleUrls: ['./pet-create.page.scss'],
})
export class PetCreatePage implements OnInit {

  data: Pet

    constructor(
      public apiService: ApiService,
      public router: Router
    ) {
      this.data = new Pet();
    }

    ngOnInit() {
    }

    submitForm() {
      this.apiService.createItem(this.data).subscribe((response) => {
        this.router.navigate(['pet-list']);
      });

    }

}
