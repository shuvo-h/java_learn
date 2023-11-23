import express, { Application, Request, Response } from 'express';
import cors from 'cors';
import { UserRoute } from './app/module/user/user.route';

export const app: Application = express();

// parsers
app.use(express.json());
app.use(cors());

app.use('/api/users', UserRoute);

app.get('/', (req: Request, res: Response) => {
  res.send('Hello Assignment 2!');
});
