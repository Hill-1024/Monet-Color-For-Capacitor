import { registerPlugin } from '@capacitor/core';

import type { MonetColorPlugin } from './definitions';

const MonetColor = registerPlugin<MonetColorPlugin>('MonetColor', {
  web: () => import('./web').then((m) => new m.MonetColorWeb()),
});

export * from './definitions';
export { MonetColor };
