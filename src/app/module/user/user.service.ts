import { TUser, TUserOptional } from "./user.interface";
import { UserModel } from "./user.model";

const createUserIntoDb =async(user: TUser):Promise<TUser> =>{
    const isExistUser = await UserModel.isUserExistByStaticMethod(user.userId);
    if (isExistUser) {
        throw new Error('User id already exist');
    }
    const isExistUserByUsername = await UserModel.isUserExistByUserNameStaticMethod(user.username);
    if (isExistUserByUsername) {
        throw new Error('Username already exist');
    }

    const result = await UserModel.create(user);
    return result;
}

const getAllUserFromDB = async(fields={}):Promise<TUser[]> =>{
    const result = await UserModel.find({},fields);
    return result;
}
const getSingleUserFromDB = async(userId:number):Promise<TUser|null> =>{
    const result = await UserModel.findOne({userId});
    return result;
}

const updateUserByUserId = async(userId:number,userInfo:TUserOptional):Promise<TUser|null> =>{
    const isExistUser = await UserModel.isUserExistByStaticMethod(userId);
        if (!isExistUser) {
            const err = new Error('User not found');
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            (err as any).ststusCode = 404;
            throw err;
        }
        if (userInfo.username) {
            const isExistUserByUsername = await UserModel.isUserExistByUserNameStaticMethod(userInfo.username);
            if (isExistUserByUsername) {
                throw new Error('Username already exist');
            }
        }

    const doc = await UserModel.findOneAndUpdate(
        {userId},
        {
            $set: userInfo
        },
        {new: true, runValidators: true}
    );
    const result = doc?.toObject() as TUser | null;
    if(result?.password)  (result as unknown as {password: undefined}).password = undefined;
    if(result?.orders)  result.orders = undefined;
    return result;
}

const deleteUserByUserId = async(userId:number,):Promise<null> =>{
    const isExistUser = await UserModel.isUserExistByStaticMethod(userId);
        if (!isExistUser) {
            const err = new Error('User not found');
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            (err as any).ststusCode = 404;
            throw err;
        }
        

    await UserModel.findOneAndDelete({userId});
    
    return null;
}

export const userService = {
    createUserIntoDb,
    getAllUserFromDB,
    getSingleUserFromDB,
    updateUserByUserId,
    deleteUserByUserId,
}