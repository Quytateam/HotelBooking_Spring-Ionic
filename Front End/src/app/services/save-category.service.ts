import { EventEmitter, Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SaveCategoryService {
  items: any[] = [];

  totalPrice = 0;

  total = 0;

  constructor() {}

  saveCategory(): void {
    localStorage.setItem('category_id', JSON.stringify(this.items));
  }

  addToCategory(item: any) {
    this.loadCategory();
    this.items.push(item);
    this.saveCategory();
  }
  loadCategory(): void {
    this.items = JSON.parse(localStorage.getItem('category_id') as any) || [];
  }

  getItems() {
    return this.items;
  }

  clearCategory() {
    this.items = [];
    localStorage.removeItem('category_id');
  }
}
