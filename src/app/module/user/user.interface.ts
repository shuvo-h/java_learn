import { Model } from 'mongoose';

export type TFullName = {
  firstName: string;
  lastName: string;
};
export type TAddress = {
  street: string;
  city: string;
  country: string;
};
export type TOrders = {
  productName: string;
  price: number;
  quantity: number;
};

export type TUser = {
  userId: number;
  username: string;
  password: string;
  fullName: TFullName;
  age: number;
  email: string;
  isActive: boolean;
  hobbies: string[];
  address: TAddress;
  orders?: TOrders[];
};
export type TUserOptional = {
  userId?: number;
  username?: string;
  password?: string;
  fullName?: TFullName;
  age?: number;
  email?: string;
  isActive?: boolean;
  hobbies?: string[];
  address?: TAddress;
  orders?: TOrders[];
};

// create static method for model
export interface UserStaticModel extends Model<TUser> {
  // eslint-disable-next-line no-unused-vars
  isUserExistByStaticMethod(userId: number): Promise<TUser | null>;
  // eslint-disable-next-line no-unused-vars
  isUserExistByUserNameStaticMethod(username: string): Promise<TUser | null>;
}
