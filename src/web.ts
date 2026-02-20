import { WebPlugin } from '@capacitor/core';

import type { MonetColorPlugin } from './definitions';

export class MonetColorWeb extends WebPlugin implements MonetColorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
