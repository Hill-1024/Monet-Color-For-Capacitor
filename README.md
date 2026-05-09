# Android Dynamic Color for Capacitor

中文 | [English](./README.en.md) | [日本語](./README.ja.md)

Android Dynamic Color for Capacitor 是一个 Capacitor 插件，用于在 Android 12+ 设备上读取系统 Material You / Monet 动态色。应用可以获取由用户壁纸生成的完整 tonal palettes，并把它们映射到自己的主题系统中。

插件只暴露一个小而明确的能力：读取系统动态色。如果运行环境不是 Android 12+，调用会抛出清晰错误，方便应用回退到自定义主题或默认主题。

## 功能特性

- 支持 Android 12+ Monet 动态色。
- 返回 `accent1`、`accent2`、`accent3`、`neutral1`、`neutral2` 五组 tonal palettes。
- 每组 palette 包含 `0`、`10`、`50`、`100` 到 `900`、`1000` 等色阶。
- 在 Web、iOS 或低版本 Android 上给出可捕获错误。
- 可与 React、Vue、Tailwind CSS、CSS Variables 或自定义设计系统结合。

## 安装

```bash
npm install android-dynamic-color
npx cap sync
```

如果直接从 GitHub 使用：

```bash
npm install github:Hill-1024/Monet-Color-For-Capacitor
npx cap sync
```

## API

### `getColors()`

读取系统动态色。

```typescript
getColors() => Promise<MonetPalette>
```

当平台不支持时会抛出错误。建议始终使用 `try/catch` 并提供回退主题。

## 类型

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

## 使用示例

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

## 项目结构

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

| 路径 | 说明 |
| --- | --- |
| `src/definitions.ts` | 插件 TypeScript 接口 |
| `src/index.ts` | 插件注册入口 |
| `src/web.ts` | Web fallback 实现 |
| `android/` | Android 原生实现 |
| `ios/` | iOS 平台占位/兼容结构 |

## 开发命令

| 命令 | 说明 |
| --- | --- |
| `npm run build` | 构建插件包 |
| `npm run lint` | 运行 lint |
| `npm run fmt` | 格式化代码 |
| `npm run verify` | 运行平台验证流程 |
| `npm run docgen` | 生成插件文档 |

## 设计边界

- 插件只读取系统 Monet palette，不负责生成完整设计系统。
- Web、iOS 和 Android 12 以下版本应由应用层决定回退颜色。
- 不建议在渲染路径中频繁调用 `getColors()`；读取后应缓存到应用状态或 CSS 变量。

## 许可证

本项目使用 MIT License。详见 [LICENSE](./LICENSE)。
