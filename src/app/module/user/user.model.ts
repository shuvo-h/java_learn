import { model, Schema } from 'mongoose';
import { TAddress, TFullName, TOrders, TUser, UserStaticModel } from './user.interface';
import bcrypt from 'bcrypt';
import { env } from '../../config/configuration';

const fullNameSchema = new Schema<TFullName>({
  firstName: {
    type: String,
    trim: true,
    validate: {
      validator: function (value: string) {
        return /^[A-Za-z]+$/.test(value);
      },
      message: 'Only alphabet is accepted',
    },
  },
  lastName: {
    type: String,
    trim: true,
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
    trim: true,
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
    trim: true,
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
    trim: true,
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

const userSchema = new Schema<TUser,UserStaticModel>(
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
      min: [0, 'Age must be positive'],
    },
    email: {
      type: String,
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
      required: false,
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


// pre hook
userSchema.pre('save', async function(next){
  this.password = await bcrypt.hash(this.password,Number(env.BCRYPT_SALT_ROUND));
  next();
})
// post hook
userSchema.post('save', function(doc:TUser[],next){
  (doc as {password?:string}).password = undefined;
  next();
})

userSchema.post('find', function(doc:TUser[],next){
  doc.map((el:TUser)=>{
    (el as {password?:string}).password = undefined;
  });
  next();
})


// create custom static methods
userSchema.statics.isUserExistByStaticMethod = async function (userId: number) {
  const existingUser = await UserModel.findOne({ userId });
  return existingUser;
};
userSchema.statics.isUserExistByUserNameStaticMethod = async function (username: string) {
  const existingUser = await UserModel.findOne({ username });
  return existingUser;
};

export const UserModel = model<TUser,UserStaticModel>('User', userSchema);
