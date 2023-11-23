import express from 'express';
import { userController } from './user.controller';

const router = express.Router();


router.get('/', userController.getAllUsers);
router.post('/', userController.createUser);
router.get('/:userId', userController.getSingleUser);
router.put('/:userId', userController.updateSingleUser);
router.delete('/:userId', userController.deleteSingleUser);


export const UserRoute = router;