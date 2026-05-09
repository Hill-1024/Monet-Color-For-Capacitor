# Android Dynamic Color for Capacitor

[中文](./README.md) | English | [日本語](./README.ja.md)

Android Dynamic Color for Capacitor is a Capacitor plugin for reading Android 12+ Material You / Monet dynamic colors. It lets an app access the complete tonal palettes generated from the user's wallpaper and map them into its own theme system.

The plugin exposes one small, explicit capability: read system dynamic colors. If the runtime is not Android 12+, the call throws a clear error so the app can fall back to a custom or default theme.

## Features

- Android 12+ Monet dynamic color support.
- Returns five tonal palettes: `accent1`, `accent2`, `accent3`, `neutral1`, and `neutral2`.
- Each palette includes shades `0`, `10`, `50`, `100` through `900`, and `1000`.
- Throws catchable errors on Web, iOS, or older Android versions.
- Works with React, Vue, Tailwind CSS, CSS variables, or custom design systems.

## Installation

```bash
npm install android-dynamic-color
npx cap sync
```

Install directly from GitHub:

```bash
npm install github:Hill-1024/Monet-Color-For-Capacitor
npx cap sync
```

## API

### `getColors()`

Reads system dynamic colors.

```typescript
getColors() => Promise<MonetPalette>
```

The method throws when the platform is unsupported. Always use `try/catch` and provide a fallback theme.

## Types

```typescript
interface MonetPalette {
  accent1: TonalPalette;
  accent2: TonalPalette;
  accent3: TonalPalette;
  neutral1: TonalPalette;
  neutral2: TonalPalette;
}

interface TonalPalette {
  0: string;
  10: string;
  50: string;
  100: string;
  200: string;
  300: string;
  400: string;
  500: string;
  600: string;
  700: string;
  800: string;
  900: string;
  1000: string;
}
```

## Usage

```tsx
import { useEffect } from 'react';
import { MonetColor } from 'android-dynamic-color';

export function AppTheme() {
  useEffect(() => {
    const applyTheme = async () => {
      try {
        const palette = await MonetColor.getColors();
        const root = document.documentElement;

        root.style.setProperty('--color-primary', palette.accent1[600]);
        root.style.setProperty('--color-primary-container', palette.accent1[100]);
        root.style.setProperty('--color-on-primary', palette.accent1[0]);
        root.style.setProperty('--color-background', palette.neutral1[10]);
        root.style.setProperty('--color-surface', palette.neutral1[50]);
      } catch {
        document.documentElement.style.setProperty('--color-primary', '#0f766e');
      }
    };

    applyTheme();
  }, []);

  return null;
}
```

## Project Structure

```text
.
├── android/
├── ios/
├── src/
│   ├── definitions.ts
│   ├── index.ts
│   └── web.ts
├── AndroidDynamicColor.podspec
├── Package.swift
└── rollup.config.mjs
```

| Path | Purpose |
| --- | --- |
| `src/definitions.ts` | Plugin TypeScript interfaces |
| `src/index.ts` | Plugin registration entry |
| `src/web.ts` | Web fallback implementation |
| `android/` | Android native implementation |
| `ios/` | iOS compatibility structure |

## Development Commands

| Command | Description |
| --- | --- |
| `npm run build` | Build the plugin package |
| `npm run lint` | Run linting |
| `npm run fmt` | Format code |
| `npm run verify` | Run platform verification |
| `npm run docgen` | Generate plugin documentation |

## Boundaries

- The plugin only reads the system Monet palette; it does not generate a full design system.
- Web, iOS, and Android versions below 12 should be handled by the app's fallback theme.
- Avoid calling `getColors()` repeatedly in the render path. Read once and cache the result in app state or CSS variables.

## License

This project is licensed under the MIT License. See [LICENSE](./LICENSE).
