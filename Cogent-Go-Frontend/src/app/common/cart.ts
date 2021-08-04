import { Product } from "./product";
import { User } from "./user";

export class Cart {
    cartId:number;
	user:User;
    product: Product;
	quantity: number;
	price:number;
}
