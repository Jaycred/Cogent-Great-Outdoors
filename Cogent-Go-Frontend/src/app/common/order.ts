import { DeliveryAddress } from "./delivery-address";
import { Product } from "./product";
import { User } from "./user";

export class Order {
    orderId:number;
	user:User;
    product: Product;
	quantity: number;
	price:number;
    da:DeliveryAddress;
}
