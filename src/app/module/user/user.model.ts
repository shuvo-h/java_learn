import { model, Schema } from 'mongoose';
import { TAddress, TFullName, TOrders, TUser } from './user.interface';

const fullNameSchema = new Schema<TFullName>({
  firstName: {
    type: String,
    required: [true, ' First Name is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z]+$/.test(value);
      },
      message: 'Only alphabet is accepted',
    },
  },
  lastName: {
    type: String,
    required: [true, ' Last Name is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z]+$/.test(value);
      },
      message: '{VALUE} is not acceptable. Only alphabet is accepted',
    },
  },
});
const addressSchema = new Schema<TAddress>({
  street: {
    type: String,
    required: [true, 'Street is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      message:
        '{VALUE} is not acceptable. Only alphabet number and space are accepted',
    },
  },
  city: {
    type: String,
    required: [true, 'City is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z\s]*$/.test(value);
      },
      message:
        '{VALUE} is not acceptable. Only alphabet and space are accepted',
    },
  },
  country: {
    type: String,
    required: [true, 'Country is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z]*$/.test(value);
      },
      message: '{VALUE} is not acceptable. Only alphabet are accepted',
    },
  },
});
const orderSchema = new Schema<TOrders>({
  productName: {
    type: String,
    required: [true, 'Product name is required'],
    trim: true,
    minlength: [2, 'Atleast 2 characters required'],
    maxlength: [20, 'Maximum 20 characters allowed'],
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      message:
        '{VALUE} is not acceptable. Only alphabet number and space are accepted',
    },
  },
  price: {
    type: Number,
    required: [true, 'Product price is required'],
    min: [0, "Price can't be negative"],
  },
  quantity: {
    type: Number,
    required: [true, 'Product quantity is required'],
    min: [0, "Quantity can't be negative"],
  },
});

const userSchema = new Schema<TUser>(
  {
    userId: {
      type: Number,
      required: [true, 'User ID is required'],
      unique: true,
      min: [0, 'User ID must be positive'],
    },
    username: {
      type: String,
      required: [true, 'Username is required'],
      trim: true,
      unique: true,
      minlength: [2, 'Minimum 2 characters are required'],
      maxlength: [25, 'Maximum 25 characters are allowed'],
      validate: {
        validator: function (value: string) {
          return /^[A-Za-z0-9]*$/.test(value);
        },
        message:
          '{VALUE} is not acceptable. Only alphabet and number are accepted',
      },
    },
    password: {
      type: String,
      required: [true, 'Password is required'],
      trim: true,
    },
    fullName: {
      type: fullNameSchema,
      required: [true, 'Name is required'],
    },
    age: {
      type: Number,
      required: [true, 'Age is required'],
      min: [0, 'Age must be positive'],
    },
    email: {
      type: String,
      required: [true, 'Email is required'],
      trim: true,
    },
    isActive: {
      type: Boolean,
      default: true,
    },
    hobbies: {
      type: [String],
      required: [true, 'Hobbies are required'],
      validate: {
        validator: function (value: string[]) {
          if (!Array.isArray(value)) {
            return false;
          }
          const hobbyRegex = /^[A-Za-z0-9\s]*$/;
          return value.every((hobby) => hobbyRegex.test(hobby));
        },
        message: 'Only alphabet number and space are accepted in list',
      },
    },
    address: {
      type: addressSchema,
      required: [true, 'Address is required'],
    },
    orders: {
      type: [orderSchema],
      validate: {
        validator: function (value: TOrders[]) {
          if (Array.isArray(value)) {
            return true;
          }
          return false;
        },
        message: 'Orders must be an array',
      },
    },
  },
  // {}
);

export const UserModel = model<TUser>('User', userSchema);
