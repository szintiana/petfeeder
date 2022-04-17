import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvService {
  BASE_API_URL = 'http://127.0.0.1:';
  BASE_IP = '53361';
  BASE_URL = this.BASE_API_URL + this.BASE_IP;
  REGISTER_USER = this.BASE_URL + '/addUser';
  LOGIN = this.BASE_URL + '/login';
  REGISTER;
  GET_ALL_PETS;
  ADD_PET;
  UPDATE_PET;
  DELETE_PET;
  GET_PET;
  EDIT_USER;
  LOGOUT = this.BASE_URL + '/logout';
  ADD_FEED_DATE;
  REMOVE_FEED_DATE;
  GET_USER_BY_TOKEN = this.BASE_URL + '/findByToken';

  constructor() { }
}
