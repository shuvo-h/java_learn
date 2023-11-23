# User Management and Orders(Assignment 2)

This is a simple TypeScript project using Express, mongoose, typescript, and bcrypt. This package is developed to manage users and their orders.

Module based project has been structured and Eslint and Prettier has been used to ensure the code formatting and code cleanup. 

## Live URL 
Click here to visit the root of the server [User Management](https://assignment-2-ebon.vercel.app)

## Prerequisites

Make sure you have the following installed on your machine:

- [Node.js](https://nodejs.org/)
- [npm](https://www.npmjs.com/) (Node Package Manager)

## Installation

```bash
git clone https://github.com/shuvo-h/user-order.git
cd user-order
npm install
```
## App Configuration(.env)
Add your environment variable values like database url, saltround for bycript, production env etc to the .env file.

- [NODE_ENV]()(development || production)
- [MONGO_URL]()(mongodb+srv://username:password@clasternumber/?)
- [DB_NAME]()(database_name)
- [PORT]()(5000)
- [BCRYPT_SALT_ROUNDS]()(12)



## Run the Server

```bash
npm run build
npm run start
```
