import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceDetail } from '../_class/service-detail';

const SERVICE_API = 'http://localhost:8080/api/service/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class ServiceService {
  constructor(private http: HttpClient) {}

  createImportCoupon(
    chefId: number,
    serviceDate: string,
    serviceDetail: ServiceDetail[]
  ): Observable<any> {
    return this.http.post(
      SERVICE_API + 'create',
      { chefId, serviceDate, serviceDetail },
      httpOptions
    );
  }

  getServiceByChef(chefId: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('chefId', chefId);
    return this.http.get(SERVICE_API + 'chef', { params: params });
  }

  getServiceDetail(serviceId: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('serviceId', serviceId);
    return this.http.get(SERVICE_API + 'detail', { params: params });
  }
}
