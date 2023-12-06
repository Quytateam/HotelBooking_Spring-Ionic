import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-blog-post',
  templateUrl: './blog-post.page.html',
  styleUrls: ['./blog-post.page.scss'],
})
export class BlogPostPage implements OnInit {
  totalItemsCart: number = 0;
  constructor(
    private _route: ActivatedRoute,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    const id = +this._route.snapshot.paramMap.get('id');
  }
}
