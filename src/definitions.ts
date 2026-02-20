export interface MonetColorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
