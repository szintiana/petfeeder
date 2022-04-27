import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs/operators';
import { ApiService } from '../services/pet.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.page.html',
  styleUrls: ['./pet-list.page.scss'],
})
export class PetListPage implements OnInit {

  petsData: any;

    constructor(
      public apiService: ApiService,
      public authService: AuthService
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

    async getAllPets() {
      //Get saved list of students
      console.log(this.authService.user());
      this.apiService.getList().pipe(timeout(300000)).subscribe(response => {
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
