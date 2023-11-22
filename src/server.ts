import { env } from './app/config/configuration';
import { app } from './app';
import mongoose, { ConnectOptions } from 'mongoose';

async function main() {
  try {
    if (env.dn_url && env.db_name) {
      await mongoose.connect(env.dn_url, {
        dbName: env.db_name,
      } as ConnectOptions);
    }
    app.listen(env.port, () => {
      console.log(`app listening on port = `, env.port);
    });
  } catch (error) {
    console.log(error);
  }
}

main();
