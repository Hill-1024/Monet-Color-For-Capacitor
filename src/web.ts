import { WebPlugin } from '@capacitor/core';

import type { MonetColorPlugin, MonetPalette } from './definitions';

export class MonetColorWeb extends WebPlugin implements MonetColorPlugin {
  async getColors(): Promise<MonetPalette> {
    throw new Error('Monet colors are only available on Android 12+');
  }
}
