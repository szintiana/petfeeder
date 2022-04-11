import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.page.html',
  styleUrls: ['./pet-list.page.scss'],
})
export class PetListPage implements OnInit {

  petsData: any;

    constructor(
      public apiService: ApiService
    ) {
      this.petsData = [];
    }

    ngOnInit() {
      // this.getAllStudents();
    }

    ionViewWillEnter() {
      // Used ionViewWillEnter as ngOnInit is not
      // called due to view persistence in Ionic
      this.getAllPets();
    }

    getAllPets() {
      //Get saved list of students
      this.apiService.getList().subscribe(response => {
        console.log(response);
        this.petsData = response;
      })
    }


    delete(item) {
      //Delete item in Student data
      this.apiService.deleteItem(item.name).subscribe(Response => {
        //Update list after delete is successful
        this.getAllPets();
      });
    }

}
