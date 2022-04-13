import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pet } from '../models/pet';
import { ApiService } from '../services/pet.service';


@Component({
  selector: 'app-pet-edit',
  templateUrl: './pet-edit.page.html',
  styleUrls: ['./pet-edit.page.scss'],
})
export class PetEditPage implements OnInit {

  name: string;
  data: Pet;

  constructor(
    public activatedRoute: ActivatedRoute,
    public router: Router,
    public apiService: ApiService
  ) {
      this.data = new Pet();
  }

  ngOnInit() {
    this.name = this.activatedRoute.snapshot.params["name"];
    //get item details using id
    this.apiService.getItem(this.name).subscribe(response => {
      console.log(response);
      this.data = response;
    })
  }

  update() {
    //Update item by taking id and updated data object
    this.apiService.updateItem(this.name, this.data).subscribe(response => {
      this.router.navigate(['pet-list']);
    })
  }

}
