import { z } from 'zod';

const fullNameZodValidateSchema = z.object({
  firstName: z
    .string({ required_error: 'First Name is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z]+$/.test(value);
      },
      { message: 'Only alphabet is accepted' },
    ),

  lastName: z
    .string({ required_error: 'Last Name is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z]+$/.test(value);
      },
      { message: 'Only alphabet is accepted' },
    ),
});

const addressZodValidateSchema = z.object({
  street: z
    .string({ required_error: 'Street is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      { message: 'Only alphabet number and space are accepted' },
    ),

  city: z
    .string({ required_error: 'City is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      { message: 'Only alphabet number and space are accepted' },
    ),

  country: z
    .string({ required_error: 'Country is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      { message: 'Only alphabet number and space are accepted' },
    ),
});
const orderZodValidateSchema = z.object({
  productName: z
    .string({ required_error: 'Product name is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9\s]*$/.test(value);
      },
      { message: 'Only alphabet number and space are accepted' },
    ),

  price: z
    .number({ required_error: 'Price is required' })
    .gte(0, { message: "Price can't be negative" }),

  quantity: z
    .number({ required_error: 'Qantity is required' })
    .gte(0, { message: "Price can't be negative" }),
});

export const userZodValidateSchema = z.object({
  userId: z
    .number({ required_error: 'User ID is required' })
    .gte(0, { message: 'User ID must be positive' }),

  username: z
    .string({ required_error: 'Username is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9]*$/.test(value);
      },
      { message: 'Only alphabet and number are accepted' },
    ),

  password: z
    .string({ required_error: 'Password is required' })
    .trim()
    .min(4, { message: 'Atleast 4 characters required' })
    .max(16, { message: 'Maximum 16 characters allowed' }),

  fullName: fullNameZodValidateSchema,

  age: z
    .number({ required_error: 'Age is required' })
    .gte(0, { message: 'Age must be positive' }),

  email: z
    .string({ required_error: 'Email is required' })
    .trim()
    .email({ message: 'Invalid email address' }),

  isActive: z
    .boolean({
      required_error: 'isActive is required',
      invalid_type_error: 'isActive must be a boolean',
    })
    .default(true),

  hobbies: z
    .string({ required_error: 'Hobbies are required' })
    .array()
    .refine(
      (value) => {
        if (!Array.isArray(value)) {
          return false;
        }
        const hobbyRegex = /^[A-Za-z0-9\s]*$/;
        return value.every((hobby) => hobbyRegex.test(hobby));
      },
      {
        message: 'Only alphabet number and space are accepted in list',
      },
    ),
  address: addressZodValidateSchema,
  orders: z
    .array(orderZodValidateSchema)
    .refine(
      (value) => {
        if (Array.isArray(value)) {
          return true;
        }
        return false;
      },
      {
        message: 'Orders must be an array',
      },
    )
    .optional(),
});

// update validation schema
export const userZodUpdateValidateSchema = z.object({
  userId: z
    .number({ required_error: 'User ID is required' })
    .gte(0, { message: 'User ID must be positive' })
    .optional(),

  username: z
    .string({ required_error: 'Username is required' })
    .trim()
    .refine(
      (value) => {
        return /^[A-Za-z0-9]*$/.test(value);
      },
      { message: 'Only alphabet and number are accepted' },
    )
    .optional(),

  password: z
    .string({ required_error: 'Password is required' })
    .trim()
    .min(4, { message: 'Atleast 4 characters required' })
    .max(16, { message: 'Maximum 16 characters allowed' })
    .optional(),

  fullName: fullNameZodValidateSchema.optional(),

  age: z
    .number({ required_error: 'Age is required' })
    .gte(0, { message: 'Age must be positive' })
    .optional(),

  email: z
    .string({ required_error: 'Email is required' })
    .trim()
    .email({ message: 'Invalid email address' })
    .optional(),

  isActive: z
    .boolean({
      required_error: 'isActive is required',
      invalid_type_error: 'isActive must be a boolean',
    })
    .default(true)
    .optional(),

  hobbies: z
    .string({ required_error: 'Hobbies are required' })
    .array()
    .refine(
      (value) => {
        if (!Array.isArray(value)) {
          return false;
        }
        const hobbyRegex = /^[A-Za-z0-9\s]*$/;
        return value.every((hobby) => hobbyRegex.test(hobby));
      },
      {
        message: 'Only alphabet number and space are accepted in list',
      },
    )
    .optional(),
  address: addressZodValidateSchema.optional(),
  orders: z
    .array(orderZodValidateSchema)
    .refine(
      (value) => {
        if (Array.isArray(value)) {
          return true;
        }
        return false;
      },
      {
        message: 'Orders must be an array',
      },
    )
    .optional(),
});
