import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BOOKING_API = 'http://localhost:8080/api/booking/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  constructor(private http: HttpClient) {}

  placeBooking(
    userId: number,
    chefId: number,
    title: string,
    description: string,
    image: string,
    startTime: string,
    endTime: string,
    email: string,
    phone: string,
    address: string
  ): Observable<any> {
    return this.http.post(
      BOOKING_API + 'create',
      {
        userId,
        chefId,
        title,
        description,
        image,
        startTime,
        endTime,
        email,
        phone,
        address,
      },
      httpOptions
    );
  }

  getListByChefId(id: number): Observable<any> {
    return this.http.get(BOOKING_API + 'chef/' + id, httpOptions);
  }
}
