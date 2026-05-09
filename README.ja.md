# Android Dynamic Color for Capacitor

[中文](./README.md) | [English](./README.en.md) | 日本語

Android Dynamic Color for Capacitor は、Android 12+ の Material You / Monet 動的カラーを読み取るための Capacitor プラグインです。ユーザーの壁紙から生成された tonal palettes を取得し、アプリ独自のテーマシステムへマッピングできます。

このプラグインが提供する能力は小さく明確です。システムの動的カラーを読み取ることだけを担当します。実行環境が Android 12+ ではない場合は明確なエラーを投げるため、アプリ側でカスタムテーマや既定テーマへフォールバックできます。

## 主な機能

- Android 12+ Monet 動的カラーに対応。
- `accent1`、`accent2`、`accent3`、`neutral1`、`neutral2` の 5 つの tonal palette を返します。
- 各 palette には `0`、`10`、`50`、`100` から `900`、`1000` までの色階調が含まれます。
- Web、iOS、古い Android では捕捉可能なエラーを返します。
- React、Vue、Tailwind CSS、CSS Variables、独自デザインシステムと組み合わせられます。

## インストール

```bash
npm install android-dynamic-color
npx cap sync
```

GitHub から直接使う場合：

```bash
npm install github:Hill-1024/Monet-Color-For-Capacitor
npx cap sync
```

## API

### `getColors()`

システム動的カラーを読み取ります。

```typescript
getColors() => Promise<MonetPalette>
```

未対応プラットフォームではエラーを投げます。常に `try/catch` を使い、フォールバックテーマを用意してください。

## 型

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

## 使用例

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

## プロジェクト構成

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

| Path | 説明 |
| --- | --- |
| `src/definitions.ts` | プラグイン TypeScript インターフェース |
| `src/index.ts` | プラグイン登録入口 |
| `src/web.ts` | Web fallback 実装 |
| `android/` | Android ネイティブ実装 |
| `ios/` | iOS 互換構造 |

## 開発コマンド

| Command | 説明 |
| --- | --- |
| `npm run build` | プラグインパッケージをビルド |
| `npm run lint` | lint を実行 |
| `npm run fmt` | コードをフォーマット |
| `npm run verify` | プラットフォーム検証を実行 |
| `npm run docgen` | プラグインドキュメントを生成 |

## 境界

- このプラグインはシステム Monet palette の読み取りだけを担当し、完全なデザインシステムは生成しません。
- Web、iOS、Android 12 未満では、アプリ側のフォールバックテーマで対応してください。
- レンダリング経路で `getColors()` を頻繁に呼ばないでください。読み取った結果はアプリ状態または CSS 変数にキャッシュすることを推奨します。

## ライセンス

このプロジェクトは MIT License の下で公開されています。詳しくは [LICENSE](./LICENSE) を参照してください。
