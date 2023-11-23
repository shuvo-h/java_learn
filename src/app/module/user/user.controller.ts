import { Request, Response } from 'express';
import { ZodError } from 'zod';
import { TOrders } from './user.interface';
import { userService } from './user.service';
import {
  orderZodValidateSchema,
  userZodUpdateValidateSchema,
  userZodValidateSchema,
} from './user.zod.validation';

const createUser = async (req: Request, res: Response): Promise<void> => {
  try {
    const user = req.body;

    const validateUser = userZodValidateSchema.parse(user);

    const result = await userService.createUserIntoDb(validateUser);

    res.status(201).json({
      success: true,
      message: 'User created successfully!',
      data: result,
    });
  } catch (error) {
    if (error instanceof ZodError) {
      const err = error.errors.map((el) => {
        return {
          [el.path.join('.')]: el.message,
        };
      });
      res.status(500).json({
        success: false,
        message: 'Validation failed',
        error: {
          code: 500,
          description: err || 'something went wrong',
        },
      });
    } else {
      res.status(500).json({
        success: false,
        message:
          (error as { message: string }).message || 'something went wrong',
        error: {
          code: 500,
          description:
            (error as { message: string }).message || 'something went wrong',
        },
      });
    }
  }
};

const getAllUsers = async (req: Request, res: Response): Promise<void> => {
  try {
    const selectedFields = {
      username: 1,
      fullName: 1,
      age: 1,
      email: 1,
      address: 1,
    };
    const result = await userService.getAllUserFromDB(selectedFields);
    res.status(201).json({
      success: true,
      message: 'Users fetched successfully!',
      data: result,
    });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: (error as { message: string }).message || 'something went wrong',
      error: {
        code: 500,
        description:
          (error as { message: string }).message || 'something went wrong',
      },
    });
  }
};

const getSingleUser = async (req: Request, res: Response): Promise<void> => {
  try {
    const userId = req.params.userId;
    const result = await userService.getSingleUserFromDB(Number(userId));
    if (result) {
      (result as { password?: string }).password = undefined;
    }

    res.status(201).json({
      success: true,
      message: 'User fetched successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    res.status(statusCode).json({
      success: false,
      message: (error as { message: string }).message || 'something went wrong',
      error: {
        code: statusCode,
        description:
          (error as { message: string }).message || 'something went wrong',
      },
    });
  }
};

const updateSingleUser = async (req: Request, res: Response): Promise<void> => {
  try {
    const userId = req.params.userId;
    const newInfo = req.body;
    // validate
    const newValidateInfo = userZodUpdateValidateSchema.parse(newInfo);

    const result = await userService.updateUserByUserId(
      Number(userId),
      newValidateInfo,
    );
    (result as { password?: string }).password = undefined;

    res.status(201).json({
      success: true,
      message: 'User updated successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    if (error instanceof ZodError) {
      const err = error.errors.map((el) => {
        return {
          [el.path.join('.')]: el.message,
        };
      });
      res.status(statusCode).json({
        success: false,
        message: 'Validation failed',
        error: {
          code: statusCode,
          description: err || 'something went wrong',
        },
      });
    } else {
      res.status(statusCode).json({
        success: false,
        message:
          (error as { message: string }).message || 'something went wrong',
        error: {
          code: statusCode,
          description:
            (error as { message: string }).message || 'something went wrong',
        },
      });
    }
  }
};

const deleteSingleUser = async (req: Request, res: Response): Promise<void> => {
  try {
    const userId = req.params.userId;
    const result = await userService.deleteUserByUserId(Number(userId));

    res.status(200).json({
      success: true,
      message: 'User deleted successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    res.status(statusCode).json({
      success: false,
      message: (error as { message: string }).message || 'something went wrong',
      error: {
        code: statusCode,
        description:
          (error as { message: string }).message || 'something went wrong',
      },
    });
  }
};

const addOrderTOList = async (req: Request, res: Response): Promise<void> => {
  try {
    const userId: string = req.params.userId;
    const order: TOrders = req.body;
    // validate the body
    const newValidateOrder = orderZodValidateSchema.parse(order);
    const result = await userService.addAnOrderByUserId(
      Number(userId),
      newValidateOrder,
    );

    res.status(200).json({
      success: true,
      message: 'Order created successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    if (error instanceof ZodError) {
      const err = error.errors.map((el) => {
        return {
          [el.path.join('.')]: el.message,
        };
      });
      res.status(statusCode).json({
        success: false,
        message: 'Validation failed',
        error: {
          code: statusCode,
          description: err || 'something went wrong',
        },
      });
    } else {
      res.status(statusCode).json({
        success: false,
        message:
          (error as { message: string }).message || 'something went wrong',
        error: {
          code: statusCode,
          description:
            (error as { message: string }).message || 'something went wrong',
        },
      });
    }
  }
};

const getOrdersOfSingleuser = async (
  req: Request,
  res: Response,
): Promise<void> => {
  try {
    const userId = req.params.userId;
    const result = await userService.getAllOrdersByUserId(Number(userId));

    res.status(200).json({
      success: true,
      message: 'Order fetched successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    res.status(statusCode).json({
      success: false,
      message: (error as { message: string }).message || 'something went wrong',
      error: {
        code: statusCode,
        description:
          (error as { message: string }).message || 'something went wrong',
      },
    });
  }
};
const getTotalPriceOfOrderByuserId = async (
  req: Request,
  res: Response,
): Promise<void> => {
  try {
    const userId = req.params.userId;
    const result = await userService.getTotalPriceOfOrderByuserId(
      Number(userId),
    );

    res.status(200).json({
      success: true,
      message: 'Total price calculated successfully!',
      data: result,
    });
  } catch (error) {
    const statusCode = (error as { ststusCode: number }).ststusCode || 500;
    res.status(statusCode).json({
      success: false,
      message: (error as { message: string }).message || 'something went wrong',
      error: {
        code: statusCode,
        description:
          (error as { message: string }).message || 'something went wrong',
      },
    });
  }
};

export const userController = {
  createUser,
  getAllUsers,
  getSingleUser,
  updateSingleUser,
  deleteSingleUser,
  addOrderTOList,
  getOrdersOfSingleuser,
  getTotalPriceOfOrderByuserId,
};
