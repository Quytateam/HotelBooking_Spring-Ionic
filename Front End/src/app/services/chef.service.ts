import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const CHEF_API = 'http://localhost:8080/api/chef/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class ChefService {
  constructor(private http: HttpClient) {}

  getListChef(): Observable<any> {
    return this.http.get(CHEF_API, httpOptions);
  }

  getChef(id: number): Observable<any> {
    return this.http.get(CHEF_API + id, httpOptions);
  }

  // searchChef(keyword: string): Observable<any> {
  //   let params = new HttpParams();
  //   params = params.append('keyword', keyword);
  //   return this.http.get(CHEF_API + 'search', { params: params });
  // }

  // getListByPriceRange(id: number, min: number, max: number): Observable<any> {
  //   let params = new HttpParams();
  //   params = params.append('id', id);
  //   params = params.append('min', min);
  //   params = params.append('max', max);
  //   return this.http.get(CHEF_API + 'range', { params: params });
  // }

  getListRandom(number: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('number', number);
    return this.http.get(CHEF_API + 'random', { params: params });
  }

  // createChef(
  //   name: string,
  //   description: string,
  //   price: number,
  //   quantity: number,
  //   unit: string,
  //   categoryId: number,
  //   imageIds: Array<string>
  // ): Observable<any> {
  //   return this.http.post(
  //     CHEF_API + 'create',
  //     { name, description, price, quantity, unit, categoryId, imageIds },
  //     httpOptions
  //   );
  // }

  createChef(
    name: string,
    address: string,
    star: number,
    review: number,
    image: string,
    dishIds: Array<string>
  ): Observable<any> {
    return this.http.post(
      CHEF_API + 'create',
      { name, address, star, review, image, dishIds },
      httpOptions
    );
  }

  // updateChef(
  //   id: number,
  //   name: string,
  //   description: string,
  //   price: number,
  //   quantity: number,
  //   unit: string,
  //   categoryId: number,
  //   imageIds: Array<string>
  // ): Observable<any> {
  //   return this.http.put(
  //     CHEF_API + 'update/' + id,
  //     { name, description, price, quantity, unit, categoryId, imageIds },
  //     httpOptions
  //   );
  // }

  // deleteChef(id: number): Observable<any> {
  //   return this.http.delete(CHEF_API + 'delete/' + id, httpOptions);
  // }
}
