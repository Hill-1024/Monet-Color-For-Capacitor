export interface MonetColorPlugin {
  /**
   * Get the system Monet colors (Material You).
   * Throws an error if not on Android 12+ (API 31+).
   */
  getColors(): Promise<MonetPalette>;
}

export interface MonetPalette {
  accent1: TonalPalette;
  accent2: TonalPalette;
  accent3: TonalPalette;
  neutral1: TonalPalette;
  neutral2: TonalPalette;
}

export interface TonalPalette {
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
