import { Order } from "./order";

export class DeliveryAddress {
    addressId:number;
	addressLine1:string;
	addressLine2:string;
	state:string;
	pincode:number;
	order:Order;
}
