import dotenv from 'dotenv';
import path from 'path';

// initialize dotenv
const currentPath = process.cwd();
const envFilePath = path.join(currentPath, '.env');
dotenv.config({
  path: envFilePath,
});

export const env = {
  port: process.env.PORT || 5000,
  dn_url: process.env.MONGO_URL,
  db_name: process.env.DB_NAME,
  BCRYPT_SALT_ROUND: process.env.BCRYPT_SALT_ROUND,
};
