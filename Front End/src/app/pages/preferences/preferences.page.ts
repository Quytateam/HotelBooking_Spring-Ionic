import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { CategoryService } from 'src/app/services/category.service';
import { SaveCategoryService } from 'src/app/services/save-category.service';

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.page.html',
  styleUrls: ['./preferences.page.scss'],
})
export class PreferencesPage implements OnInit {
  listCategory: any;
  totalItemsCart: number = 0;
  constructor(
    private router: Router,
    private categoryService: CategoryService,
    private cdr: ChangeDetectorRef,
    private saveCategoryService: SaveCategoryService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.getListCategory();
  }
  next() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.saveCategoryService.clearCategory();
    this.listCategory.map((item) => {
      if (item.checked) {
        this.saveCategoryService.addToCategory(item.id);
      }
    });
    // console.log(localStorage.getItem('category_id'));
    if (localStorage.getItem('category_id') !== null) {
      this.router.navigate(['/setting']);
    }
  }

  getListCategory() {
    this.categoryService.getListCategory().subscribe({
      next: (res) => {
        // this.listCategory = res;
        this.listCategory = res.map((item) => ({
          ...item,
          checked: this.getRandomBoolean(),
          color: this.getRandomIcon(),
        }));
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getRandomIcon(): string {
    const icons = [
      'facebook',
      'twitter',
      'instagram',
      'linkedin',
      'whatsapp',
      'google',
    ];
    const randomIndex = Math.floor(Math.random() * icons.length);
    return icons[randomIndex];
  }

  getRandomBoolean(): boolean {
    return Math.random() <= 0.5; // 50% cơ hội là true, 50% cơ hội là false
  }
  preventChange(item: any) {
    item.checked = !item.checked;
  }
}
