import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Pet } from '../models/pet';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  base_ip = '59891';
  base_path = 'http://127.0.0.1:';

  constructor(private http: HttpClient) {
  }

  httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json' })};

  handleError(error: HttpErrorResponse) {
      if (error.error instanceof ErrorEvent) {
        // A client-side or network error occurred. Handle it accordingly.
        console.error('An error occurred:', error.error.message);
      } else {
        // The backend returned an unsuccessful response code.
        // The response body may contain clues as to what went wrong,
        console.error(
          `Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }
      // return an observable with a user-facing error message
      return throwError('Something bad happened; please try again later.');
  }

  createItem(item): Observable<Pet> {
      return this.http
        .post<Pet>(this.base_path + this.base_ip, JSON.stringify(item), this.httpOptions)
        .pipe(
          retry(2),
          catchError(this.handleError)
        );
  }

  getItem(name): Observable<Pet> {
    return this.http
      .get<Pet>(this.base_path + '/' + name)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  getList(): Observable<Pet> {
      return this.http
        .get<Pet>(this.base_path + this.base_ip + "/getAllPets")
        .pipe(
          retry(2),
          catchError(this.handleError)
        )
  }

  updateItem(name, item): Observable<Pet> {
      return this.http
        .put<Pet>(this.base_path + '/' + name, JSON.stringify(item), this.httpOptions)
        .pipe(
          retry(2),
          catchError(this.handleError)
        )
  }

  deleteItem(name) {
    return this.http
      .delete<Pet>(this.base_path + '/' + name, this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }
}
